package com.pharmhands.controllers;

import com.pharmhands.repositories.PatientInfoRepository;
import com.pharmhands.repositories.PrescriptionsRepository;
import com.pharmhands.repositories.UserRepository;
import com.pharmhands.services.EmailService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PatientProfileController {

    private final UserRepository userDao;

    private final PatientInfoRepository patientDao;

    private final PrescriptionsRepository prescriptionsDao;

    private final EmailService emailService;

    public PatientProfileController(UserRepository userDao, PatientInfoRepository patientDao, PrescriptionsRepository prescriptionsDao, EmailService emailService){
        this.userDao = userDao;
        this.patientDao = patientDao;
        this.prescriptionsDao = prescriptionsDao;
        this.emailService = emailService;
    }

    @GetMapping("/patientProfile/{id}")
    public String patientProfileView(@PathVariable long id, Model model){
        model.addAttribute("patient",userDao.getOne(id));
        model.addAttribute("patientInfo", patientDao.findByUser(userDao.getOne(id)));
        model.addAttribute("prescriber",prescriptionsDao.getOne(id));
        return "views/patient/patientProfile";
    }

    @PostMapping("/patientProfile/{id}")
    public String emailSend(@PathVariable long id) {
        emailService.prepareAndSend(userDao.getOne(id), "Message Sent", "Dear"+userDao.getOne(id).getFull_name()+"Your Pharmacist will be notified &  should reach out to you in 2 business days .Thank you");
        return "redirect:/";
    }


}
