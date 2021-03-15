package com.pharmhands.controllers;

import com.pharmhands.repositories.UserRepository;
import com.pharmhands.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.pharmhands.models.User;

@Controller
public class PharmacistViewController {

    private final UserRepository userDao;

    private final UserService userService;

//    private final PharmacistRepository pharmacistDao;

    public PharmacistViewController(UserRepository userDao, UserService userService) {
        this.userDao = userDao;
        this.userService = userService;
//        this.pharmacistDao = pharmacistDao;
    }

    @GetMapping("/{id}/pharmacistViewProfile")
    public String pharmacistView( @PathVariable long id, Model model) {
        return "views/pharmacistViewProfile";
    }

}
