package com.catalogue.authentication.config;

import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.catalogue.authentication.entity.Login;
import com.catalogue.authentication.repository.LoginRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final LoginRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Login login = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Wrong email or password"));
        return new org.springframework.security.core.userdetails.User(
                email,
                login.getPassword(),
                List.of(new SimpleGrantedAuthority("ROLE_" + login.getRole())));
    }
}