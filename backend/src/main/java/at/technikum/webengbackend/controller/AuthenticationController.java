package at.technikum.webengbackend.controller;

import at.technikum.webengbackend.config.IAllowPath;
import at.technikum.webengbackend.dto.JwtAuthenticationResponse;
import at.technikum.webengbackend.dto.SignInRequest;
import at.technikum.webengbackend.dto.SignUpRequest;
import at.technikum.webengbackend.service.AuthenticationService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;

@CrossOrigin
@RestController
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;


    @CrossOrigin
    @PostMapping(path=IAllowPath.USER_SIGNUP)
    public JwtAuthenticationResponse signup(@RequestBody SignUpRequest request) {
        return authenticationService.signup(request);
    }

    @CrossOrigin
    @PostMapping(path=IAllowPath.USER_SIGNIN)
    public JwtAuthenticationResponse signin(@RequestBody SignInRequest request) {
        return authenticationService.signin(request);
    }
}
