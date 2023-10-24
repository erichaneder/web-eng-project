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

@CrossOrigin
@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public JwtAuthenticationResponse signup(SignUpRequest request) {
        var user = User
                .builder()
                .name(request.getName())
                .password(passwordEncoder.encode(request.getPassword()))
                .email(request.getEmail())
                .role(Role.ROLE_CUSTOMER)
                .phonenumber(request.getPhonenumber())
                .address(request.getAddress())
                .build();

        userService.addNewUser(user);
        var jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }

    public JwtAuthenticationResponse signin(SignInRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        var user = userRepository.findByEmail(request.getEmail()).orElseThrow(() -> new IllegalArgumentException("Invalid email or password."));
        var jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }
}
