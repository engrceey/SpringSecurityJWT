package com.zurum.jwtsecurityexample;

import com.zurum.jwtsecurityexample.model.ApplicationUser;
import com.zurum.jwtsecurityexample.model.Role;
import com.zurum.jwtsecurityexample.service.ApplicationUserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;

@SpringBootApplication
public class JwtsecurityexampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(JwtsecurityexampleApplication.class, args);
	}

	@Bean
	CommandLineRunner run(ApplicationUserService userService) {
		return args -> {

			userService.saveRole(new Role(null, "ROLE_USER"));
			userService.saveRole(new Role(null, "ROLE_MANAGER"));
			userService.saveRole(new Role(null, "ROLE_ADMIN"));
			userService.saveRole(new Role(null, "ROLE_SUPER_ADMIN"));

			userService.saveUser(
					new ApplicationUser
					(null,
					"john",
					"john@gmail.com",
					"123456789",
					new ArrayList<>()
					)
			);

			userService.saveUser(
					new ApplicationUser
							(null,
									"kemi",
									"kemi@gmail.com",
									"123456789",
									new ArrayList<>()
							)
			);

			userService.saveUser(
					new ApplicationUser
							(null,
									"ada",
									"ada@gmail.com",
									"123456789",
									new ArrayList<>()
							)
			);

			userService.saveUser(
					new ApplicationUser
							(null,
									"love",
									"love@gmail.com",
									"123456789",
									new ArrayList<>()
							)
			);

			userService.addRoleToUser("john@gmail.com", "ROLE_USER");
			userService.addRoleToUser("kemi@gmail.com", "ROLE_MANAGER");
			userService.addRoleToUser("ada@gmail.com", "ROLE_ADMIN");
			userService.addRoleToUser("love@gmail.com", "ROLE_SUPER_ADMIN");

		};
	}

}
