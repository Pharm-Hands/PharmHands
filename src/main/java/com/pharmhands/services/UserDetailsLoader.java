package com.pharmhands.services;

import com.pharmhands.models.User;
import com.pharmhands.models.UserWithRoles;
import com.pharmhands.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsLoader implements UserDetailsService {
    private final UserRepository userDao;

    public UserDetailsLoader(UserRepository userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("No user found for " + username);
        }
        return new UserWithRoles(user);

    }
        public UserDetails loadUserByEmail(String email) {
             User userEmail = userDao.findByEmail(email);
            if (email == null) {
                throw new UsernameNotFoundException("No user found for " +email);
            }
            return new UserWithRoles(userEmail);
        }
}