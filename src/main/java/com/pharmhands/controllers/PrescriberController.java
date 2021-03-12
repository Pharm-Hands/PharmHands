package com.pharmhands.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PrescriberController {

        @GetMapping("/profile/prescription_form")
        public String registerForm(){
            return "views/prescriptionForm";
        }
}
