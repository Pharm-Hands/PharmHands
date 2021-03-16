package com.pharmhands.controllers;

import com.pharmhands.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class PharmacistViewController {

    private final UserRepository userDao;

    public PharmacistViewController(UserRepository userDao) {
        this.userDao = userDao;
    }

    @GetMapping("/{id}/pharmacistViewProfile")
    public String pharmacistView( @PathVariable long id, Model model) {
        return "views/pharmacist/viewProfile";
    }

}
