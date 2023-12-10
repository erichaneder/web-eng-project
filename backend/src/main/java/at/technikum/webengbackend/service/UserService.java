package at.technikum.webengbackend.service;

import at.technikum.webengbackend.model.Address;
import at.technikum.webengbackend.model.User;
import at.technikum.webengbackend.repository.UserRepository;
import at.technikum.webengbackend.model.Role;

import jakarta.transaction.Transactional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) { this.userRepository = userRepository; }
    
    public List<User> getUsers() { return userRepository.findAll(); }

    public User getUser(Long userId) {
        boolean exists = userRepository.existsById(userId);
        if(!exists) {
            throw new IllegalStateException("user with id: " + userId + " does not exist.");
        }
        return userRepository.findById(userId).get();
    }

    public void addNewUser(User user) {userRepository.save(user);}
    
    public void delete(Long userId) {
        boolean exists = userRepository.existsById(userId);
        if(!exists) {
            throw new IllegalStateException("user with id: "+userId+ " does not exist.");
        }
        userRepository.deleteById(userId);
    }
    @Transactional
    public void updateUserData(Long userId, String name, String salutation, String password, String mail, Role role, Address address) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalStateException("user with id "+userId+ "does not exist"));

        if (name != null && !name.trim().isEmpty() && !Objects.equals(user.getName(), name)) {
            user.setName(name);
        }

        if (salutation != null && !salutation.trim().isEmpty()) {
            user.setSalutation(salutation);
        }

        if (password != null && !password.trim().isEmpty()) {
            user.setPassword(password);
        }

        if (mail != null && !mail.trim().isEmpty() && !Objects.equals(user.getEmail(), mail)) {
            user.setEmail(mail);
        }

        if (role != null) {
            user.setRole(role);
        }

        if (address != null) {
            user.setAddress(address);
        }
    }

    // patch

    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                return userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("Username not found"));
            }
        };
    }
}
