package com.catalogue.authentication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
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
