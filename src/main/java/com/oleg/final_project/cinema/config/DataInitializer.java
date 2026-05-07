package com.oleg.final_project.cinema.config;

import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.oleg.final_project.cinema.model.security.Role;
import com.oleg.final_project.cinema.model.security.User;
import com.oleg.final_project.cinema.repository.RoleRepository;
import com.oleg.final_project.cinema.repository.UserRepository;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner init(UserRepository userRepository, RoleRepository roleRepository,
            PasswordEncoder passwordEncoder) {
        return args -> {
            // make sure role exists
            Role adminRole = roleRepository.findByName("ADMIN")
                    .orElseGet(() -> {
                        Role r = new Role();
                        r.setName("ADMIN");
                        return roleRepository.save(r);
                    });

            // make sure user exists
            if(userRepository.findByUsername("admin").isEmpty()){
                User user = new User();
                user.setUsername("admin");
                user.setPassword(passwordEncoder.encode("12345"));
                user.setRoles(Set.of(adminRole));
                userRepository.save(user);
            }
        };
    }
}
