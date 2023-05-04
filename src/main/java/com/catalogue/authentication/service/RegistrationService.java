package com.catalogue.authentication.service;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.catalogue.authentication.client.RegistrationFeign;
import com.catalogue.authentication.dto.LoginDto;
import com.catalogue.authentication.dto.RegistrationRequest;
import com.catalogue.authentication.entity.Login;
import com.catalogue.authentication.exception.ApiRequestException;
import com.catalogue.authentication.repository.LoginRepository;

import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class RegistrationService {
    private final RegistrationFeign registrationFeign;
    private final LoginRepository loginRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    public ResponseEntity<?> registerNewUser(RegistrationRequest registerRequest) {

        if (emailAlreadyExists(registerRequest.email())) {
            throw new ApiRequestException("Email already exists");
        }

        try {
            Long user_id = registrationFeign.register(registerRequest);
            saveUserLogin(registerRequest, user_id);

        } catch (FeignException e) {
            log.info("Openfeign client exception was: {}", e.getMessage());
            throw new ApiRequestException(e.getMessage());
        }
        return new ResponseEntity<>("Successfully registered", HttpStatus.OK);
    }

    private void saveUserLogin(RegistrationRequest registerRequest, Long user_id) {

        Login login = new Login(registerRequest.email(), passwordEncoder.encode(registerRequest.password()),
                registerRequest.role().toUpperCase().trim(), user_id);
        loginRepository.save(login);
    }

    private boolean emailAlreadyExists(String email) {
        return loginRepository.findByEmail(email).isPresent();
    }

    public String register2() {
        return registrationFeign.register2();
    }

    public LoginDto getLoginDetails(Long user_id) {
        return modelMapper.map(loginRepository.findByUserId(user_id).get(), LoginDto.class);
    }
}
