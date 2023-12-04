package at.technikum.webengbackend.config;

import at.technikum.webengbackend.model.*;
import at.technikum.webengbackend.repository.OrderRepository;
import at.technikum.webengbackend.repository.ProductRepository;
import at.technikum.webengbackend.repository.UserRepository;
import at.technikum.webengbackend.service.OrderService;
import at.technikum.webengbackend.service.ProductService;
import at.technikum.webengbackend.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class SeedDataConfig implements CommandLineRunner {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final ProductService prodService;
    private final OrderService orderService;

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.count() == 0) {
            User admin = User
                    .builder()
                    .name("admin")
                    .salutation("Male")
                    .password(passwordEncoder.encode("Password1"))
                    .email("admin@admin.com")
                    .role(Role.ROLE_ADMIN)
                    .phonenumber("06704546232")
                    .address(new Address("Ferdestraße 31","1210","Vienna","Austria"))
                    .build();

            User normal = User
                    .builder()
                    .name("Fredi")
                    .salutation("Male")
                    .password(passwordEncoder.encode("Password1"))
                    .email("fred@fred.com")
                    .role(Role.ROLE_CUSTOMER)
                    .phonenumber("+43704546232")
                    .address(new Address("Fredstrett 1","1220","Vienna","Austria"))
                    .build();

            User random1 = User
                    .builder()
                    .name("Julia Herrmann")
                    .salutation("Female")
                    .password(passwordEncoder.encode("Password1"))
                    .email("julia@gmail.com")
                    .role(Role.ROLE_CUSTOMER)
                    .phonenumber("+4370456499")
                    .address(new Address("Bahnstraße 2","98654","Berlin","Germany"))
                    .build();

            User random2 = User
                    .builder()
                    .name("Hawara")
                    .salutation("Helicopter")
                    .password(passwordEncoder.encode("Password1"))
                    .email("heli@outlook.com")
                    .role(Role.ROLE_CUSTOMER)
                    .phonenumber("+43799546455")
                    .address(new Address("Baumgasse 101","3344","Ferd","Ferdien"))
                    .build();

            User random3 = User
                    .builder()
                    .name("Boboo")
                    .salutation("Male")
                    .password(passwordEncoder.encode("Password1"))
                    .email("bobcok@gmail.com")
                    .role(Role.ROLE_CUSTOMER)
                    .phonenumber("+43704589989")
                    .address(new Address("Wallstreet 10","98797","New York","USA"))
                    .build();

            User[] user = {admin,normal, random1, random2, random3};
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

        if(orderRepository.count()==0) {
            List<CustomerOrder> orders = new ArrayList<>();
            for(int i = 0; i<10; i++) {
                orders.add(CustomerOrder.builder().orderNo("ORDER93287498").totalAmount(612.99f).build());
            }
            orders.stream().forEach(orderService::addNewOrder);
        }
    }
}
