package com.pharmhands.controllers;

import com.pharmhands.repositories.UserRepository;
import com.pharmhands.services.EmailService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
class Home {

    private final EmailService emailService;
    private final UserRepository userDao;

    public Home(EmailService emailService, UserRepository userDao) {
        this.emailService = emailService;
        this.userDao = userDao;
    }

    @GetMapping("/")
    public String mainPage(Model model) {

        return "views/landingPage";
    }

    @GetMapping("/email")
    @ResponseBody
    public String emailSend() {
        emailService.prepareAndSend(userDao.getOne((long) 2), "test", "hello from pharmhands");
        return "email page";
    }
}
