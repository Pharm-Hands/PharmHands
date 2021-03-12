package com.pharmhands.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ForgotPassword {

    @GetMapping( "/forgotPassword")
    public String password(){
        return "views/forgotPassword";
    }


}
