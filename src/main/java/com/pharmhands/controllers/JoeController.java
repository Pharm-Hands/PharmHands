package com.pharmhands.controllers;

import com.pharmhands.repositories.PrescriberInfoRepository;
import com.pharmhands.repositories.PrescriptionsRepository;
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

    private final PrescriptionsRepository prescriptionsDao;

    private final PrescriberInfoRepository prescriberDao;

    public JoeController(EmailService emailService, UserRepository userDao, PrescriptionsRepository prescriptionsDao, PrescriberInfoRepository prescriberDao){
        this.emailService = emailService;
        this.userDao = userDao;
        this.prescriptionsDao = prescriptionsDao;
        this.prescriberDao = prescriberDao;
    }

    @GetMapping("/{id}/doctorProfile")
    public String doctorProfile(Model model, @PathVariable long id) {
        model.addAttribute("doctor", userDao.getOne(id));
        model.addAttribute("prescriptions", prescriptionsDao.findAllByPrescriberId(id));
        model.addAttribute("doctorInfo", prescriberDao.findByUser(userDao.getOne(id)));
        return "views/doctor/doctorProfile";

    }

    @GetMapping("/prescription/{id}")
    public String viewPrescription(Model model, @PathVariable long id){
        model.addAttribute("prescription", prescriptionsDao.getOne(id));
        return "views/prescription";
    }

}
