package com.pharmhands.services;

import com.pharmhands.models.User;
import com.pharmhands.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


@Service("userService")
public class UserService {

    private final UserRepository usersDao;

    public UserService(UserRepository usersDao){
        this.usersDao=usersDao;
    }

    public User loggedInUser(){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(user.getUsername()==null){
            return null;
        }
        return usersDao.findById(user.getId()).get();
    }
}
