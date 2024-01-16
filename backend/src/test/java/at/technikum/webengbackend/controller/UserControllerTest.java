package at.technikum.webengbackend.controller;

import at.technikum.webengbackend.dto.SignInRequest;
import at.technikum.webengbackend.dto.SignUpRequest;
import at.technikum.webengbackend.model.User;
import at.technikum.webengbackend.service.AuthenticationService;
import at.technikum.webengbackend.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock
    private UserService userService;

    @Mock
    private AuthenticationService authenticationService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        userController = new UserController(userService, authenticationService);
    }

    @Test
    void getUsersTest() {
        when(userService.getUsers()).thenReturn(Collections.singletonList(new User()));
        ResponseEntity<List<User>> response = userController.getUsers();
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
    }

    @Test
    void getUserTest() {
        Long userId = 1L;
        when(userService.getUser(userId)).thenReturn(new User());
        ResponseEntity<User> response = userController.getUser(userId);
        assertNotNull(response.getBody());
    }

    @Test
    void signupTest() {
        SignUpRequest signUpRequest = new SignUpRequest();
        ResponseEntity<?> response = userController.signup(signUpRequest);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(authenticationService).signup(any(SignUpRequest.class));
    }

    @Test
    void signinTest() {
        SignInRequest signInRequest = new SignInRequest();
        ResponseEntity<?> response = userController.signin(signInRequest);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(authenticationService).signin(any(SignInRequest.class));
    }

    @Test
    void deleteUserTest() {
        Long userId = 1L;
        ResponseEntity<?> response = userController.deleteUser(userId);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(userService).delete(userId);
    }

}