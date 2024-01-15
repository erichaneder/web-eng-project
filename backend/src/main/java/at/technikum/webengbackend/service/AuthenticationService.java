package at.technikum.webengbackend.service;

import at.technikum.webengbackend.dto.JwtAuthenticationResponse;
import at.technikum.webengbackend.dto.SignUpRequest;
import at.technikum.webengbackend.dto.SignInRequest;
import at.technikum.webengbackend.model.Role;
import at.technikum.webengbackend.model.User;
import at.technikum.webengbackend.repository.UserRepository;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

@CrossOrigin
@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private static final Set<String> ISO_COUNTRIES = Set.of(Locale.getISOCountries());

    public JwtAuthenticationResponse signup(SignUpRequest request) {
        // Validate password
        if (!isValidPassword(request.getPassword())) {
            throw new IllegalArgumentException("Invalid password. Please ensure it has at least 8 characters, numbers, and both lowercase and uppercase letters.");
        }
        // Validate country code
        if (!ISO_COUNTRIES.contains(request.getAddress().getCountry())) {
            throw new IllegalArgumentException(request.getAddress().getCountry() + " -> Invalid country. Please provide a valid country");
        }

        // Fill in the rest if the password was valid
        var user = User
                .builder()
                .name(request.getName())
                .salutation(request.getSalutation())
                .password(passwordEncoder.encode(request.getPassword()))
                .email(request.getEmail())
                .role(Role.ROLE_CUSTOMER) // all registered users will be customers. Change it in the DB.
                .phonenumber(request.getPhonenumber())
                .address(request.getAddress())
                .build();
        // Add the user
        userService.addNewUser(user);
        // Add extra fields to the JWT (it aint the pretty way)
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("id",user.getId());
        extraClaims.put("role",user.getRole());
        // Generate a new JWT
        var jwt = jwtService.generateToken(extraClaims,user);
        // Return it with the help of the DTO JwtAuthenticationResponse
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }

    public JwtAuthenticationResponse signin(SignInRequest request) {
        // Authenticate user
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        // If authentication is successful, retrieve the user from the database
        User user;
        if (request.getUsername().contains("@")) {
            user = userRepository.findByEmail(request.getUsername()).orElseThrow(() -> new IllegalArgumentException("Invalid email or password."));
        } else {
            user = userRepository.findByName(request.getUsername()).orElseThrow(() -> new IllegalArgumentException("Invalid username or password."));
        }
        // Add extra fields to the JWT
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("id",user.getId());
        extraClaims.put("role",user.getRole());
        // Generate JWT token
        var jwt = jwtService.generateToken(extraClaims,user);
        // Return the authentication response
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }

    private boolean isValidPassword(String password) {
        // Minimum 8 characters, at least one number, one lowercase letter, and one uppercase letter
        String passwordRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$";
        // Compile the regular expression
        Pattern pattern = Pattern.compile(passwordRegex);
        // Match the password against the pattern
        return pattern.matcher(password).matches();
    }
}
