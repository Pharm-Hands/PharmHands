package com.pharmhands.controllers;

import com.pharmhands.models.User;
import com.pharmhands.repositories.PatientInfoRepository;
import com.pharmhands.repositories.PrescriberInfoRepository;
import com.pharmhands.repositories.UserRepository;
import com.pharmhands.services.EmailService;
import com.pharmhands.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PatientProfileController {

    private final UserRepository userDao;

    private final PatientInfoRepository patientDao;

    private final UserService userService;

    public PatientProfileController( UserRepository userDao, PatientInfoRepository patientDao, UserService userService){
        this.userDao = userDao;
        this.patientDao = patientDao;
        this.userService= userService;
    }

    @GetMapping("/{id}/Patientview")
    public String patientProfileLoad(@PathVariable long id, Model model){

        User currUser = userService.loggedInUser();

        model.addAttribute("patient_info",userDao.getOne(id));
        model.addAttribute("patientInfo", patientDao.findByUser(userDao.getOne(id)));
        return "views/patientProfile";
    }


}
