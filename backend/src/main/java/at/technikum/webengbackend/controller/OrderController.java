package at.technikum.webengbackend.controller;

import at.technikum.webengbackend.config.AllowedPaths;
import at.technikum.webengbackend.dto.CustomerOrderDTO;
import at.technikum.webengbackend.dto.OrderDTO;
import at.technikum.webengbackend.model.CustomerOrder;
import at.technikum.webengbackend.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@PreAuthorize("hasRole('ROLE_CUSTOMER') OR hasRole('ROLE_ADMIN')")
public class OrderController {
    private final OrderService orderService;
    @Autowired
    public OrderController(OrderService orderService) { this.orderService = orderService; }

    @GetMapping(path= AllowedPaths.Order.LIST)
    public ResponseEntity<List<CustomerOrderDTO>> getOrders(){
        try {
            return ResponseEntity.ok(orderService.getOrders());
        } catch (Exception e) {
            throw new IllegalArgumentException("Fehler beim Lesen der Bestellungen: " + e.getMessage());
        }
    }

    @PreAuthorize("(hasRole('ROLE_CUSTOMER') and #userId == authentication.principal.id) OR hasRole('ROLE_ADMIN') ")
    @GetMapping(path= AllowedPaths.Order.GET_ONE)
    public ResponseEntity<CustomerOrderDTO> getOrder(@PathVariable("orderId") Long orderId) {
        try {
            return ResponseEntity.ok(orderService.getOrder(orderId));
        } catch (Exception e) {
            throw new IllegalArgumentException("Fehler beim Lesen der Bestellung: " + e.getMessage());
        }
    }

    @PostMapping(path=AllowedPaths.Order.ADD)
    public ResponseEntity<?> createNewOrder(@RequestBody OrderDTO order) {
        try {
            orderService.addNewOrder(order);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            throw new IllegalArgumentException("Fehler beim Erstellen der Bestellung: " + e.getMessage());
        }
    }

    @DeleteMapping(path=AllowedPaths.Order.DELETE)
    public ResponseEntity<?> deleteOrder(@PathVariable("orderId") Long orderId) {
        try {
            orderService.delete(orderId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            throw new IllegalArgumentException("Fehler beim Löschen der Bestellung: " + e.getMessage());
        }
    }
}
