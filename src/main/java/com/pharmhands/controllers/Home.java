package com.pharmhands.controllers;

import com.pharmhands.services.EmailService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
class Home {

    private final EmailService emailService;

    public Home(EmailService emailService){
        this.emailService = emailService;
    }

    @GetMapping("/")
    public String mainPage(Model model) {

        return "views/landingPage";
    }

    @GetMapping("/email")
    @ResponseBody
    public String emailSend(){
        emailService.prepareAndSend("test", "hello from pharmhands");
        return "email page";
    }
}
