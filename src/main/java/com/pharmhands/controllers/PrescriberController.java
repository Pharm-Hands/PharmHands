package com.pharmhands.controllers;

import com.pharmhands.models.Drugs;
import com.pharmhands.models.Prescriptions;
import com.pharmhands.models.User;
import com.pharmhands.repositories.*;
import com.pharmhands.services.EmailService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;

@Controller
public class PrescriberController{

    private final EmailService emailService;
    private final UserRepository userDao;
    private final PrescriberInfoRepository prescriberDao;
    private final PrescriptionsRepository prescriptionDao;
    private final PatientInfoRepository patientDao;
    private final DrugsRepository drugsDao;

    public PrescriberController(EmailService emailService, UserRepository userDao, PrescriberInfoRepository prescriberDao, PrescriptionsRepository prescriptionDao, PatientInfoRepository patientDao, DrugsRepository drugsDao) {
        this.emailService = emailService;
        this.userDao = userDao;
        this.prescriberDao = prescriberDao;
        this.prescriptionDao = prescriptionDao;
        this.patientDao = patientDao;
        this.drugsDao = drugsDao;
    }

        @GetMapping("/{id}/doctorProfile/prescription-create")
        public String registerForm(Model model, @PathVariable long id){
            model.addAttribute("prescription", new Prescriptions());
            model.addAttribute("user", userDao.getOne(id));
            return "views/prescriptionForm";
        }

        @PostMapping("/{id}/doctorProfile/prescription-create")
        public String submitForm(Model model, @PathVariable long id, @ModelAttribute Prescriptions prescription, @RequestParam(name = "name") String name, @RequestParam(name= "phone_number") String phone, @RequestParam(name= "drugName") String drugName){
            User patientUserId = userDao.findByUserFullNameAndPhone(name, phone);
            int doctorId = (int) prescriberDao.findByUser(userDao.getOne(id)).getId();
            prescription.setUser(patientUserId);
            prescription.setPrescriber_id(doctorId);
            prescription.setDrug_form(prescription.getDrug_form());
            Drugs drugs = drugsDao.findDrugsByDrug_name(drugName);
            prescription.setDrug(drugs);
            long d = System.currentTimeMillis();
            Date date = new Date(d);
            prescription.setCreated_at(date);
            prescription.setDays_supply(prescription.getDays_supply());
            prescription.setDose(prescription.getDose());
            prescription.setDrug(prescription.getDrug());
            prescription.setDrug_Strength(prescription.getDrug_Strength());
            prescription.setIs_verified(0);
            prescription.setQuantity(prescription.getQuantity());
            prescription.setSig(prescription.getSig());
            prescription.setIs_deleted(0);
            prescriptionDao.save(prescription);
            return "redirect:/{id}/doctorProfile";
        }
//        @GetMapping("/profile/prescription-create/1")
//    public String registerForm1(Model model, @PathVariable long id) {
//            model.addAttribute("doctor", userDao.getOne(id));
//            model.addAttribute("doctorInfo", prescriberDao.findByUser(userDao.getOne(id)));
//            return "views/prescriptionForm1";
//        }

//        @GetMapping("/profile/prescription-create/2")
//    public String registerForm2() {
//            return "views/prescriptionForm2";
//        }
//
//        @PostMapping("/profile/prescription-create/2")
//        public String registerForm2() {
//
//        }
//
//        @GetMapping("/profile/prescription-create/3")
//    public String registerForm3() {
//            return "views/prescriptionForm3";
//        }
}
