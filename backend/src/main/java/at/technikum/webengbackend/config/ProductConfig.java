package at.technikum.webengbackend.config;

import at.technikum.webengbackend.model.Product;
import at.technikum.webengbackend.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ProductConfig {
    @Bean
    CommandLineRunner commandLineRunner(ProductRepository productRepository) {
        return args -> {
            Product prod = new Product(1L, "fett", 3, 2);
            productRepository.saveAll(List.of(prod));
        };

    }
}
