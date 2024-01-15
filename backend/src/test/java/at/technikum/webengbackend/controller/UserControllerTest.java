package at.technikum.webengbackend.controller;

import at.technikum.webengbackend.model.User;
import at.technikum.webengbackend.service.AuthenticationService;
import at.technikum.webengbackend.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class UserControllerTest {
    @Mock
    private UserService userService;
    @Mock
    AuthenticationService authenticationService;

    private UserController userController;


    @BeforeEach
    void setUp() {
        userController = new UserController(userService, authenticationService);
    }

    @Test
    void getUsers() {
        //given
        Mockito.when(userService.getUsers()).thenReturn(buildUserList());
        //when
        ResponseEntity resp = userController.getUsers();
        List<User> list =(List<User>) resp.getBody();
        //then
        assertEquals(1L, list.get(0).getId());
    }

    @Test
    void getUser() {
    }

    @Test
    void signup() {
    }

    @Test
    void signin() {
    }

    @Test
    void deleteUser() {
    }

    @Test
    void updateUser() {
    }

    @Test
    void patchUser() {
    }

    public List<User> buildUserList(){
        User u = new User();
        u.setId(1L);
        List<User> list = new ArrayList<>();
        list.add(u);
        return list;
    }
}