package at.technikum.webengbackend.config;

import at.technikum.webengbackend.model.Address;
import at.technikum.webengbackend.model.Product;
import at.technikum.webengbackend.model.Role;
import at.technikum.webengbackend.model.User;
import at.technikum.webengbackend.repository.ProductRepository;
import at.technikum.webengbackend.repository.UserRepository;
import at.technikum.webengbackend.service.ProductService;
import at.technikum.webengbackend.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

@Component
@RequiredArgsConstructor
@Slf4j
public class SeedDataConfig implements CommandLineRunner {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final ProductService prodService;

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.count() == 0) {
            User admin = User
                    .builder()
                    .name("admin")
                    .password(passwordEncoder.encode("password"))
                    .email("admin@admin.com")
                    .role(Role.ROLE_ADMIN)
                    .phonenumber("1234")
                    .address(new Address("ferdestraße 31","1210","vienna","vienna"))
                    .build();

            User normal = User
                    .builder()
                    .name("Fred")
                    .password(passwordEncoder.encode("fred123"))
                    .email("fred@fred.com")
                    .role(Role.ROLE_CUSTOMER)
                    .phonenumber("fred123")
                    .address(new Address("fredstrett 1","1220","vienna","vienna"))
                    .build();

            User[] user = {admin,normal};
            Arrays.stream(user).forEach(userService::addNewUser);
        }
        if(productRepository.count()==0) {
            Product prod1 = Product.builder().name("product1").price(3).amount(2).build();
            Product prod2 = Product.builder().name("product2").price(33).amount(22).build();

            Product[] prod = {prod1,prod2};
            Arrays.stream(prod).forEach(prodService::addNewProduct);


        }
    }
}
