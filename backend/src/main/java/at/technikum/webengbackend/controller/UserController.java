package at.technikum.webengbackend.controller;

import at.technikum.webengbackend.config.AllowedPath;
import at.technikum.webengbackend.config.IAllowPath;
import at.technikum.webengbackend.model.Address;
import at.technikum.webengbackend.model.User;
import at.technikum.webengbackend.service.UserService;
import at.technikum.webengbackend.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    private final UserService userService;
    @Autowired
    public UserController(UserService userService) { this.userService = userService; }

    @GetMapping(path= IAllowPath.USER_LIST)
    public List<User> getUser() {
        return userService.getUser();
    }

    @PostMapping(path=IAllowPath.USER_ADD)
    public void registerNewUser(@RequestBody User user) {userService.addNewUser(user);}

    @DeleteMapping(path=IAllowPath.USER_DELETE)
    public void deleteUser(@PathVariable("userId") Long userID) { userService.delete(userID);}

    @PutMapping(path= IAllowPath.USER_UPDATE)
    public void updateUser(@PathVariable("userId") Long userId,@RequestBody User user) {
        userService.updateUserData(userId,user.getName(),user.getPassword(),user.getEmail(),user.getRole(),user.getPhonenumber());
    }
}
