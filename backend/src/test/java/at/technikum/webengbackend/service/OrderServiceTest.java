package at.technikum.webengbackend.service;

import at.technikum.webengbackend.dto.CustomerOrderDTO;
import at.technikum.webengbackend.dto.OrderDTO;
import at.technikum.webengbackend.dto.ProductDTO;
import at.technikum.webengbackend.model.CustomerOrder;
import at.technikum.webengbackend.model.OrderDetail;
import at.technikum.webengbackend.model.Product;
import at.technikum.webengbackend.model.User;
import at.technikum.webengbackend.repository.OrderRepository;
import at.technikum.webengbackend.repository.ProductRepository;
import at.technikum.webengbackend.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private UserRepository userRepository;

    private OrderService orderService;

    @BeforeEach
    void setUp() {
        orderService = new OrderService(orderRepository, productRepository, userRepository);
    }

    @Test
    void getOrdersTest() {
        CustomerOrder ord = new CustomerOrder();
        ord.setOrderNo("123");
        ord.setId(1L);
        ord.setOrderDetails(Collections.singletonList(new OrderDetail()));
        when(orderRepository.findAllWithDetails()).thenReturn(buildCustomerOrderList());
        List<CustomerOrderDTO> orders = orderService.getOrders();
        assertNotNull(orders);
        assertFalse(orders.isEmpty());
    }

    @Test
    void getOrderTest() {
        Long orderId = 1L;
        CustomerOrder order = new CustomerOrder();
        when(orderRepository.existsById(orderId)).thenReturn(true);
        when(orderRepository.findById(orderId)).thenReturn(Optional.of(buildCustomerOrder()));
        CustomerOrderDTO result = orderService.getOrder(orderId);
        assertNotNull(result);
    }

    @Test
    void addNewOrderTest() {
        when(productRepository.findById(ArgumentMatchers.any())).thenReturn(Optional.of(new Product()));
        OrderDTO orderDTO = new OrderDTO();
        User user = new User();
        user.setId(orderDTO.getUserId());
        ProductDTO productDTO = new ProductDTO();
        orderDTO.setProducts(Collections.singletonList(productDTO));
        when(userRepository.findById(orderDTO.getUserId())).thenReturn(Optional.of(user));
        orderService.addNewOrder(orderDTO);
        verify(orderRepository).save(any(CustomerOrder.class));
    }

    @Test
    void deleteOrderTest() {
        Long orderId = 1L;
        when(orderRepository.existsById(orderId)).thenReturn(true);
        orderService.delete(orderId);
        verify(orderRepository).deleteById(orderId);
    }

    public List<CustomerOrder> buildCustomerOrderList(){
        CustomerOrder c = new CustomerOrder();
        c.setOrderNo("1234");
        c.setId(1L);
        c.setUser(new User());
        OrderDetail detail = new OrderDetail();
        detail.setOrder(c);
        detail.setProduct(new Product());
        detail.setQuantity(1);
        c.setOrderDetails(Collections.singletonList(detail));
        return Collections.singletonList(c);
    }

    public CustomerOrder buildCustomerOrder(){
        CustomerOrder c = new CustomerOrder();
        c.setOrderNo("1234");
        c.setId(1L);
        c.setUser(new User());
        OrderDetail detail = new OrderDetail();
        detail.setOrder(c);
        detail.setProduct(new Product());
        detail.setQuantity(1);
        c.setOrderDetails(Collections.singletonList(detail));
        return c;
    }
}