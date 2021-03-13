package com.pharmhands.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PhViewController {
    @GetMapping("/profile/phViewProfile")
    public String phView(){
        return "views/phViewProfile";
    }
}
