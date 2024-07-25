package assessment.dxc.login;

import assessment.dxc.login.model.User;
import assessment.dxc.login.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        // Clear the existing users for demonstration purposes
        userRepository.deleteAll();

        // Insert new users with encoded passwords
        User user1 = new User();
        user1.setUsername("user1");
        user1.setPassword(passwordEncoder.encode("password1"));
        user1.setName("User One");
        user1.setRole("USER");

        User user2 = new User();
        user2.setUsername("manager");
        user2.setPassword(passwordEncoder.encode("abcdefg"));
        user2.setName("Manager");
        user2.setRole("MANAGER");

        // Any resemblance to real or fictional persons is entirely coincidental
        User user3 = new User();
        user3.setUsername("林");
        user3.setPassword(passwordEncoder.encode("jiayinglim"));
        user3.setName("林嘉颖");
        user3.setRole("USER");

        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);

    }
}
