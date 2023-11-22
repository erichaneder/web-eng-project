package at.technikum.webengbackend.controller;

import at.technikum.webengbackend.config.AllowedPaths;
import at.technikum.webengbackend.model.CustomerOrder;
import at.technikum.webengbackend.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {
    private final OrderService orderService;
    @Autowired
    public OrderController(OrderService orderService) { this.orderService = orderService; }
    @GetMapping(path= AllowedPaths.Order.LIST)
    public ResponseEntity<List<CustomerOrder>> getOrders() {
        return ResponseEntity.ok(orderService.getOrders());
    }

    @GetMapping(path= AllowedPaths.Order.GET_ONE)
    public ResponseEntity<CustomerOrder> getOrder(@PathVariable("orderId") Long orderId) {
        return ResponseEntity.ok(orderService.getOrder(orderId));
    }

    @PostMapping(path=AllowedPaths.Order.ADD)
    public ResponseEntity<?> createNewOrder(@RequestBody CustomerOrder order) {
        orderService.addNewOrder(order);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(path=AllowedPaths.Order.DELETE)
    public ResponseEntity<?> deleteOrder(@PathVariable("orderId") Long orderId) {
        orderService.delete(orderId);
        return ResponseEntity.ok().build();
    }
}
