package com.pharmhands.controllers;

import com.pharmhands.models.User;
import com.pharmhands.repositories.PrescriptionsRepository;
import com.pharmhands.repositories.UserRepository;
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

    @GetMapping("pharmacistProfile/{id}edit")
    public String editPharmacistInfoForm(Model model, @PathVariable long id){
        User user = userDao.getOne(id);
        model.addAttribute("user", user);
        return "/views/pharmacist/pharmacistInfoEdit";
    }

    @PostMapping(path = "pharmacistProfile/{id}edit")
    public String editPharmacistInfo(@ModelAttribute User user){
        userDao.save(user);
        return "redirect:/pharmacistProfile/{id}/";
    }

}
