package com.pharmhands.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


//Takes you to the registration page.
@Controller
public class registerController {

    @GetMapping("/register")
    public String registerForm(){
        return "views/register";
    }
}
