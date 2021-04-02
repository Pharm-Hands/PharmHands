package com.pharmhands.controllers;

import com.pharmhands.models.PatientInfo;
import com.pharmhands.models.User;
import com.pharmhands.repositories.PatientInfoRepository;
import com.pharmhands.repositories.PrescriptionsRepository;
import com.pharmhands.repositories.UserRepository;
import com.pharmhands.services.EmailService;
import com.pharmhands.services.UserService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Date;

@Controller
@Secured({"ROLE_PATIENT"})
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
        model.addAttribute("prescriptions", prescriptionsDao.findAllByPatientId(id));

        return "views/patient/patientProfile";
    }

    @GetMapping("/patientProfile/{id}/edit")
    public String editPatientInfoForm(Model model, @PathVariable long id){
        User user = userDao.getOne(id);
        model.addAttribute("user", user);
        return "views/patient/EditPatientProfile";
    }

    @PostMapping("/patientProfile/{id}/edit")
    public String editPatientInfo(@ModelAttribute User user, @PathVariable long id){
        User loggedIn = userService.loggedInUser();


        user.setFullName(user.getFullName());
        user.setEmail(user.getEmail());
        user.setPassword(loggedIn.getPassword());
        user.setPhone_number(loggedIn.getPhone_number());
        user.setUsername(loggedIn.getUsername());
        user.setIs_deleted(loggedIn.getIs_deleted());
        user.setRole(loggedIn.getRole());

        userDao.save(user);
        return "redirect:/patientProfile/{id}/edit02";
    }

    @GetMapping("/patientProfile/{id}/edit02")
    public String editPatientInfoAddress(Model model, @PathVariable long id){
        User user = userDao.getOne(id);

        model.addAttribute("user", user );
        model.addAttribute("patientInfo", patientDao.findByUser(userDao.getOne(id)));
        return "views/patient/editPatientProfile02";
    }

    @PostMapping("/patientProfile/{patientId}/edit02")
    public String editPatientInfoAddress(@ModelAttribute User user, @ModelAttribute PatientInfo patientInfo, @PathVariable long patientId){
        User loggedInPatient = userService.loggedInUser();
        Date dob = patientDao.findByUser(loggedInPatient).getDob();
        String sex = patientDao.findByUser(loggedInPatient).getSex();

        patientInfo.setAddress(patientInfo.getAddress());
        patientInfo.setCity(patientInfo.getCity());
        patientInfo.setDob(dob);
        patientInfo.setState(patientInfo.getState());
        patientInfo.setZip(patientInfo.getZip());
        patientInfo.setUser(loggedInPatient);

        patientInfo.setSex(sex);

        patientDao.save(patientInfo);
        return "redirect:/patientProfile/" + patientId;
    }


    @PostMapping("/patientProfile/{id}")
    public String emailSend(@PathVariable long id) {
        emailService.prepareAndSend(userDao.getOne(id), "Message Sent", "Dear"+userDao.getOne(id).getFullName()+"Your Pharmacist will be notified &  should reach out to you in 2 business days .Thank you");
        return "redirect:/";
    }

}


/**BUFFER*/

//    @GetMapping("/patientProfile/{id}/edit")
//    public String editPatientInfoForm(@PathVariable long id, Model model) {
//        User loggedUser = userDao.getOne(id);
//        if (loggedUser == null) {
//            return "redirect:/";
//        }
//        User user = userDao.getOne(loggedUser.getId());
//        model.addAttribute("patient", user);
//        model.addAttribute("patientInfo", patientDao.findByUser(userDao.getOne(id)));
//        return "views/patient/EditPatientProfile";
//    }
