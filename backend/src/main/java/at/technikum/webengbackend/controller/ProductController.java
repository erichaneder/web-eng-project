package at.technikum.webengbackend.controller;

import at.technikum.webengbackend.config.IAllowPath;
import at.technikum.webengbackend.model.Product;
import at.technikum.webengbackend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(path= IAllowPath.PRODUCT_LIST)
    public List<Product> getProducts() {return productService.getProducts();}

    @PostMapping(path=IAllowPath.PRODUCT_ADD)
    public void registerNewProduct(@RequestBody Product product) {
       productService.addNewProduct(product);
    }

    @DeleteMapping(path=IAllowPath.PRODUCT_DELETE)
    public void deleteProduct(@PathVariable("productId") Long productId) {
        productService.deleteProduct(productId);
    }

    @PutMapping(path = IAllowPath.PRODUCT_UPDATE)
    public void updateProduct(@PathVariable("productId") Long productId,@RequestBody Product product) {
        productService.updateProduct(productId,product.getName(),product.getPrice(),product.getAmount());
    }
}
