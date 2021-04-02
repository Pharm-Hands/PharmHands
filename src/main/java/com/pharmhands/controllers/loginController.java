package com.pharmhands.controllers;


import com.pharmhands.repositories.*;
import com.pharmhands.services.EmailService;
import com.pharmhands.services.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class loginController {
    private final EmailService emailService;

    private final UserRepository userDao;

    private final PrescriptionsRepository prescriptionsDao;

    private final PrescriberInfoRepository prescriberDao;

    private final FillsRepository fillsDao;

    private final PrescriptionRequestsRepository requestsDao;

    private final UserService userService;

    public loginController(EmailService emailService, UserRepository userDao, PrescriptionsRepository prescriptionsDao, PrescriberInfoRepository prescriberDao, FillsRepository fillsDao, PrescriptionRequestsRepository requestsDao, UserService userService) {
        this.emailService = emailService;
        this.userDao = userDao;
        this.prescriptionsDao = prescriptionsDao;
        this.prescriberDao = prescriberDao;
        this.fillsDao = fillsDao;
        this.requestsDao = requestsDao;
        this.userService = userService;
    }

    @GetMapping("/login")
    public String loginForm(Model model) {
        return "views/login";
    }

    @GetMapping("/patientProfile")
    @PreAuthorize("isAuthenticated()")
    public String patientLogin(Model model) {
        model.addAttribute("patient", userService.loggedInUser());
        return "views/patient/patientProfile";
    }

}
