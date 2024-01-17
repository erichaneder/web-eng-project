package at.technikum.webengbackend.service;

import at.technikum.webengbackend.model.User;
import at.technikum.webengbackend.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    private UserService userService;

    @BeforeEach
    public void setUp() {
       userService = new UserService(userRepository);
    }

    @Test
    public void testGetUsers() {
        when(userRepository.findAll()).thenReturn(List.of(new User()));
        List<User> users = userService.getUsers();
        assertNotNull(users, "Users list should not be null");
        assertFalse(users.isEmpty(), "Users list should not be empty");
    }

    @Test
    public void testGetUser() {
        Long userId = 1L;
        when(userRepository.existsById(userId)).thenReturn(true);
        when(userRepository.findById(userId)).thenReturn(Optional.of(new User()));

        User user = userService.getUser(userId);
        assertNotNull(user, "User should not be null");
    }

    @Test
    public void testAddNewUser() {
        User user = new User();
        userService.addNewUser(user);
        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void testDeleteUser() {
        Long userId = 1L;
        when(userRepository.existsById(userId)).thenReturn(true);
        userService.delete(userId);
        verify(userRepository, times(1)).deleteById(userId);
    }

    @Test
    public void testUpdateUserData() {
        Long userId = 1L;
        User existingUser = new User();
        when(userRepository.findById(userId)).thenReturn(Optional.of(existingUser));
        when(userRepository.save(any(User.class))).thenReturn(existingUser);

        User updatedUser = userService.updateUserData(userId, "newName", "salutation", "password", "email", null, null);
        assertNotNull(updatedUser, "Updated user should not be null");
        assertEquals("newName", updatedUser.getName(), "User name should be updated");
    }

    public List<User> buildUserList(){
        User u = new User();
        u.setId(1L);
        List<User> list = new ArrayList<>();
        list.add(u);
        return list;
    }

}