package com.codewithproject.springsecurity.seeder;

import com.codewithproject.springsecurity.enums.Role;
import com.codewithproject.springsecurity.entities.User;
import com.codewithproject.springsecurity.repository.UserRepository;
import com.codewithproject.springsecurity.util.PassUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserSeeder {

    @Autowired
    private UserRepository userRepo;

    public List<User> seederUser() {
        List<User> result = new ArrayList<>();
        try {
            User admin1 = new User();
            admin1.setLastname("Nguyen Viet");
            admin1.setFirstname("Hai Admin");
            admin1.setEmail("nvhai061993@gmail.com");
            admin1.setUsername("admin1");
            admin1.setPassword(new BCryptPasswordEncoder().encode("123456"));
            admin1.setRole(Role.ADMIN);
            userRepo.save(admin1);
            result.add(admin1);

            User user1 = new User();
            user1.setLastname("Le");
            user1.setFirstname("Hai User");
            user1.setEmail("nvhai2306@gmail.com");
            user1.setUsername("user1");
            user1.setPassword(new BCryptPasswordEncoder().encode("123456"));
            user1.setRole(Role.USER);
            userRepo.save(user1);
            result.add(user1);

            User user2 = new User();
            user2.setLastname("Nguyen");
            user2.setFirstname("An User");
            user2.setEmail("nvhai2306@gmail.com");
            user2.setUsername("user2");
            user1.setPassword(new BCryptPasswordEncoder().encode("123456"));
            user2.setRole(Role.USER);
            userRepo.save(user2);
            result.add(user2);

            User user3 = new User();
            user3.setLastname("Thanh");
            user3.setFirstname("Xuan User");
            user3.setEmail("nvhai2306@gmail.com");
            user3.setUsername("user3");
            user3.setPassword(new BCryptPasswordEncoder().encode("123456"));
            user3.setRole(Role.USER);
            userRepo.save(user3);
            result.add(user3);

            User user4 = new User();
            user4.setLastname("Nguyen");
            user4.setFirstname("An User");
            user4.setEmail("nvhai2306@gmail.com");
            user4.setUsername("user4");
            user4.setPassword(new BCryptPasswordEncoder().encode("123456"));
            user4.setRole(Role.USER);
            userRepo.save(user4);
            result.add(user4);

            User user5 = new User();
            user5.setLastname("Mai");
            user5.setFirstname("Anh User");
            user5.setEmail("nvhai2306@gmail.com");
            user5.setUsername("user5");
            user5.setPassword(new BCryptPasswordEncoder().encode("123456"));
            user5.setRole(Role.USER);
            userRepo.save(user5);
            result.add(user5);

            User user6 = new User();
            user6.setLastname("Doan");
            user6.setFirstname("Duc User");
            user6.setEmail("nvhai2306@gmail.com");
            user6.setUsername("user6");
            user6.setPassword(new BCryptPasswordEncoder().encode("123456"));
            user6.setRole(Role.USER);
            userRepo.save(user6);
            result.add(user6);

            User user7 = new User();
            user7.setLastname("Pham");
            user7.setFirstname("Thuong User");
            user7.setEmail("nvhai2306@gmail.com");
            user7.setUsername("user7");
            user7.setPassword(new BCryptPasswordEncoder().encode("123456"));
            user7.setRole(Role.USER);
            userRepo.save(user7);
            result.add(user7);

            User user8 = new User();
            user8.setLastname("Nguyen");
            user8.setFirstname("Quynh User");
            user8.setEmail("nvhai2306@gmail.com");
            user8.setUsername("user8");
            user8.setPassword(new BCryptPasswordEncoder().encode("123456"));
            user8.setRole(Role.USER);
            userRepo.save(user8);
            result.add(user8);

            User user9 = new User();
            user9.setLastname("Minh");
            user9.setFirstname("Tan User");
            user9.setEmail("nvhai2306@gmail.com");
            user9.setUsername("user9");
            user9.setPassword(new BCryptPasswordEncoder().encode("123456"));
            user9.setRole(Role.USER);
            userRepo.save(user9);
            result.add(user9);

            User user10 = new User();
            user10.setLastname("Quoc");
            user10.setFirstname("Viet User");
            user10.setEmail("nvhai2306@gmail.com");
            user10.setUsername("user10");
            user10.setPassword(new BCryptPasswordEncoder().encode("123456"));
            user10.setRole(Role.USER);
            userRepo.save(user10);
            result.add(user10);
        } catch (Exception ex) {
            return result;
        }
        return result;
    }
}
