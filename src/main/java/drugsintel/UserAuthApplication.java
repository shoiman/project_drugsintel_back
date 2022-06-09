package drugsintel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class UserAuthApplication {
	

	public static void main(String[] args) {
		SpringApplication.run(UserAuthApplication.class, args);
	}

//	@Override
//	public void run(String... args) throws Exception {
//		if (!userRepository.existsById("admin")) {
//			String password = passwordEncoder.encode("admin");
//			UserAccount userAccount = new UserAccount("admin", "admin@grugsintel.com", password);
//			userAccount.setRole("ADMIN");
//			userRepository.save(userAccount);
//		}

//	}
}
