package at.technikum.webengbackend.controller;

import at.technikum.webengbackend.config.AllowedPaths;
import at.technikum.webengbackend.dto.SignInRequest;
import at.technikum.webengbackend.dto.SignUpRequest;
import at.technikum.webengbackend.model.User;
import at.technikum.webengbackend.service.AuthenticationService;
import at.technikum.webengbackend.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class UserController {
    private final UserService userService;
    private final AuthenticationService authenticationService;

    @GetMapping(path= AllowedPaths.User.LIST)
    public ResponseEntity<List<User>> getUsers() {
        try {
            return ResponseEntity.ok(userService.getUsers());
        } catch (Exception e) {
            throw new IllegalArgumentException("Fehler beim Lesen der Benutzer: " + e.getMessage());
        }
    }

    @PreAuthorize("(hasRole('ROLE_CUSTOMER') and #userId == authentication.principal.id) OR hasRole('ROLE_ADMIN') ")
    @GetMapping(path= AllowedPaths.User.GET_ONE)
    public ResponseEntity<User> getUser(@PathVariable("userId") Long userId) {
        try {
            return ResponseEntity.ok(userService.getUser(userId));
        } catch (Exception e) {
            throw new IllegalArgumentException("Fehler beim Lesen des Benutzers: " + e.getMessage());
        }
    }

    @PostMapping(path= AllowedPaths.User.SIGNUP)
    @PreAuthorize("isAnonymous()")
    public ResponseEntity<?> signup(@RequestBody SignUpRequest request) {
        try {
            return ResponseEntity.ok(authenticationService.signup(request));
        } catch (Exception e) {
            throw e;
            //throw new IllegalArgumentException("Fehler bei der Benutzerregistrierung: " + e.getMessage());
        }
    }

    @PostMapping(path=AllowedPaths.User.SIGNIN)
    @PreAuthorize("isAnonymous()")
    public ResponseEntity<?> signin(@RequestBody SignInRequest request) {
        try {
            return ResponseEntity.ok(authenticationService.signin(request));
        } catch (AuthenticationException e) {
           return new ResponseEntity<>("Fehler beim Login: Ungültige Anmeldeinformationen", HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            throw new IllegalArgumentException("Fehler beim Login: " + e.getMessage());
        }
    }

    @DeleteMapping(path=AllowedPaths.User.DELETE)
    public ResponseEntity<?> deleteUser(@PathVariable("userId") Long userID) {
        try {
            userService.delete(userID);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            throw new IllegalArgumentException("Fehler beim Löschen des Benutzers: " + e.getMessage());
        }
    }

    @PreAuthorize("(hasRole('ROLE_CUSTOMER') and #userId == authentication.principal.id) OR hasRole('ROLE_ADMIN') ")
    @PutMapping(path= AllowedPaths.User.UPDATE)
    public ResponseEntity<?> updateUser(@PathVariable("userId") Long userId,@RequestBody User user) {
        try {
            userService.updateUserData(userId,user.getName(), user.getSalutation(), user.getPassword(), user.getEmail(),user.getRole(), user.getAddress(), user.getProfilePicture());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            throw new IllegalArgumentException("Fehler bei der Benutzerdatenänderung: " + e.getMessage());
        }
    }
}
