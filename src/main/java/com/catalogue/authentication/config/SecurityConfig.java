package com.catalogue.authentication.config;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.catalogue.authentication.exception.ApiRequestException;
import com.catalogue.authentication.repository.LoginRepository;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final LoginRepository loginRepository;

    @Bean
    UserDetailsService userDetailsService() {
        return email -> loginRepository.findByEmail(email)
                .orElseThrow(() -> new ApiRequestException("Wrong email or password"));
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(
                authenticationProvider());
        customAuthenticationFilter.setFilterProcessesUrl("/api/v1/auth/login");

        http.csrf().disable()
                .sessionManagement().sessionCreationPolicy(STATELESS)
                .and()
                .authorizeHttpRequests()
                .requestMatchers("/api/v1/auth/login", "/api/v1/auth/register").permitAll()
                .and()
                .authorizeHttpRequests().anyRequest().authenticated()
                .and()
                .addFilter(customAuthenticationFilter);
        return http.build();
    }

    // private AuthenticationManager
    // authenticationManagerBean(AuthenticationProvider authenticationProvider) {
    // AuthenticationManagerBuilder authenticationManagerBuilder =
    // authenticationProvider.
    // }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    // @Bean
    // AuthenticationManager authenticationManager(AuthenticationConfiguration
    // config) throws Exception {
    // return config.getAuthenticationManager();
    // }
}
