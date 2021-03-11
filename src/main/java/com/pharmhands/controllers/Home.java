package com.pharmhands.controllers;

import com.pharmhands.services.EmailService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
class Home {

    private final EmailService emailService;

    public Home(EmailService emailService){
        this.emailService = emailService;
    }

    @GetMapping("/")
    @ResponseBody
    public String hello() {
        System.out.println("hi");
        return "Hello from Spring!";
    }

    @GetMapping("/email")
    @ResponseBody
    public String emailSend(){
        emailService.prepareAndSend("test", "hello from pharmhands");
        return "email page";
    }
}