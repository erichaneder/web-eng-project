package at.technikum.webengbackend.controller;

import at.technikum.webengbackend.dto.ProductDTO;
import at.technikum.webengbackend.model.Product;
import at.technikum.webengbackend.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    @BeforeEach
    void setUp() {
        productController = new ProductController(productService);
    }

    @Test
    void getProductsTest() {
        when(productService.getProducts()).thenReturn(Collections.singletonList(new Product()));
        ResponseEntity<List<Product>> response = productController.getProducts();
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
    }

    @Test
    void registerNewProductTest() {
        ProductDTO productDTO = new ProductDTO();
        ResponseEntity<?> response = productController.registerNewProduct(productDTO);
        assertEquals(201, response.getStatusCodeValue());
        verify(productService).addNewProduct(any(ProductDTO.class));
    }

    @Test
    void deleteProductTest() {
        Long productId = 1L;
        ResponseEntity<?> response = productController.deleteProduct(productId);
        assertEquals(204, response.getStatusCodeValue());
        verify(productService).deleteProduct(productId);
    }

    /*
    @Test
    void updateProductTest() {
        Long productId = 1L;
        Product product = new Product();
        ResponseEntity<?> response = productController.updateProduct(productId, product);
        assertEquals(200, response.getStatusCodeValue());
        verify(productService).updateProduct(eq(productId), anyString(), anyInt(), anyInt(), anyString());
    }

   */
}