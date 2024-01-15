package at.technikum.webengbackend.service;

import at.technikum.webengbackend.dto.*;
import at.technikum.webengbackend.model.CustomerOrder;
import at.technikum.webengbackend.model.OrderDetail;
import at.technikum.webengbackend.model.Product;
import at.technikum.webengbackend.model.User;
import at.technikum.webengbackend.repository.OrderRepository;

import at.technikum.webengbackend.repository.ProductRepository;
import at.technikum.webengbackend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public OrderService(OrderRepository orderRepository, ProductRepository productRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    public List<CustomerOrderDTO> getOrders() {
        List<CustomerOrder> orders = orderRepository.findAllWithDetails();
        return orders.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private CustomerOrderDTO convertToDTO(CustomerOrder order) {
        return CustomerOrderDTO.builder().orderNo(order.getOrderNo()).id(order.getId()).totalQuantity(order.getTotalQuantity()).createdOn(order.getCreatedOn()).lastUpdatedOn(order.getLastUpdatedOn()).orderDetails(order.getOrderDetails().stream().map(this::convertToDTO).collect(Collectors.toList())).user(convertToDTO(order.getUser())).totalAmount(order.getTotalAmount()).build();
    }

    private OrderDetailDTO convertToDTO(OrderDetail orderDetail) {
        return OrderDetailDTO.builder().id(orderDetail.getId()).product(convertToDTO(orderDetail.getProduct())).quantity(orderDetail.getQuantity()).build();
    }

    private ProductDTO convertToDTO(Product product) {
        return ProductDTO.builder().price(product.getPrice()).name(product.getName()).id(product.getId()).build();
    }

    private UserDTO convertToDTO(User user) {
        return UserDTO.builder().email(user.getEmail()).id(user.getId()).name(user.getName()).build();
    }

    public CustomerOrderDTO getOrder(Long orderId) {
        boolean exists = orderRepository.existsById(orderId);
        if(!exists) {
            throw new IllegalStateException("Order with id: " + orderId + " does not exist.");
        }
        CustomerOrder order = orderRepository.findById(orderId).get();
        return  convertToDTO(order);
    }

    public void addNewOrder(OrderDTO orderDTO) {
        //check user
        Optional<User> user = userRepository.findById(orderDTO.getUserId());
        if(!user.isPresent()) {
            throw new IllegalArgumentException("User not found!");
        }

        CustomerOrder order = new CustomerOrder();
        order.setUser(user.get());
        order.setOrderNo(generateOrderNo());

        List<OrderDetail> orderDetails = new ArrayList<>();
        float orderTotal = 0f;
        int totalQuantity = 0;
        for (ProductDTO product : orderDTO.getProducts()) {
            OrderDetail detail = new OrderDetail();
            detail.setOrder(order);
            detail.setProduct(productRepository.findById(product.getId()).orElseThrow());
            detail.setQuantity(product.getQuantity());
            orderDetails.add(detail);
            orderTotal += product.getPrice() * product.getQuantity();
            totalQuantity += product.getQuantity();
        }
        order.setTotalAmount(orderTotal);
        order.setTotalQuantity(totalQuantity);
        order.setOrderDetails(orderDetails);
        orderRepository.save(order);
    }

    public String generateOrderNo() {
        // Generate a random UUID
        UUID uuid = UUID.randomUUID();
        // Convert it to a string and take the first 8 characters
        String randomOrderNo = uuid.toString().replaceAll("-", "").substring(0, 8).toUpperCase();
        return "ORD-" + randomOrderNo;
    }

    public void delete(Long orderId) {
        boolean exists = orderRepository.existsById(orderId);
        if(!exists) {
            throw new IllegalStateException("Order with id: "+orderId+ " does not exist.");
        }
        orderRepository.deleteById(orderId);
    }
}
