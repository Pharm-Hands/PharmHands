package com.pharmhands.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PrescriberController {

        @GetMapping("/profile/prescription-create")
        public String registerForm(){

             return "views/prescriptionForm";
//            return "redirect:/profile";
        }
}
