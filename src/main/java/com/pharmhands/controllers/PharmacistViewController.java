package com.pharmhands.controllers;

import com.pharmhands.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.pharmhands.models.User;

@Controller
public class PharmacistViewController {

    private final UserRepository userDao;


//    private final PharmacistRepository pharmacistDao;

    public PharmacistViewController(UserRepository userDao) {
        this.userDao = userDao;
//        this.pharmacistDao = pharmacistDao;
    }

    @GetMapping("/{id}/pharmacistViewProfile")
    public String pharmacistView( @PathVariable long id, Model model) {
        return "views/pharmacistViewProfile";
    }

}
