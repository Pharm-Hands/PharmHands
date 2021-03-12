package com.pharmhands.controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


public class ForgotPassword {

    @GetMapping( "/forgotPassword")
    public String password(Model model){
        return "/forgotPassword";
    }


}
