package at.technikum.webengbackend.controller;

import at.technikum.webengbackend.dto.CustomerOrderDTO;
import at.technikum.webengbackend.dto.OrderDTO;
import at.technikum.webengbackend.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class OrderControllerTest {

    @Mock
    private OrderService orderService;

    @InjectMocks
    private OrderController orderController;

    @BeforeEach
    void setUp() {
        orderController = new OrderController(orderService);
    }

    @Test
    void getOrdersTest() {
        when(orderService.getOrders()).thenReturn(Collections.singletonList(new CustomerOrderDTO()));
        ResponseEntity<List<CustomerOrderDTO>> response = orderController.getOrders();
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
    }

    @Test
    void getOrderTest() {
        Long orderId = 1L;
        when(orderService.getOrder(orderId)).thenReturn(new CustomerOrderDTO());
        ResponseEntity<CustomerOrderDTO> response = orderController.getOrder(orderId);
        assertNotNull(response.getBody());
    }

    @Test
    void createNewOrderTest() {
        OrderDTO orderDTO = new OrderDTO();
        ResponseEntity<?> response = orderController.createNewOrder(orderDTO);
        assertEquals(201, response.getStatusCodeValue());
        verify(orderService).addNewOrder(any(OrderDTO.class));
    }

    @Test
    void deleteOrderTest() {
        Long orderId = 1L;
        ResponseEntity<?> response = orderController.deleteOrder(orderId);
        assertEquals(204, response.getStatusCodeValue());
        verify(orderService).delete(orderId);
    }

}