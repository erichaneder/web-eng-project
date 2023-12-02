package at.technikum.webengbackend.controller;

import at.technikum.webengbackend.config.AllowedPaths;
import at.technikum.webengbackend.dto.JwtAuthenticationResponse;
import at.technikum.webengbackend.dto.SignInRequest;
import at.technikum.webengbackend.dto.SignUpRequest;
import at.technikum.webengbackend.model.User;
import at.technikum.webengbackend.service.AuthenticationService;
import at.technikum.webengbackend.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class UserController {
    private final UserService userService;
    private final AuthenticationService authenticationService;

    @GetMapping(path= AllowedPaths.User.LIST)
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok(userService.getUsers());
    }

    @GetMapping(path= AllowedPaths.User.GET_ONE)
    public ResponseEntity<User> getUser(@PathVariable("userId") Long userId) {
        return ResponseEntity.ok(userService.getUser(userId));
    }

    @PostMapping(path= AllowedPaths.User.SIGNUP)
    public ResponseEntity<JwtAuthenticationResponse> signup(@RequestBody SignUpRequest request) {
        return ResponseEntity.ok(authenticationService.signup(request));
    }

    @PostMapping(path=AllowedPaths.User.SIGNIN)
    public ResponseEntity<JwtAuthenticationResponse> signin(@RequestBody SignInRequest request) {
        return ResponseEntity.ok(authenticationService.signin(request));
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

    // patch
}
