package com.catalogue.authentication.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.catalogue.authentication.dto.RegisterRequest;
import com.catalogue.authentication.entity.Login;
import com.catalogue.authentication.feignClient.RegistrationFeign;
import com.catalogue.authentication.repository.LoginRepository;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RegistrationService {
    private final RegistrationFeign registrationFeign;
    private final LoginRepository loginRepository;
    private final PasswordEncoder passwordEncoder;

    public Long registerNewUser(RegisterRequest registerRequest) {
        Long user_id = registrationFeign.register(registerRequest);

        if (user_id == null)
            throw new RuntimeException("user is is null");

        Login login = new Login(
                registerRequest.email(),
                passwordEncoder.encode(registerRequest.password()),
                registerRequest.role(),
                user_id);
        loginRepository.save(login);

        return user_id;
    }

    public String register2() {
        return registrationFeign.register2();
    }
}
