package at.technikum.webengbackend.service;

import at.technikum.webengbackend.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    public List<Product> getProducts() {
        return List.of(new Product(1L,"kebab",3f, 1));
    }

}
