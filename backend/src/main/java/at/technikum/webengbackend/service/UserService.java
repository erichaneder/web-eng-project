package at.technikum.webengbackend.service;

import at.technikum.webengbackend.model.Address;
import at.technikum.webengbackend.model.User;
import at.technikum.webengbackend.repository.UserRepository;
import at.technikum.webengbackend.util.Role;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) { this.userRepository = userRepository; }
    
    public List<User> getUser() { return userRepository.findAll(); }
    
    public void addNewUser(User user) { userRepository.save(user); }
    
    public void delete(Long userId) {
        boolean exists = userRepository.existsById(userId);
        if(!exists) {
            throw new IllegalStateException("user with id: "+userId+ " does not exist.");
        }
        userRepository.deleteById(userId);
    }
    @Transactional
    public void updateUserData(Long userId, String name, String password, String mail, Role role, String phonenumber, Address address) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalStateException("user with id "+userId+ "does not exist"));

        // Tests
    }
}
