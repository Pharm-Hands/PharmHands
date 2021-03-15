package com.pharmhands.controllers;

import com.pharmhands.models.User;
import com.pharmhands.repositories.PatientInfoRepository;
import com.pharmhands.repositories.UserRepository;
import com.pharmhands.services.EmailService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PatientProfileController {

    private final UserRepository userDao;

    private final PatientInfoRepository patientDao;


    private final EmailService emailService;

    public PatientProfileController(UserRepository userDao, PatientInfoRepository patientDao, EmailService emailService){
        this.userDao = userDao;
        this.patientDao = patientDao;
        this.emailService = emailService;
    }

    @GetMapping("/patientProfile/{id}")
    public String patientProfileLoad(@PathVariable long id, Model model){
        model.addAttribute("patient",userDao.getOne(id));
        model.addAttribute("patientInfo", patientDao.findByUser(userDao.getOne(id)));
        return "views/patientProfile";
    }

    @PostMapping("/patientProfile/{id}")
    public String emailSend(@PathVariable long id) {
        emailService.prepareAndSend(userDao.getOne(id), "Message Sent", "Dear"+userDao.getOne(id).getFull_name()+"Your Pharmacist will be notified &  should reach out to you in 2 business days .Thank you");
        return "redirect:/";
    }


}
