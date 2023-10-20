package at.technikum.webengbackend.controller;

import at.technikum.webengbackend.model.Address;
import at.technikum.webengbackend.model.LoginRequest;
import at.technikum.webengbackend.model.LoginResponse;
import at.technikum.webengbackend.model.User;
import at.technikum.webengbackend.service.UserService;
import at.technikum.webengbackend.util.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api/v1/user")
public class UserController {
    private final UserService userService;
    @Autowired
    public UserController(UserService userService) { this.userService = userService; }

    @GetMapping
    public List<User> getUser() {
        return userService.getUser();
    }

    @PostMapping
    public void registerNewUser(@RequestBody User user) {userService.addNewUser(user);}

    @CrossOrigin
    @PostMapping(path="/login")
    public ResponseEntity<LoginResponse> loginUser(@RequestBody LoginRequest login) {
        return ResponseEntity.ok(userService.loginUser(login));
    }

    @DeleteMapping(path="{userId}")
    public void deleteUser(@PathVariable("userId") Long userID) { userService.delete(userID);}

    @PutMapping(path="{userId}")
    public void updateUser(@PathVariable("userId") Long userId,
                           @RequestParam String name,
                           @RequestParam String password,
                           @RequestParam String mail,
                           @RequestParam Role role,
                           @RequestParam String phonenumber,
                           @RequestParam Address address) {
        userService.updateUserData(userId,name,password,mail,role,phonenumber,address);

    }
}
