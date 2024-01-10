package at.technikum.webengbackend.service;

import at.technikum.webengbackend.config.PasswordConfig;
import at.technikum.webengbackend.model.Address;
import at.technikum.webengbackend.model.User;
import at.technikum.webengbackend.repository.UserRepository;
import at.technikum.webengbackend.model.Role;

import io.micrometer.common.util.StringUtils;
import jakarta.transaction.Transactional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User getUser(Long userId) {
        boolean exists = userRepository.existsById(userId);
        if (!exists) {
            throw new IllegalStateException("user with id: " + userId + " does not exist.");
        }
        return userRepository.findById(userId).get();
    }

    public void addNewUser(User user) {
        userRepository.save(user);
    }

    public void delete(Long userId) {
        boolean exists = userRepository.existsById(userId);
        if (!exists) {
            throw new IllegalStateException("user with id: " + userId + " does not exist.");
        }
        userRepository.deleteById(userId);
    }

    @Transactional
    public User updateUserData(Long userId, String name, String salutation, String password, String mail, Role role, Address address) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalStateException("user with id " + userId + "does not exist"));

        if (name != null && !name.trim().isEmpty() && !Objects.equals(user.getName(), name)) {
            user.setName(name);
        }

        if (salutation != null && !salutation.trim().isEmpty()) {
            user.setSalutation(salutation);
        }

        if (password != null && !password.trim().isEmpty()) {
            user.setPassword(PasswordConfig.passwordEncoder().encode(password));
        }

        if (mail != null && !mail.trim().isEmpty() && !Objects.equals(user.getEmail(), mail)) {
            user.setEmail(mail);
        }

        if (role != null) {
            user.setRole(role);
        }

        if (address != null) {
            if (!StringUtils.isEmpty(address.getCountry())) {
                user.getAddress().setCountry(address.getCountry());
            }
            if (!StringUtils.isEmpty(address.getCity())) {
                user.getAddress().setCity(address.getCity());
            }
            if (!StringUtils.isEmpty(address.getZipcode())) {
                user.getAddress().setZipcode(address.getZipcode());
            }
            if (!StringUtils.isEmpty(address.getStreet())) {
                user.getAddress().setStreet(address.getStreet());
            }
        }
        return userRepository.save(user);
    }

    @Transactional
    public User patchUserData(Long userId, Map<String, Object> fields) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalStateException("user with id " + userId + "does not exist"));

        fields.forEach((key,value) -> {
            validateField(key,value);
            Field field = ReflectionUtils.findField(User.class,key);
            if(field != null) {
                field.setAccessible(true);
                ReflectionUtils.setField(field, user, value);
            }
        });
        return userRepository.save(user);
    }

    private void validateField(String key, Object value) {
        switch (key) {
            case "name":
                validateName((String) value);
                break;
            case "salutation":
                validateSalutation((String) value);
                break;
            case "password":
                validatePassword((String) value);
                break;
            case "email":
                validateEmail((String) value);
                break;

            // Herwig du kannst hier die restliche Validation hinzufügen
            default:
                // Falls was unbekannt sein sollte oderso:
                break;
        }
    }

    private void validateName(String name) {
        if(name.length() < 5){
            throw new IllegalStateException("Name must have at least 5 characters");
        }
        if(name.length() > 50){
            throw new IllegalStateException("Name must have under 51 characters");
        }
    }

    private void validateEmail(String value) {
        if(value.isEmpty()){
            throw new IllegalStateException("e-mail is mandatory");
        }
    }

    private void validatePassword(String value) {
        if(value.isEmpty()){
            throw new IllegalStateException("password is mandatory");
        }
    }

    private void validateSalutation(String value) {
        if(value.length() > 30){
            throw new IllegalStateException("Name must have under 31 characters");
        }
    }


    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                if (username.contains("@")) {
                    return userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("Email not found"));
                } else {
                    return userRepository.findByName(username).orElseThrow(() -> new UsernameNotFoundException("Username not found"));
                }
            }
        };
    }
}
