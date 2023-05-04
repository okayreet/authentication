package com.catalogue.authentication.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.catalogue.authentication.dto.LoginDto;
// import com.catalogue.authentication.dto.AuthRequest;
import com.catalogue.authentication.dto.RegistrationRequest;
import com.catalogue.authentication.service.RegistrationService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    // private final AuthService service;
    private final RegistrationService registrationService;
    // private final AuthenticationManager authenticationManager;

    // @PostMapping("/login")
    // public String getToken(@RequestBody AuthRequest authRequest) {
    // Authentication authenticate = authenticationManager
    // .authenticate(
    // new UsernamePasswordAuthenticationToken(
    // authRequest.getEmail(),
    // authRequest.getPassword()));
    // if (authenticate.isAuthenticated()) {
    // return service.generateToken(authRequest.getEmail());
    // } else {
    // throw new ApiRequestException("invalid access");
    // }
    // }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegistrationRequest registerRequest) {
        return registrationService.registerNewUser(registerRequest);
    }

    @PostMapping("/register2")
    public String register2() {
        return registrationService.register2();
    }

    @GetMapping("/getlogin")
    public LoginDto getlogin(@RequestBody Long user_id) {
        return registrationService.getLoginDetails(user_id);
    }
    // @GetMapping("/validate")
    // public String validateToken(@RequestParam("token") String token) {
    // service.validateToken(token);
    // return "Token is valid";
    // }
}
