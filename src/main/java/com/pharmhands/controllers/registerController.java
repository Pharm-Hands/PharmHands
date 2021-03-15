package com.pharmhands.controllers;


import com.pharmhands.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


//Takes you to the registration page.
@Controller
public class registerController {

    @GetMapping("/register")
    public String registerForm(Model model){
        model.addAttribute("user", new User());
        return "views/register";
    }


//    Takes you to the patient registration form
    @GetMapping("/patientRegister")
     public String patientRegister(Model model){
        return "views/patientRegister";
    }

//    Takes you to the doctor registration form
    @GetMapping("/doctorRegister")
    public String doctorRegister(Model model){
        return "views/doctorRegister";
    }
//    Takes you to the pharmacist registration form
    @GetMapping("/pharmRegister")
    public String pharmRegister(Model model){
        return "pharmacistRegister";
    }

//    @GetMapping("/sign-up")
//    public String showSignupForm(Model model){
//        model.addAttribute("user", new User());
//        return "users/sign-up";
//    }
//
//    @PostMapping("/sign-up")
//    public String saveUser(@ModelAttribute User user){
//        String hash = passwordEncoder.encode(user.getPassword());
//        user.setPassword(hash);
//        users.save(user);
//        return "redirect:/login";
//    }
}
