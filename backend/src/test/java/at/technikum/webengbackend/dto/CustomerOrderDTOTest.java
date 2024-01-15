package at.technikum.webengbackend.dto;

import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CustomerOrderDTOTest {
    @Test
    public void testGetterSetter() {
        CustomerOrderDTO customerOrderDTO = new CustomerOrderDTO();

        customerOrderDTO.setId(1L);
        customerOrderDTO.setOrderNo("ORD123");
        customerOrderDTO.setTotalAmount(99.99f);
        customerOrderDTO.setCreatedOn(Instant.now());
        customerOrderDTO.setLastUpdatedOn(Instant.now());
        customerOrderDTO.setUser(new UserDTO(1L, "test@example.com", "John Doe"));
        List<OrderDetailDTO> orderDetails = new ArrayList<>();
        orderDetails.add(new OrderDetailDTO(1L, new ProductDTO(1L, "Product A", 29.99f, 2, "Description A", "image_a.jpg"), 3));
        orderDetails.add(new OrderDetailDTO(2L, new ProductDTO(2L, "Product B", 39.99f, 1, "Description B", "image_b.jpg"), 1));
        customerOrderDTO.setOrderDetails(orderDetails);
        customerOrderDTO.setTotalQuantity(4);

        assertEquals(1L, customerOrderDTO.getId());
        assertEquals("ORD123", customerOrderDTO.getOrderNo());
        assertEquals(99.99f, customerOrderDTO.getTotalAmount());
        assertNotNull(customerOrderDTO.getCreatedOn());
        assertNotNull(customerOrderDTO.getLastUpdatedOn());
        assertEquals(new UserDTO(1L, "test@example.com", "John Doe"), customerOrderDTO.getUser());
        assertEquals(orderDetails, customerOrderDTO.getOrderDetails());
        assertEquals(4, customerOrderDTO.getTotalQuantity());
    }

    @Test
    public void testNoArgsConstructor() {
        CustomerOrderDTO customerOrderDTO = new CustomerOrderDTO();
        assertNotNull(customerOrderDTO);
        assertNull(customerOrderDTO.getId());
        assertNull(customerOrderDTO.getOrderNo());
        assertEquals(0.0f, customerOrderDTO.getTotalAmount());
        assertNull(customerOrderDTO.getCreatedOn());
        assertNull(customerOrderDTO.getLastUpdatedOn());
        assertNull(customerOrderDTO.getUser());
        assertNull(customerOrderDTO.getOrderDetails());
        assertEquals(0, customerOrderDTO.getTotalQuantity());
    }

    @Test
    public void testAllArgsConstructor() {
        Instant now = Instant.now();
        List<OrderDetailDTO> orderDetails = new ArrayList<>();
        orderDetails.add(new OrderDetailDTO(1L, new ProductDTO(1L, "Product A", 29.99f, 2, "Description A", "image_a.jpg"), 3));
        orderDetails.add(new OrderDetailDTO(2L, new ProductDTO(2L, "Product B", 39.99f, 1, "Description B", "image_b.jpg"), 1));

        CustomerOrderDTO customerOrderDTO = new CustomerOrderDTO(
                1L,
                "ORD123",
                99.99f,
                now,
                now,
                new UserDTO(1L, "test@example.com", "John Doe"),
                orderDetails,
                4
        );

        assertNotNull(customerOrderDTO);
        assertEquals(1L, customerOrderDTO.getId());
        assertEquals("ORD123", customerOrderDTO.getOrderNo());
        assertEquals(99.99f, customerOrderDTO.getTotalAmount());
        assertEquals(now, customerOrderDTO.getCreatedOn());
        assertEquals(now, customerOrderDTO.getLastUpdatedOn());
        assertEquals(new UserDTO(1L, "test@example.com", "John Doe"), customerOrderDTO.getUser());
        assertEquals(orderDetails, customerOrderDTO.getOrderDetails());
        assertEquals(4, customerOrderDTO.getTotalQuantity());
    }

}