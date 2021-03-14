//package com.pharmhands.controllers;
//
//import com.pharmhands.models.User;
//import com.pharmhands.services.UserService;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//
//@Controller
//public class PatientProfileController {
//    @GetMapping("/profile/Patientview")
//    public String patientProfileLoad(Model model){
//
//        User currentUser= userService.loggedInUser();
//
//        model.addAttribute("patient_info",patient_info);
//        return "views/patientProfile";
//    }
//
//
//}
