package SAGroup;

import SAGroup.login.model.Roles;
import SAGroup.login.model.UserEntity;
import SAGroup.login.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class WebshopApplication {

	private final UserService userService;
	private final PasswordEncoder passwordEncoder;


	public WebshopApplication(UserService userService, PasswordEncoder passwordEncoder) {
		this.userService = userService;
		this.passwordEncoder = passwordEncoder;
	}

	public static void main(String[] args) {
		SpringApplication.run(WebshopApplication.class, args);
	}

	@Bean
	CommandLineRunner createAdminUser() {
		return args -> {
			String adminUsername = "admin";
			if (!userService.existsByUsername(adminUsername)) {
				UserEntity admin = new UserEntity();
				admin.setUsername(adminUsername);
				admin.setPassword(passwordEncoder.encode("admin"));
				admin.setRole(Roles.ROLE_ADMIN);
				ObjectMapper objectMapper = new ObjectMapper();
				String adminJson = objectMapper.writeValueAsString(admin);
				userService.saveJson(adminJson);
			}
		};
	}
}
