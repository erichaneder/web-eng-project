package at.technikum.webengbackend.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SignInRequestTest {

    @Test
    public void testGetterSetter() {
        SignInRequest signInRequest = new SignInRequest();

        signInRequest.setUsername("john.doe");
        signInRequest.setPassword("securePassword");

        assertEquals("john.doe", signInRequest.getUsername());
        assertEquals("securePassword", signInRequest.getPassword());
    }

    @Test
    public void testNoArgsConstructor() {
        SignInRequest signInRequest = new SignInRequest();
        assertNotNull(signInRequest);
        assertNull(signInRequest.getUsername());
        assertNull(signInRequest.getPassword());
    }

    @Test
    public void testAllArgsConstructor() {
        SignInRequest signInRequest = new SignInRequest("john.doe", "securePassword");

        assertNotNull(signInRequest);
        assertEquals("john.doe", signInRequest.getUsername());
        assertEquals("securePassword", signInRequest.getPassword());
    }

}