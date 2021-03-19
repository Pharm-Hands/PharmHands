package com.pharmhands.controllers;

import com.pharmhands.models.PatientInfo;
import com.pharmhands.models.User;
import com.pharmhands.repositories.PatientInfoRepository;
import com.pharmhands.repositories.PrescriptionsRepository;
import com.pharmhands.repositories.UserRepository;
import com.pharmhands.services.EmailService;
import com.pharmhands.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PatientProfileController {

    private final UserRepository userDao;

    private final PatientInfoRepository patientDao;

    private final PrescriptionsRepository prescriptionsDao;

    private final EmailService emailService;

    private final UserService userService;

    public PatientProfileController(UserRepository userDao, PatientInfoRepository patientDao, PrescriptionsRepository prescriptionsDao, EmailService emailService,UserService userService){
        this.userDao = userDao;
        this.patientDao = patientDao;
        this.prescriptionsDao = prescriptionsDao;
        this.emailService = emailService;
        this.userService=userService;
    }

    @GetMapping("/patientProfile/{id}")
    public String patientProfileView(@PathVariable long id, Model model){
        model.addAttribute("patient",userDao.getOne(id));
        model.addAttribute("patientInfo", patientDao.findByUser(userDao.getOne(id)));
//        model.addAttribute("prescriber",prescriptionsDao.getOne(id));
        return "views/patient/patientProfile";
    }

    @GetMapping("/patientProfile/{id}/edit")
    public String editProfileForm(@PathVariable long id, Model model) {
        User loggedUser = userService.loggedInUser();
        if (loggedUser == null) {
            return "redirect:/";
        }
        User user = userDao.getOne(loggedUser.getId());
        model.addAttribute("patient", user);
        model.addAttribute("patientInfo", patientDao.findByUser(userDao.getOne(id)));
        return "views/patient/EditPatientProfile";
    }
    @PostMapping("/patientProfile/{id}/edit")
    public String updatePatient(@ModelAttribute User patient,@PathVariable long id,Model model) {
        User loggedUser = userService.loggedInUser();
        model.addAttribute("patientInfo", patientDao.findByUser(userDao.getOne(id)));
        PatientInfo patientInfo =patientDao.getOne(id);
        patient = userDao.getOne(loggedUser.getId());
        patient.setUsername(patient.getUsername());
        patientInfo.setAddress(patientInfo.getAddress());
        patientInfo.setDob(patientInfo.getDob());
        userDao.save(patient);
        patientDao.save(patientInfo);
        return "redirect:/patientProfile/{id}";
    }

    @PostMapping("/patientProfile/{id}")
    public String emailSend(@PathVariable long id) {
        emailService.prepareAndSend(userDao.getOne(id), "Message Sent", "Dear"+userDao.getOne(id).getFull_name()+"Your Pharmacist will be notified &  should reach out to you in 2 business days .Thank you");
        return "redirect:/";
    }

}
