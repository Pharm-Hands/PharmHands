package com.pharmhands;

import com.pharmhands.models.User;
import com.pharmhands.models.UserRoles;
import com.pharmhands.repositories.UserRepository;
import com.pharmhands.repositories.UserRolesRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PostStartupRunner implements CommandLineRunner {

    private final UserRepository userDao;
    private final UserRolesRepository userRolesDao;
    private final PasswordEncoder encoder;

    public PostStartupRunner(UserRepository userDao, UserRolesRepository userRolesDao, PasswordEncoder encoder) {
        this.userDao = userDao;
        this.userRolesDao = userRolesDao;
        this.encoder = encoder;
    }

    @Override
    public void run(String... args) throws Exception {
        // don't run if there's already any users in the database
        if (userDao.count() != 0) {
            return;
        }
        UserRoles role = new UserRoles();
        role.setRole_name("patient");
        UserRoles savedRole = userRolesDao.save(role);

        User user = new User();

        String hash = encoder.encode("joe1996");
        user.setPassword(hash);
        user.setFull_name("Joe Cuthbert");
        user.setIs_deleted(0);
        user.setPhone_number("2102596441");
        user.setUsername("joe");
        user.setEmail("josiah.thomas.cuthbert@gmail.com");
        user.setRole(savedRole);
        User savedUser = userDao.save(user);

    }
}