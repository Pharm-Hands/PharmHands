package com.pharmhands.controllers;

import com.pharmhands.models.User;
import com.pharmhands.repositories.*;
import com.pharmhands.services.EmailService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ForgotPassword {
    private final UserRepository userDao;
    private final UserRolesRepository userRolesDao;
    private final PrescriberInfoRepository prescriberInfoDao;
    private final PrescriptionsRepository prescriptionsDao;
    private final PatientInfoRepository patientInfoDao;
    private final EmailService emailService;

    public ForgotPassword(UserRepository userDao, UserRolesRepository userRolesDao, PrescriberInfoRepository prescriberInfoDao, PrescriptionsRepository prescriptionsDao, PatientInfoRepository patientInfoDao, EmailService emailService) {
        this.userDao = userDao;
        this.userRolesDao = userRolesDao;
        this.prescriberInfoDao = prescriberInfoDao;
        this.prescriptionsDao = prescriptionsDao;
        this.patientInfoDao = patientInfoDao;
        this.emailService = emailService;
    }

    //
    @GetMapping("/forgotPassword")
    public String forgotPassword(Model model) {
        model.addAttribute(new User());
        return "views/forgotPassword";
    }

    //
    @PostMapping("/forgotPassword")
    public String password(@RequestParam(name = "email") String email, User user) {

        if (!user.getEmail().equals(email)) {
            return "redirect:forgotPassword?email";
        }
        User userObj = userDao.findByEmail(user.getEmail());

        if (userObj != null) {
            emailService.prepareAndSend(userObj, " Reset password", "You clicked the reset password");
        }
        return "views/forgotPassword";
    }

}

