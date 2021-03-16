package com.pharmhands.controllers;

import com.pharmhands.repositories.PrescriptionsRepository;
import com.pharmhands.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class PharmacistViewController {

    private final UserRepository userDao;

    private final PrescriptionsRepository prescriptionsDao;

    public PharmacistViewController(UserRepository userDao, PrescriptionsRepository prescriptionsDao) {
        this.userDao = userDao;
        this.prescriptionsDao = prescriptionsDao;
    }

    @GetMapping("/pharmacistProfile/{id}")
    public String pharmacistProfileView( Model model,@PathVariable long id) {
        model.addAttribute("user", userDao.getOne(id));
        model.addAttribute("prescriptions", prescriptionsDao.findAllUnverified());
        return "views/pharmacist/pharmacistProfile";
    }

}
