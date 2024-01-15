package at.technikum.webengbackend.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderDetailTest {

    @Test
    public void testGetterSetter() {
        OrderDetail orderDetail = new OrderDetail();

        orderDetail.setId(1L);
        orderDetail.setOrder(new CustomerOrder());
        orderDetail.setProduct(new Product());
        orderDetail.setQuantity(3);

        assertEquals(1L, orderDetail.getId());
        assertNotNull(orderDetail.getOrder());
        assertNotNull(orderDetail.getProduct());
        assertEquals(3, orderDetail.getQuantity());
    }

    @Test
    public void testNoArgsConstructor() {
        OrderDetail orderDetail = new OrderDetail();
        assertNotNull(orderDetail);
        assertNull(orderDetail.getId());
        assertNull(orderDetail.getOrder());
        assertNull(orderDetail.getProduct());
        assertNull(orderDetail.getQuantity());
    }

    @Test
    public void testAllArgsConstructor() {
        CustomerOrder customerOrder = new CustomerOrder();
        Product product = new Product();

        OrderDetail orderDetail = new OrderDetail(1L, customerOrder, product, 3);

        assertNotNull(orderDetail);
        assertEquals(1L, orderDetail.getId());
        assertEquals(customerOrder, orderDetail.getOrder());
        assertEquals(product, orderDetail.getProduct());
        assertEquals(3, orderDetail.getQuantity());
    }

}