package at.technikum.webengbackend.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductDTOTest {

    @Test
    public void testGetterSetter() {
        ProductDTO productDTO = new ProductDTO();

        productDTO.setId(1L);
        productDTO.setName("Product A");
        productDTO.setPrice(29.99f);
        productDTO.setQuantity(50);
        productDTO.setDescription("A wonderful product");
        productDTO.setImage("product_a.jpg");

        assertEquals(1L, productDTO.getId());
        assertEquals("Product A", productDTO.getName());
        assertEquals(29.99f, productDTO.getPrice());
        assertEquals(50, productDTO.getQuantity());
        assertEquals("A wonderful product", productDTO.getDescription());
        assertEquals("product_a.jpg", productDTO.getImage());
    }

    @Test
    public void testNoArgsConstructor() {
        ProductDTO productDTO = new ProductDTO();
        assertNotNull(productDTO);
        assertNull(productDTO.getId());
        assertNull(productDTO.getName());
        assertEquals(0.0f, productDTO.getPrice());
        assertEquals(0, productDTO.getQuantity());
        assertNull(productDTO.getDescription());
        assertNull(productDTO.getImage());
    }

    @Test
    public void testAllArgsConstructor() {
        ProductDTO productDTO = new ProductDTO(
                1L,
                "Product A",
                29.99f,
                50,
                "A wonderful product",
                "product_a.jpg"
        );

        assertNotNull(productDTO);
        assertEquals(1L, productDTO.getId());
        assertEquals("Product A", productDTO.getName());
        assertEquals(29.99f, productDTO.getPrice());
        assertEquals(50, productDTO.getQuantity());
        assertEquals("A wonderful product", productDTO.getDescription());
        assertEquals("product_a.jpg", productDTO.getImage());
    }

}