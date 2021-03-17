package com.pharmhands.controllers;

import com.pharmhands.models.User;
import com.pharmhands.repositories.PrescriptionsRepository;
import com.pharmhands.repositories.UserRepository;
import com.pharmhands.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PharmacistViewController {

    private final UserRepository userDao;

    private final PrescriptionsRepository prescriptionsDao;

    private final UserService userService;

    public PharmacistViewController(UserRepository userDao, PrescriptionsRepository prescriptionsDao, UserService userService) {
        this.userDao = userDao;
        this.prescriptionsDao = prescriptionsDao;
        this.userService = userService;
    }

    @GetMapping("/pharmacistProfile/{id}")
    public String pharmacistProfileView( Model model,@PathVariable long id) {
        model.addAttribute("user", userDao.getOne(id));
        model.addAttribute("prescriptions", prescriptionsDao.findAllUnverified());
        return "views/pharmacist/pharmacistProfile";
    }

    @GetMapping("pharmacistProfile/{id}/edit")
    public String editPharmacistInfoForm(Model model, @PathVariable long id){
        User user = userDao.getOne(id);
        model.addAttribute("user", user);
        return "/views/pharmacist/pharmacistInfoEdit";
    }

    @PostMapping(path = "pharmacistProfile/{id}/edit")
    public String editPharmacistInfo(@ModelAttribute User user){
        User loggedIn = userDao.getOne(3L);
        user.setFull_name(user.getFull_name());
        user.setEmail(user.getEmail());
        userDao.save(user);
        return "redirect:/pharmacistProfile/{id}/";
    }

}
