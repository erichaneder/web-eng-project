package at.technikum.webengbackend.service;

import at.technikum.webengbackend.model.User;
import at.technikum.webengbackend.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    private UserService userService;

    @BeforeEach
    void setUp() {
        userService = new UserService(userRepository);
    }

    @Test
    void getUsers() {
        // given
        User user = new User(); //
        Mockito.when(userRepository.findAll()).thenReturn(Collections.singletonList(user));

        // when
        List<User> users = userService.getUsers();

        // then
        assertFalse(users.isEmpty());
        assertEquals(user, users.get(0));
    }

    @Test
    void getUser() {
    }

    public List<User> buildUserList(){
        User u = new User();
        u.setId(1L);
        List<User> list = new ArrayList<>();
        list.add(u);
        return list;
    }

}