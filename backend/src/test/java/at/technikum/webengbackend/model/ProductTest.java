package at.technikum.webengbackend.model;

import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {
    @Test
    public void testGetterSetter() {
        Product product = new Product();

        product.setId(1L);
        product.setName("Product A");
        product.setPrice(29.99f);
        product.setImage("product_a.jpg");
        product.setDescription("A wonderful product");
        product.setAmount(50);
        product.setCreatedOn(Instant.now());
        product.setLastUpdatedOn(Instant.now());

        assertEquals(1L, product.getId());
        assertEquals("Product A", product.getName());
        assertEquals(29.99f, product.getPrice());
        assertEquals("product_a.jpg", product.getImage());
        assertEquals("A wonderful product", product.getDescription());
        assertEquals(50, product.getAmount());
        assertNotNull(product.getCreatedOn());
        assertNotNull(product.getLastUpdatedOn());
    }

    @Test
    public void testNoArgsConstructor() {
        Product product = new Product();
        assertNotNull(product);
        assertNull(product.getId());
        assertNull(product.getName());
        assertEquals(0.0f, product.getPrice());
        assertNull(product.getImage());
        assertNull(product.getDescription());
        assertNull(product.getAmount());
        assertNull(product.getCreatedOn());
        assertNull(product.getLastUpdatedOn());
    }

    @Test
    public void testAllArgsConstructor() {
        Instant now = Instant.now();
        List<OrderDetail> orderDetails = new ArrayList<>();
        orderDetails.add(new OrderDetail(1L,new CustomerOrder(), new Product(), 3));

        Product product = new Product(
                1L,
                "Product A",
                29.99f,
                "product_a.jpg",
                "A wonderful product",
                50,
                now,
                now,
                orderDetails
        );

        assertNotNull(product);
        assertEquals(1L, product.getId());
        assertEquals("Product A", product.getName());
        assertEquals(29.99f, product.getPrice());
        assertEquals("product_a.jpg", product.getImage());
        assertEquals("A wonderful product", product.getDescription());
        assertEquals(50, product.getAmount());
        assertEquals(now, product.getCreatedOn());
        assertEquals(now, product.getLastUpdatedOn());
        assertEquals(orderDetails, product.getOrderDetails());
    }

}