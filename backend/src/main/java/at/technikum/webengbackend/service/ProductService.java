package at.technikum.webengbackend.service;

import at.technikum.webengbackend.model.Product;
import at.technikum.webengbackend.repository.ProductRepository;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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

    public void addNewProduct(Product product) {
        // Uploaddate must be set here
        product.setUpload_date(new Date());
        productRepository.save(product);
    }

    public void deleteProduct(Long productId) {
        boolean exists = productRepository.existsById(productId);
        if(!exists) {
            throw new IllegalStateException("product with id: "+ productId + " does not exist!");
        }
        productRepository.deleteById(productId);
    }

    @Transactional
    public void updateProduct(Long productId, String name, float price, Integer amount) {
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

    }
}
