package at.technikum.webengbackend.dto;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderDTOTest {
    @Test
    public void testGetterSetter() {
        OrderDTO orderDTO = new OrderDTO();

        orderDTO.setUserId(1L);
        List<ProductDTO> products = new ArrayList<>();
        products.add(new ProductDTO(1L, "Product A", 29.99f, 2, "Description A", "image_a.jpg"));
        products.add(new ProductDTO(2L, "Product B", 39.99f, 1, "Description B", "image_b.jpg"));
        orderDTO.setProducts(products);

        assertEquals(1L, orderDTO.getUserId());
        assertEquals(products, orderDTO.getProducts());
    }

    @Test
    public void testNoArgsConstructor() {
        OrderDTO orderDTO = new OrderDTO();
        assertNotNull(orderDTO);
        assertNull(orderDTO.getUserId());
        assertNull(orderDTO.getProducts());
    }

    @Test
    public void testAllArgsConstructor() {
        List<ProductDTO> products = new ArrayList<>();
        products.add(new ProductDTO(1L, "Product A", 29.99f, 2, "Description A", "image_a.jpg"));
        products.add(new ProductDTO(2L, "Product B", 39.99f, 1, "Description B", "image_b.jpg"));

        OrderDTO orderDTO = new OrderDTO(1L, products);

        assertNotNull(orderDTO);
        assertEquals(1L, orderDTO.getUserId());
        assertEquals(products, orderDTO.getProducts());
    }


}