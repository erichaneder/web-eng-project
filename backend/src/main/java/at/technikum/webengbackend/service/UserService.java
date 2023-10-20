package at.technikum.webengbackend.service;

import at.technikum.webengbackend.model.Address;
import at.technikum.webengbackend.model.LoginRequest;
import at.technikum.webengbackend.model.LoginResponse;
import at.technikum.webengbackend.model.User;
import at.technikum.webengbackend.repository.UserRepository;
import at.technikum.webengbackend.util.Role;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

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

        if (name != null && !name.trim().isEmpty() && !Objects.equals(user.getName(), name)) {
            user.setName(name);
        }

        // ToDo: Passwörter hashen?
        if (password != null && !password.trim().isEmpty()) {
            user.setPassword(password);
        }

        if (mail != null && !mail.trim().isEmpty() && !Objects.equals(user.getMail(), mail)) {
            user.setMail(mail);
        }

        if (role != null) {
            user.setRole(role);
        }

        if (phonenumber != null && !phonenumber.trim().isEmpty()) {
            user.setPhonenumber(phonenumber);
        }

        if (address != null) {
            user.setAddress(address);
        }
    }

    public LoginResponse loginUser(LoginRequest login) {

        //TODO:
        //check email/password in db if they match a user, if yes return the JWT Token, if not 404 not found

        return new LoginResponse("1232342");
    }
}
