package com.pharmhands.controllers;

import com.pharmhands.repositories.UserRepository;
import com.pharmhands.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PhViewController {

    private final UserRepository userDao;

    private final UserService userService;

    public PhViewController(UserRepository userDao, UserService userService) {
        this.userDao = userDao;
        this.userService = userService;
    }

    @GetMapping("/profile/phViewProfile")
    public String phView() {
        return "views/phViewProfile";
    }
}
