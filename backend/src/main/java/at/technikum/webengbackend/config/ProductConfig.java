package at.technikum.webengbackend.config;


import at.technikum.webengbackend.model.Address;
import at.technikum.webengbackend.model.Product;
import at.technikum.webengbackend.model.User;
import at.technikum.webengbackend.repository.ProductRepository;
import at.technikum.webengbackend.repository.UserRepository;
import at.technikum.webengbackend.util.Role;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ProductConfig {
    // Temporary
    @Bean
    CommandLineRunner commandLineRunnerProd(ProductRepository productRepository) {
        return args -> {
            Product prod1 = new Product("product1", 3, 2);
            Product prod2 = new Product("product2", 33, 22);
            productRepository.saveAll(List.of(prod1,prod2));
        };

    }

    @Bean
    CommandLineRunner commandLineRunnerUser(UserRepository userRepository) {
        return args -> {
            User user1 = new User("Fred","fred123","fredvanvleet@mail.com", Role.customer,"+43456151",
                                  new Address("street 1","1200","Vienna","Vienna"));
            User user2 = new User("Gary","gary123","garytrentjr@mail.com", Role.admin,"+434256151",
                                  new Address("street 2","1200","Vienna","Vienna"));
            userRepository.saveAll(List.of(user1,user2));
        };

    }

}
