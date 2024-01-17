package at.technikum.webengbackend.service;

import at.technikum.webengbackend.dto.JwtAuthenticationResponse;
import at.technikum.webengbackend.dto.SignInRequest;
import at.technikum.webengbackend.dto.SignUpRequest;
import at.technikum.webengbackend.model.Address;
import at.technikum.webengbackend.model.Role;
import at.technikum.webengbackend.model.User;
import at.technikum.webengbackend.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class AuthenticationServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserService userService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtService jwtService;

    @Mock
    private AuthenticationManager authenticationManager;

    @InjectMocks
    private AuthenticationService authenticationService;

    @BeforeEach
    public void setUp() {
    }

    @Test
    public void testSignup() {
        SignUpRequest signUpRequest = new SignUpRequest();
        signUpRequest.setPassword("Test12345");
        Address a = new Address();
        a.setCountry("AT");
        signUpRequest.setAddress(a);
        signUpRequest.setRole(Role.ROLE_CUSTOMER);


        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");
        when(userService.addNewUser(any(User.class))).thenReturn(new User());
        when(jwtService.generateToken(anyMap(), any(User.class))).thenReturn("mockToken");

        JwtAuthenticationResponse response = authenticationService.signup(signUpRequest);
        assertNotNull(response, "Response should not be null");
        assertEquals("mockToken", response.getToken(), "Token should match the mock token");
    }

    @Test
    public void testSignin() {
        SignInRequest signInRequest = new SignInRequest();
        signInRequest.setUsername("test");

        User mockUser = new User();
        mockUser.setId(1L);
        when(userRepository.findByName(anyString())).thenReturn(Optional.of(mockUser));
        when(jwtService.generateToken(anyMap(), any(User.class))).thenReturn("mockToken");

        JwtAuthenticationResponse response = authenticationService.signin(signInRequest);
        assertNotNull(response, "Response should not be null");
        assertEquals("mockToken", response.getToken(), "Token should match the mock token");
    }

}