package at.technikum.webengbackend.dto;

import at.technikum.webengbackend.model.Address;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import at.technikum.webengbackend.model.Role;

class SignUpRequestTest {
    @Test
    public void testGetterSetter() {
        SignUpRequest signUpRequest = new SignUpRequest();

        signUpRequest.setName("John Doe");
        signUpRequest.setSalutation("Mr");
        signUpRequest.setPassword("securePassword");
        signUpRequest.setEmail("john.doe@example.com");
        signUpRequest.setRole(Role.ROLE_ADMIN);
        signUpRequest.setPhonenumber("123456789");
        signUpRequest.setAddress(new Address("Street", "City", "12345","aut"));

        assertEquals("John Doe", signUpRequest.getName());
        assertEquals("Mr", signUpRequest.getSalutation());
        assertEquals("securePassword", signUpRequest.getPassword());
        assertEquals("john.doe@example.com", signUpRequest.getEmail());
        assertEquals(Role.ROLE_ADMIN, signUpRequest.getRole());
        assertEquals("123456789", signUpRequest.getPhonenumber());
        assertEquals(new Address("Street", "City", "12345","aut"), signUpRequest.getAddress());
    }

    @Test
    public void testNoArgsConstructor() {
        SignUpRequest signUpRequest = new SignUpRequest();
        assertNotNull(signUpRequest);
        assertNull(signUpRequest.getName());
        assertNull(signUpRequest.getSalutation());
        assertNull(signUpRequest.getPassword());
        assertNull(signUpRequest.getEmail());
        assertNull(signUpRequest.getRole());
        assertNull(signUpRequest.getPhonenumber());
        assertNull(signUpRequest.getAddress());
    }

    @Test
    public void testAllArgsConstructor() {
        SignUpRequest signUpRequest = new SignUpRequest(
                "John Doe",
                "Mr",
                "securePassword",
                "john.doe@example.com",
                Role.ROLE_ADMIN,
                "123456789",
                new Address("Street", "City", "12345","aut")
        );

        assertNotNull(signUpRequest);
        assertEquals("John Doe", signUpRequest.getName());
        assertEquals("Mr", signUpRequest.getSalutation());
        assertEquals("securePassword", signUpRequest.getPassword());
        assertEquals("john.doe@example.com", signUpRequest.getEmail());
        assertEquals(Role.ROLE_ADMIN, signUpRequest.getRole());
        assertEquals("123456789", signUpRequest.getPhonenumber());
        assertEquals(new Address("Street", "City", "12345","aut"), signUpRequest.getAddress());
    }

}