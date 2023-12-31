package at.technikum.webengbackend.config;

import at.technikum.webengbackend.dto.OrderDTO;
import at.technikum.webengbackend.dto.ProductDTO;
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
import java.util.stream.Collectors;

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
                    .address(new Address("Ferdestraße 31","1210","Vienna","AT"))
                    .build();

            User normal = User
                    .builder()
                    .name("Fredi")
                    .salutation("Male")
                    .password(passwordEncoder.encode("Password1"))
                    .email("fred@fred.com")
                    .role(Role.ROLE_CUSTOMER)
                    .phonenumber("+43704546232")
                    .address(new Address("Fredstrett 1","1220","Vienna","AT"))
                    .build();

            User random1 = User
                    .builder()
                    .name("Julia Herrmann")
                    .salutation("Female")
                    .password(passwordEncoder.encode("Password1"))
                    .email("julia@gmail.com")
                    .role(Role.ROLE_CUSTOMER)
                    .phonenumber("+4370456499")
                    .address(new Address("Bahnstraße 2","98654","Berlin","DE"))
                    .build();

            User random2 = User
                    .builder()
                    .name("Hawara")
                    .salutation("Helicopter")
                    .password(passwordEncoder.encode("Password1"))
                    .email("heli@outlook.com")
                    .role(Role.ROLE_CUSTOMER)
                    .phonenumber("+43799546455")
                    .address(new Address("Baumgasse 101","3344","Ferd","FR"))
                    .build();

            User random3 = User
                    .builder()
                    .name("Boboo")
                    .salutation("Male")
                    .password(passwordEncoder.encode("Password1"))
                    .email("bobcok@gmail.com")
                    .role(Role.ROLE_CUSTOMER)
                    .phonenumber("+43704589989")
                    .address(new Address("Wallstreet 10","98797","New York","US"))
                    .build();

            User[] user = {admin,normal, random1, random2, random3};
            Arrays.stream(user).forEach(userService::addNewUser);
        }
        if(productRepository.count()==0) {
            List<Product> products = new ArrayList<>();
            products.add(Product.builder().name("Nike Dunk Low Sneakers - Olive").price(129.99f).amount(120).image("green1.jpg").description("Stylish, comfortable and durable.").build());
            products.add(Product.builder().name("Nike Dunk Low Sneakers - Blue").price(119.99f).amount(120).image("blue1.jpg").description("Stylish, comfortable and durable.").build());
            products.add(Product.builder().name("Nike Dunk Low Sneakers - Retro").price(165.99f).amount(120).image("retro.jpg").description("Stylish, comfortable and durable.").build());
            products.add(Product.builder().name("Nike Dunk Low Sneakers - Pink").price(119.99f).amount(120).image("pink1.jpg").description("Stylish, comfortable and durable.").build());
            products.add(Product.builder().name("Nike Dunk Low Sneakers - Purple").price(139.99f).amount(120).image("purple1.jpg").description("Stylish, comfortable and durable.").build());
            products.add(Product.builder().name("Nike Dunk Low Sneakers - Gold").price(199.99f).amount(120).image("gold1.jpg").description("Stylish, comfortable and durable.").build());
            products.add(Product.builder().name("Nike Dunk Low Sneakers - Grey").price(119.99f).amount(120).image("grey1.jpg").description("Stylish, comfortable and durable.").build());
            products.add(Product.builder().name("Nike Dunk Low Sneakers - Peach").price(119.99f).amount(120).image("orange.jpg").description("Stylish, comfortable and durable.").build());
            products.add(Product.builder().name("Nike Dunk Low Sneakers - White").price(119.99f).amount(120).image("white.jpg").description("Stylish, comfortable and durable.").build());
            products.add(Product.builder().name("Nike Dunk Low Sneakers - Midnight Navy").price(129.99f).amount(120).image("midnight_navy.jpg").description("Stylish, comfortable and durable.").build());
            products.add(Product.builder().name("Nike Dunk Low Sneakers - Indigo Haze").price(119.99f).amount(120).image("indigo_haze.jpg").description("Stylish, comfortable and durable.").build());
            products.add(Product.builder().name("Nike Dunk Low Sneakers - Deep Royal Blue").price(199.99f).amount(120).image("royal_blue.jpg").description("Stylish, comfortable and durable.").build());
            products.stream().forEach(prodService::addNewProduct);
        }

        // Fetch real products from the database
        List<Product> realProducts = productRepository.findAll();
        List<ProductDTO> productDTOs = realProducts.stream().map(product ->
                ProductDTO.builder()
                        .id(product.getId())
                        .quantity(2) // or any other logic to set quantity
                        .price(119.99f)
                        .build()
        ).collect(Collectors.toList());

        // Ensure there are some products to add to orders
        if (!productDTOs.isEmpty() && orderRepository.count() == 0) {
            List<OrderDTO> orderDTOs = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                OrderDTO orderDTO = OrderDTO.builder()
                        .userId(i % 2 == 0 ? 1L : 2L) // Make sure these user IDs exist
                        .products(productDTOs)
                        .build();
                orderDTOs.add(orderDTO);
            }

            // Save each OrderDTO
            orderDTOs.forEach(orderService::addNewOrder);
        }
    }
}
