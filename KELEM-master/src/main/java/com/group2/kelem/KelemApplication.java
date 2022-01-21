package com.group2.kelem;

import com.group2.kelem.dao.UserRepository;
import com.group2.kelem.model.UserModel;
import com.group2.kelem.model.UserModel.ROLE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class KelemApplication {

	public static void main(String[] args) {
		SpringApplication.run(KelemApplication.class, args);
	}

	@Autowired
	PasswordEncoder encoder;

	@Bean
	public CommandLineRunner dataLoader(UserRepository repo) {
		UserModel admin = new UserModel();
		admin.setUsername("admin");
		admin.setPassword(encoder.encode("admin"));
		admin.setFirstName("admin");
		admin.setLastName("admin");
		admin.setRole(ROLE.ADMIN.name());
		admin.setProfilePicture("abebe.jpeg");
		return args -> {
			repo.save(admin);
		};
	}
}
