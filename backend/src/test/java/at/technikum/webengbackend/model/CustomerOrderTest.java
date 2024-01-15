package at.technikum.webengbackend.model;

import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CustomerOrderTest {
    @Test
    public void testGetterSetter() {

        CustomerOrder customerOrder = new CustomerOrder();

        customerOrder.setId(1L);
        customerOrder.setOrderNo("ORD123");
        customerOrder.setTotalAmount(99.99f);
        customerOrder.setTotalQuantity(4);
        customerOrder.setCreatedOn(Instant.now());
        customerOrder.setLastUpdatedOn(Instant.now());
        customerOrder.setUser(new User());
        List<OrderDetail> orderDetails = new ArrayList<>();
        orderDetails.add(new OrderDetail());
        customerOrder.setOrderDetails(orderDetails);

        assertEquals(1L, customerOrder.getId());
        assertEquals("ORD123", customerOrder.getOrderNo());
        assertEquals(99.99f, customerOrder.getTotalAmount());
        assertEquals(4, customerOrder.getTotalQuantity());
        assertNotNull(customerOrder.getCreatedOn());
        assertNotNull(customerOrder.getLastUpdatedOn());
        assertNotNull(customerOrder.getUser());
        assertEquals(orderDetails, customerOrder.getOrderDetails());
    }

    @Test
    public void testNoArgsConstructor() {
        CustomerOrder customerOrder = new CustomerOrder();
        assertNotNull(customerOrder);
        assertNull(customerOrder.getId());
        assertNull(customerOrder.getOrderNo());
        assertEquals(0.0f, customerOrder.getTotalAmount());
        assertEquals(0, customerOrder.getTotalQuantity());
        assertNull(customerOrder.getCreatedOn());
        assertNull(customerOrder.getLastUpdatedOn());
        assertNull(customerOrder.getUser());
        assertNull(customerOrder.getOrderDetails());
    }

    @Test
    public void testAllArgsConstructor() {
        Instant now = Instant.now();
        User user = new User();
        List<OrderDetail> orderDetails = new ArrayList<>();
        orderDetails.add(new OrderDetail());

        CustomerOrder customerOrder = new CustomerOrder(
                1L,
                "ORD123",
                99.99f,
                4,
                now,
                now,
                user,
                orderDetails
        );

        assertNotNull(customerOrder);
        assertEquals(1L, customerOrder.getId());
        assertEquals("ORD123", customerOrder.getOrderNo());
        assertEquals(99.99f, customerOrder.getTotalAmount());
        assertEquals(4, customerOrder.getTotalQuantity());
        assertEquals(now, customerOrder.getCreatedOn());
        assertEquals(now, customerOrder.getLastUpdatedOn());
        assertEquals(user, customerOrder.getUser());
        assertEquals(orderDetails, customerOrder.getOrderDetails());
    }

}