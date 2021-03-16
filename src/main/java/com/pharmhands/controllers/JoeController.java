package com.pharmhands.controllers;

import com.pharmhands.repositories.PrescriberInfoRepository;
import com.pharmhands.repositories.UserRepository;
import com.pharmhands.services.EmailService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class JoeController {

    private final EmailService emailService;

    private final UserRepository userDao;

    private final PrescriberInfoRepository prescriberDao;

    public JoeController(EmailService emailService, UserRepository userDao, PrescriberInfoRepository prescriberDao){
        this.emailService = emailService;
        this.userDao = userDao;
        this.prescriberDao = prescriberDao;
    }

    @GetMapping("/{id}/doctorProfile")
    public String doctorProfile(Model model, @PathVariable long id) {
        model.addAttribute("doctor", userDao.getOne(id));
        model.addAttribute("doctorInfo", prescriberDao.findByUser(userDao.getOne(id)));
        return "views/doctor/doctorProfile";
    }

}
