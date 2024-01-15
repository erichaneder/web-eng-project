package at.technikum.webengbackend.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AddressTest {
    @Test
    public void testGetterSetter() {
        Address address = new Address();

        address.setStreet("Main Street");
        address.setZipcode("12345");
        address.setCity("Cityville");
        address.setCountry("Wonderland");

        assertEquals("Main Street", address.getStreet());
        assertEquals("12345", address.getZipcode());
        assertEquals("Cityville", address.getCity());
        assertEquals("Wonderland", address.getCountry());
    }

    @Test
    public void testNoArgsConstructor() {
        Address address = new Address();
        assertNotNull(address);
        assertNull(address.getStreet());
        assertNull(address.getZipcode());
        assertNull(address.getCity());
        assertNull(address.getCountry());
    }

    @Test
    public void testAllArgsConstructor() {
        Address address = new Address("Main Street", "12345", "Cityville", "Wonderland");

        assertNotNull(address);
        assertEquals("Main Street", address.getStreet());
        assertEquals("12345", address.getZipcode());
        assertEquals("Cityville", address.getCity());
        assertEquals("Wonderland", address.getCountry());
    }

}