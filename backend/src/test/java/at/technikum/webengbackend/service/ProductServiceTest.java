package at.technikum.webengbackend.service;

import at.technikum.webengbackend.model.Product;
import at.technikum.webengbackend.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    private ProductService productService;

    @BeforeEach
    void setUp() {
        productService = new ProductService(productRepository);
    }

    @Test
    void getProducts() {
        //given
        Mockito.when(productRepository.findAll()).thenReturn(buildProductList());
        //when
        List<Product> list = productService.getProducts();
        //then
        assertEquals(1L,list.get(0).getId());
    }

    @Test
    void addNewProduct() {
        //given
        Mockito.when(productRepository.save(ArgumentMatchers.any())).thenReturn(buildProduct());
        //when
        Product p = productService.addNewProduct(new Product());
        //then
        assertNotNull(p);

    }

    @Test
    void testAddNewProduct() {

    }

    @Test
    void deleteProduct() {
        //given
        Mockito.when(productRepository.existsById(ArgumentMatchers.anyLong())).thenReturn(true);
        //when
        Long id = productService.deleteProduct(1L);
        //then
        assertEquals(1L,id);


    }

    @Test
    void updateProduct() {
        //given
        Mockito.when(productRepository.findById(ArgumentMatchers.anyLong())).thenReturn(buildOptional());
        //when
        Product p = productService.updateProduct(1L,"test",10,5,"testDesc");
        //then
        assertEquals(1L,p.getId());
    }

    public List<Product> buildProductList(){
        Product p1 = new Product();
        p1.setId(1L);
        ArrayList<Product> list = new ArrayList<>();
        list.add(p1);
        return list;
    }

    public Product buildProduct(){
        Product p1 = new Product();
        p1.setId(1L);
        return p1;
    }

    public Optional<Product> buildOptional(){
        Product p = buildProduct();
        return Optional.of(p);
    }
}