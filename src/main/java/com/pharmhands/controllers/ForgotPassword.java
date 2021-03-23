package com.pharmhands.controllers;

import com.pharmhands.models.User;
import com.pharmhands.repositories.*;
import com.pharmhands.services.EmailService;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ForgotPassword {
    private final UserRepository userDao;
    private final UserRolesRepository userRolesDao;
    private final PrescriberInfoRepository prescriberInfoDao;
    private final PrescriptionsRepository prescriptionsDao;
    private final PatientInfoRepository patientInfoDao;
    private final EmailService emailService;

    public ForgotPassword(UserRepository userDao, UserRolesRepository userRolesDao, PrescriberInfoRepository prescriberInfoDao, PrescriptionsRepository prescriptionsDao, PatientInfoRepository patientInfoDao,EmailService emailService) {
        this.userDao = userDao;
        this.userRolesDao = userRolesDao;
        this.prescriberInfoDao = prescriberInfoDao;
        this.prescriptionsDao = prescriptionsDao;
        this.patientInfoDao = patientInfoDao;
        this.emailService = emailService;
    }

//
//    @GetMapping("/forgotPassword")
//    public String forgotPasswordForm(Model model){
//        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        model.addAttribute("user",user);
//    return "views/forgotPassword";
//    }
//
//
//    @PostMapping("/forgotPassword")
//    public String password( User user) {
//        User userExist = userDao.findByEmailExists(user.getEmail());
//        emailService.prepareAndSend(userExist," Reset password","You clicked the reset password");
//            return "views/forgotPassword";
//        }

    }

