package com.tma.user_management;

import com.tma.user_management.model.User;
import com.tma.user_management.model.enumType.TypeUser;
import com.tma.user_management.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@SpringBootApplication
public class UserManagementApplication {

    public static void main(final String[] args) {
        SpringApplication.run(UserManagementApplication.class, args);
    }
    @Bean
    public CommandLineRunner loadData(UserRepository userRepository) {
        return (args) -> {
            if (!userRepository.existsUserByType(TypeUser.ADMIN)) {
                User admin = new User();
                admin.setType(TypeUser.ADMIN);
                admin.setEmail("hannguyen@gmail.com");
                admin.setUsername("admin");
                admin.setPassword(new BCryptPasswordEncoder().encode("admin"));
                admin.setFirstname("admin");
                admin.setLastname("admin");
                userRepository.save(admin);
            }
        };
    }
}
