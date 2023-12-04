package at.technikum.webengbackend.controller;

import at.technikum.webengbackend.config.AllowedPaths;
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
    public ResponseEntity<List<Product>> getProducts() {return ResponseEntity.ok(productService.getProducts());}

    @PostMapping(path=AllowedPaths.Product.ADD)
    public ResponseEntity<?> registerNewProduct(@RequestBody Product product) {
       productService.addNewProduct(product);
       return ResponseEntity.ok().build();
    }

    @DeleteMapping(path=AllowedPaths.Product.DELETE)
    public ResponseEntity<?> deleteProduct(@PathVariable("productId") Long productId) {
        productService.deleteProduct(productId);
        return ResponseEntity.ok().build();
    }

    @PutMapping(path = AllowedPaths.Product.UPDATE)
    public ResponseEntity<?> updateProduct(@PathVariable("productId") Long productId,@RequestBody Product product) {
        productService.updateProduct(productId,product.getName(),product.getPrice(),product.getAmount());
        return ResponseEntity.ok().build();
    }
}
