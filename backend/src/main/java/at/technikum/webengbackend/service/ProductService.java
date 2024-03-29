package at.technikum.webengbackend.service;

import at.technikum.webengbackend.dto.ProductDTO;
import at.technikum.webengbackend.model.Product;
import at.technikum.webengbackend.repository.ProductRepository;

import io.micrometer.common.util.StringUtils;
import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public Product addNewProduct(ProductDTO request) {
        Product product = Product.builder().name(request.getName()).price(request.getPrice()).image(request.getImage()).description(request.getDescription()).amount(1).build();
        return productRepository.save(product);
    }

    public Product addNewProduct(Product product) {

        return productRepository.save(product);
    }

    public Long deleteProduct(Long productId) {
        boolean exists = productRepository.existsById(productId);
        if(!exists) {
            throw new IllegalStateException("product with id: "+ productId + " does not exist!");
        }
        productRepository.deleteById(productId);
        return productId;
    }

    @Transactional
    public Product updateProduct(Long productId, String name, float price, Integer amount, String description) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new IllegalStateException("product with id "+productId+ "does not exist"));

        if(name != null && !name.isEmpty() && !Objects.equals(product.getName(),name)) {
            product.setName(name);
        }

        if(price > 0 && price < 10000) {
            product.setPrice(price);
        }

        //ToDo: Maximalmenge oder nicht?
        if (amount != null && amount > 0) {
            product.setAmount(amount);
        }

        if(!StringUtils.isEmpty(description)) {
            product.setDescription(description);
        }
        return product;

    }

    // patch
}
