package at.technikum.webengbackend.service;

import at.technikum.webengbackend.model.CustomerOrder;
import at.technikum.webengbackend.repository.OrderRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) { this.orderRepository = orderRepository; }

    public List<CustomerOrder> getOrders() { return orderRepository.findAll(); }

    public CustomerOrder getOrder(Long orderId) {
        boolean exists = orderRepository.existsById(orderId);
        if(!exists) {
            throw new IllegalStateException("Order with id: " + orderId + " does not exist.");
        }
        return orderRepository.findById(orderId).get();
    }

    public void addNewOrder(CustomerOrder order) {orderRepository.save(order);}

    public void delete(Long orderId) {
        boolean exists = orderRepository.existsById(orderId);
        if(!exists) {
            throw new IllegalStateException("Order with id: "+orderId+ " does not exist.");
        }
        orderRepository.deleteById(orderId);
    }
}
