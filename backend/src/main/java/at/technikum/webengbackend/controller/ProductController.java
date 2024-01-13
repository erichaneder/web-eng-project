package at.technikum.webengbackend.controller;

import at.technikum.webengbackend.config.AllowedPaths;
import at.technikum.webengbackend.dto.ProductDTO;
import at.technikum.webengbackend.model.Product;
import at.technikum.webengbackend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(path= AllowedPaths.Product.LIST)
    @PreAuthorize("isAnonymous()")
    public ResponseEntity<List<Product>> getProducts() {
        try {
            return ResponseEntity.ok(productService.getProducts());
        } catch (Exception e) {
            throw new IllegalArgumentException("Fehler beim Aufrufen der Produkte: " + e.getMessage());
        }
    }

    @PostMapping(path=AllowedPaths.Product.ADD)
    public ResponseEntity<?> registerNewProduct(@RequestBody ProductDTO product) {
        try {
            productService.addNewProduct(product);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            throw new IllegalArgumentException("Fehler beim Anlegen eines neuen Produktes: " + e.getMessage());
        }
    }

    @DeleteMapping(path=AllowedPaths.Product.DELETE)
    public ResponseEntity<?> deleteProduct(@PathVariable("productId") Long productId) {
        try {
            productService.deleteProduct(productId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            throw new IllegalArgumentException("Fehler beim Löschen eines Produktes: " + e.getMessage());
        }
    }

    @PutMapping(path = AllowedPaths.Product.UPDATE)
    public ResponseEntity<?> updateProduct(@PathVariable("productId") Long productId,@RequestBody Product product) {
        try {
            productService.updateProduct(productId,product.getName(),product.getPrice(),product.getAmount(), product.getDescription());
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            throw new IllegalArgumentException("Fehler beim Ändern des Produktes: " + e.getMessage());
        }
    }
}
