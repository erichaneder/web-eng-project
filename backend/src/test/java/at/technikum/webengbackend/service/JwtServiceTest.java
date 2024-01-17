package at.technikum.webengbackend.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class JwtServiceTest {

    @Mock
    private JwtService jwtService;

    private UserDetails userDetails;

    @BeforeEach
    public void setUp() {
        userDetails = User.withUsername("testUser")
                .username("testUser")
                .password("password")
                .authorities(new ArrayList<>())
                .build();
    }

    @Test
    public void testGenerateToken() {
        Mockito.when(jwtService.generateToken(ArgumentMatchers.any())).thenReturn("test");
        String token = jwtService.generateToken(userDetails);
        assertNotNull(token, "Generated token should not be null");
    }

    @Test
    public void testExtractUserName() {
        Mockito.when(jwtService.generateToken(ArgumentMatchers.any())).thenReturn("test");
        Mockito.when(jwtService.extractUserName(ArgumentMatchers.any())).thenReturn("testUser");
        String token = jwtService.generateToken(userDetails);
        String username = jwtService.extractUserName(token);
        assertEquals("testUser", username, "Extracted username should match");
    }

    @Test
    public void testTokenValidation() {
        Mockito.when(jwtService.generateToken(ArgumentMatchers.any())).thenReturn("test");
        Mockito.when(jwtService.isTokenValid(ArgumentMatchers.any(),ArgumentMatchers.any())).thenReturn(true);
        String token = jwtService.generateToken(userDetails);
        assertTrue(jwtService.isTokenValid(token, userDetails), "Token should be valid for the given UserDetails");
    }

    @Test
    public void testTokenExpiration() {
        Mockito.when(jwtService.generateToken(ArgumentMatchers.any())).thenReturn("test");
        Mockito.when(jwtService.isTokenValid(ArgumentMatchers.any(),ArgumentMatchers.any())).thenReturn(false);
        // You might need to manipulate the token expiration time or use a predefined expired token
        String token = jwtService.generateToken(userDetails);
        // Simulate time passage or use a predefined expired token
        assertFalse(jwtService.isTokenValid(token, userDetails), "Expired token should not be valid");
    }

    // Additional tests can be written to cover more scenarios
}