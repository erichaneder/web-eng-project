package at.technikum.webengbackend.controller;

import at.technikum.webengbackend.config.AllowedPaths;
import at.technikum.webengbackend.dto.JwtAuthenticationResponse;
import at.technikum.webengbackend.dto.SignInRequest;
import at.technikum.webengbackend.dto.SignUpRequest;
import at.technikum.webengbackend.service.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

@CrossOrigin
@RestController
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping(path= AllowedPaths.User.SIGNUP)
    public ResponseEntity<JwtAuthenticationResponse> signup(@RequestBody SignUpRequest request) {
        return ResponseEntity.ok(authenticationService.signup(request));
    }

    @PostMapping(path=AllowedPaths.User.SIGNIN)
    public ResponseEntity<JwtAuthenticationResponse> signin(@RequestBody SignInRequest request) {
        return ResponseEntity.ok(authenticationService.signin(request));
    }
}
