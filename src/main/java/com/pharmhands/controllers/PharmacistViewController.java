package com.pharmhands.controllers;

import com.pharmhands.models.User;
import com.pharmhands.repositories.PrescriptionRequestsRepository;
import com.pharmhands.repositories.PrescriptionsRepository;
import com.pharmhands.repositories.UserRepository;
import com.pharmhands.services.UserService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Secured({"ROLE_PHARMACIST"})
@Controller
public class PharmacistViewController {

    private final UserRepository userDao;

    private final PrescriptionsRepository prescriptionsDao;

    private final PrescriptionRequestsRepository requestDao;

    private final UserService userService;

    public PharmacistViewController(UserRepository userDao, PrescriptionsRepository prescriptionsDao, PrescriptionRequestsRepository requestDao, UserService userService) {
        this.userDao = userDao;
        this.prescriptionsDao = prescriptionsDao;
        this.requestDao = requestDao;
        this.userService = userService;
    }

    @GetMapping("/pharmacistProfile/{id}")
    public String pharmacistProfileView( Model model,@PathVariable long id) {
        model.addAttribute("user", userDao.getOne(id));
        model.addAttribute("prescriptions", prescriptionsDao.findAll());
        model.addAttribute("requests", requestDao.findAllUnfulfilled());
        return "views/pharmacist/pharmacistProfile";
    }

    @GetMapping("/pharmacistProfile/{id}/edit")
    public String editPharmacistInfoForm(Model model, @PathVariable long id){
        User user = userDao.getOne(id);
        model.addAttribute("user", user);
        return "views/pharmacist/pharmacistInfoEdit";
    }


    @PostMapping("/pharmacistProfile/{id}/edit")
    public String editPharmacistInfo(@ModelAttribute User user, @PathVariable long id){
        User loggedIn = userService.loggedInUser();

//        user.setFull_name(user.getFull_name());
        user.setFullName(user.getFullName());
        user.setEmail(user.getEmail());
        user.setPassword(loggedIn.getPassword());
        user.setPhone_number(loggedIn.getPhone_number());
        user.setUsername(loggedIn.getUsername());
        user.setIs_deleted(loggedIn.getIs_deleted());
        user.setRole(loggedIn.getRole());

        userDao.save(user);
        return "redirect:/pharmacistProfile/" + id;
    }

}

