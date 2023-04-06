package com.catalogue.authentication;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final LoginRepository userRepository;

    public List<Login> getAllUsers(){
        return userRepository.findAll();
    }

    public Login getUserById(Long id){
        return userRepository.findById(id).get();
    }
}
