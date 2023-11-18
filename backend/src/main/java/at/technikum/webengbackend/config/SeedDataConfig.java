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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
            List<Product> products = new ArrayList<>();
            for(int i = 0; i<2; i++) {
                products.add(Product.builder().name("Nike Dunk Low Sneakers - Red").price(120).amount(120).image("nike_red2.jpg").description("Stylish, comfortable, and durable.").build());
                products.add(Product.builder().name("Nike Dunk Low Sneakers - Blue").price(120).amount(120).image("dunk_blue1.png").description("Stylish, comfortable, and durable.").build());
                products.add(Product.builder().name("Nike Dunk Low Sneakers - Black").price(120).amount(120).image("dunk_black1.png").description("Stylish, comfortable, and durable.").build());
                products.add(Product.builder().name("Nike Dunk Low Sneakers - Green").price(120).amount(120).image("nike_green.jpg").description("Stylish, comfortable, and durable.").build());
                products.add(Product.builder().name("Nike Dunk Low Sneakers - White").price(120).amount(120).image("dunk_white1.jpg").description("Stylish, comfortable, and durable.").build());
            }
            products.stream().forEach(prodService::addNewProduct);
        }
    }
}
