package at.technikum.webengbackend.controller;

import at.technikum.webengbackend.config.AllowedPaths;
import at.technikum.webengbackend.model.User;
import at.technikum.webengbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    private final UserService userService;
    @Autowired
    public UserController(UserService userService) { this.userService = userService; }
    @GetMapping(path= AllowedPaths.User.LIST)
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok(userService.getUsers());
    }

    @GetMapping(path= AllowedPaths.User.GET_ONE)
    public ResponseEntity<User> getUser(@PathVariable("userId") Long userId) {
        return ResponseEntity.ok(userService.getUser(userId));
    }

    @PostMapping(path=AllowedPaths.User.ADD)
    public ResponseEntity<?> registerNewUser(@RequestBody User user) {
        userService.addNewUser(user);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(path=AllowedPaths.User.DELETE)
    public ResponseEntity<?> deleteUser(@PathVariable("userId") Long userID) {
        userService.delete(userID);
        return ResponseEntity.ok().build();
    }

    @PutMapping(path= AllowedPaths.User.UPDATE)
    public ResponseEntity<?> updateUser(@PathVariable("userId") Long userId,@RequestBody User user) {
        userService.updateUserData(userId,user.getName(),user.getPassword(),user.getEmail(),user.getRole(),user.getPhonenumber());
        return ResponseEntity.ok().build();
    }
}
