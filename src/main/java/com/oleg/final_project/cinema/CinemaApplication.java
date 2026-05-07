package com.oleg.final_project.cinema;

import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.oleg.final_project.cinema.model.security.Role;
import com.oleg.final_project.cinema.repository.RoleRepository;
import com.oleg.final_project.cinema.repository.UserRepository;
import com.oleg.final_project.cinema.service.UserService;

@SpringBootApplication
public class CinemaApplication {
	public static void main(String[] args) {
		SpringApplication.run(CinemaApplication.class, args);
	}

	@Bean
	CommandLineRunner initUsers(UserService userService, RoleRepository roleRepository, UserRepository userRepository) {
		return args -> {

			// 1. ensure role exists
			Role adminRole = roleRepository.findByName("ADMIN")
					.orElseGet(() -> {
						Role r = new Role();
						r.setName("ADMIN");
						return roleRepository.save(r);
					});

			// 2. ensure user exists
			if (userRepository.findByUsername("admin").isEmpty()) {
				userService.createUser("admin", "1234", Set.of(adminRole));
			}
		};
	}
}
