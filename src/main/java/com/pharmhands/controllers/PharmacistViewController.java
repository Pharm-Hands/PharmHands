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

    @GetMapping("/pharmacistProfile/{id}")
    public String pharmacistProfileView( Model model,@PathVariable long id) {
        model.addAttribute("user", userDao.getOne(id));
        return "views/pharmacist/pharmacistProfile";
    }

}
