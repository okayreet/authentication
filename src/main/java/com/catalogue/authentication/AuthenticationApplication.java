package com.catalogue.authentication;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.catalogue.authentication.entity.Login;
import com.catalogue.authentication.repository.LoginRepository;

@SpringBootApplication
@EnableFeignClients("com.catalogue.authentication.feignClient")
public class AuthenticationApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthenticationApplication.class, args);
	}

	// @Bean
	// CommandLineRunner initDatabase(LoginRepository userRepo, PasswordEncoder passwordEncoder) {
	// 	return args -> {
	// 		Login login = new Login(1L, "okayreet@hotmail.com", passwordEncoder.encode("1111"), "USER",1L);
	// 		userRepo.save(login);
	// 	};
	// }
}
