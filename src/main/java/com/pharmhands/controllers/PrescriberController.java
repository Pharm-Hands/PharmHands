package com.pharmhands.controllers;

import com.pharmhands.models.Drugs;
import com.pharmhands.models.Prescriptions;
import com.pharmhands.models.User;
import com.pharmhands.repositories.*;
import com.pharmhands.services.EmailService;
import com.pharmhands.services.UserService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
@Secured({"ROLE_DOCTOR"})
@Controller
public class PrescriberController{

    private final UserService userService;
    private final EmailService emailService;
    private final UserRepository userDao;
    private final PrescriberInfoRepository prescriberDao;
    private final PrescriptionsRepository prescriptionDao;
    private final PatientInfoRepository patientDao;
    private final DrugsRepository drugsDao;

    public PrescriberController(UserService userService, EmailService emailService, UserRepository userDao, PrescriberInfoRepository prescriberDao, PrescriptionsRepository prescriptionDao, PatientInfoRepository patientDao, DrugsRepository drugsDao) {
        this.userService = userService;
        this.emailService = emailService;
        this.userDao = userDao;
        this.prescriberDao = prescriberDao;
        this.prescriptionDao = prescriptionDao;
        this.patientDao = patientDao;
        this.drugsDao = drugsDao;
    }

        @GetMapping("/doctorProfile/prescription-create")
        public String registerForm(Model model){
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            model.addAttribute("user", user);
            model.addAttribute("prescription", new Prescriptions());
            model.addAttribute("doctor_user", userDao.getOne(userService.loggedInUser().getId()));
            model.addAttribute("patient_user", new User());
            model.addAttribute("drugs", new Drugs());
            return "views/prescriptionForm";
        }

        @PostMapping("/doctorProfile/prescription-create")
        public String submitForm(@ModelAttribute Prescriptions prescription, @RequestParam(name = "fullName") String fullName, @RequestParam(name= "drugName") String drugName){
            User patientUser = userDao.findByUserFullName(fullName);
            //why is findById Optional Type?
            User doctorUser = userDao.getOne(userService.loggedInUser().getId());

            prescription.setPatient(patientUser);
            prescription.setDoctor(doctorUser);
            Drugs drug = drugsDao.findDrugsByDrug_name(drugName);
            prescription.setDrug(drug);

            long d = System.currentTimeMillis();
            Date date = new Date(d);
            prescription.setCreated_at(date);


            prescription.setIs_verified(0);
            prescription.setIs_deleted(0);

            prescriptionDao.save(prescription);
            return "redirect:/doctorProfile/"+ doctorUser.getId() +"/#tab2";
        }


    @GetMapping("doctorProfile/{id}/edit")
    public String editDoctorInfoForm(Model model, @PathVariable long id){
        User user = userDao.getOne(id);
        model.addAttribute("user", user);
        return "views/doctor/doctorinfoedit";
    }

    @PostMapping("/doctorProfile/{id}/edit")
    public String editDoctorInfo(@PathVariable long id ,@ModelAttribute User user){
        User loggedIn = userService.loggedInUser();

        user.setFullName(user.getFullName());
        user.setEmail(user.getEmail());

        user.setPassword(loggedIn.getPassword());
        user.setPhone_number(loggedIn.getPhone_number());
        user.setUsername(loggedIn.getUsername());
        user.setIs_deleted(loggedIn.getIs_deleted());
        user.setRole(loggedIn.getRole());

        userDao.save(user);
        return "redirect:/doctorProfile/" + id;

    }
}
/**     BUFFER
 *       prescription.setDays_supply(prescription.getDays_supply());
 * //            prescription.setDose(prescription.getDose());
 * //            prescription.setDrug(prescription.getDrug());
 * //            prescription.setDrug_Strength(prescription.getDrug_Strength());
 * //            prescription.setQuantity(prescription.getQuantity());
 * //            prescription.setSig(prescription.getSig());
 *          int doctorId = (int) prescriberDao.findByUser(userDao.getOne(id)).getId();*/

