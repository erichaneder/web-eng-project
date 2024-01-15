package at.technikum.webengbackend.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderDetailDTOTest {
    @Test
    public void testGetterSetter() {
        OrderDetailDTO orderDetailDTO = new OrderDetailDTO();

        orderDetailDTO.setId(1L);
        orderDetailDTO.setProduct(new ProductDTO(1L, "Product A", 29.99f, 2, "Description A", "image_a.jpg"));
        orderDetailDTO.setQuantity(3);

        assertEquals(1L, orderDetailDTO.getId());
        assertEquals(new ProductDTO(1L, "Product A", 29.99f, 2, "Description A", "image_a.jpg"), orderDetailDTO.getProduct());
        assertEquals(3, orderDetailDTO.getQuantity());
    }

    @Test
    public void testNoArgsConstructor() {
        OrderDetailDTO orderDetailDTO = new OrderDetailDTO();
        assertNotNull(orderDetailDTO);
        assertNull(orderDetailDTO.getId());
        assertNull(orderDetailDTO.getProduct());
        assertNull(orderDetailDTO.getQuantity());
    }

    @Test
    public void testAllArgsConstructor() {
        OrderDetailDTO orderDetailDTO = new OrderDetailDTO(1L, new ProductDTO(1L, "Product A", 29.99f, 2, "Description A", "image_a.jpg"), 3);

        assertNotNull(orderDetailDTO);
        assertEquals(1L, orderDetailDTO.getId());
        assertEquals(new ProductDTO(1L, "Product A", 29.99f, 2, "Description A", "image_a.jpg"), orderDetailDTO.getProduct());
        assertEquals(3, orderDetailDTO.getQuantity());
    }

}