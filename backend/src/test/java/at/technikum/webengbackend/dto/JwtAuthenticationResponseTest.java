package at.technikum.webengbackend.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JwtAuthenticationResponseTest {

    @Test
    public void testGetterSetter() {
        JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();

        jwtAuthenticationResponse.setToken("exampleToken");

        assertEquals("exampleToken", jwtAuthenticationResponse.getToken());
    }

    @Test
    public void testNoArgsConstructor() {
        JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
        assertNotNull(jwtAuthenticationResponse);
        assertNull(jwtAuthenticationResponse.getToken());
    }

    @Test
    public void testAllArgsConstructor() {
        JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse("exampleToken");

        assertNotNull(jwtAuthenticationResponse);
        assertEquals("exampleToken", jwtAuthenticationResponse.getToken());
    }

}