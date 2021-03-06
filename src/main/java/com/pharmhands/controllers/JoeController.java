package com.pharmhands.controllers;

import com.pharmhands.models.Fills;
import com.pharmhands.models.PrescriptionRequests;
import com.pharmhands.models.Prescriptions;
import com.pharmhands.models.User;
import com.pharmhands.repositories.*;
import com.pharmhands.services.EmailService;
import com.pharmhands.services.PrescriptionService;
import com.pharmhands.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;
import java.util.Calendar;
import java.util.List;

@Controller
public class JoeController {

    private final EmailService emailService;

    private final UserRepository userDao;

    private final PrescriptionsRepository prescriptionsDao;

    private final PrescriberInfoRepository prescriberDao;

    private final FillsRepository fillsDao;

    private final PrescriptionRequestsRepository requestsDao;

    private final UserService userService;

    private final PrescriptionService prescriptionService;

    public JoeController(EmailService emailService, UserRepository userDao, PrescriptionsRepository prescriptionsDao, PrescriberInfoRepository prescriberDao, FillsRepository fillsDao, PrescriptionRequestsRepository requestsDao, UserService userService, PrescriptionService prescriptionService) {
        this.emailService = emailService;
        this.userDao = userDao;
        this.prescriptionsDao = prescriptionsDao;
        this.prescriberDao = prescriberDao;
        this.fillsDao = fillsDao;
        this.requestsDao = requestsDao;
        this.userService = userService;
        this.prescriptionService = prescriptionService;
    }

    @GetMapping("/doctorProfile/{id}")
    public String doctorProfile(Model model, @PathVariable long id) {
        User user = userService.loggedInUser();
        model.addAttribute("user", user);
        model.addAttribute("doctor", userDao.getOne(id));
        model.addAttribute("prescriptions", prescriptionsDao.findAllByPrescriberId(id));
        model.addAttribute("doctorInfo", prescriberDao.findByUser(userDao.getOne(id)));
        return "views/doctor/doctorProfile";
    }

    @GetMapping("/prescription/{id}")
    public String viewPrescription(Model model, @PathVariable long id) {
        model.addAttribute("prescription", prescriptionsDao.getOne(id));
        model.addAttribute("user", userService.loggedInUser());
        return "views/prescription";
    }

    @PostMapping("/prescription/{id}/fill")
    public String fillPrescription(@PathVariable long id, RedirectAttributes redir) {
        Prescriptions prescription = prescriptionsDao.getOne(id);

        if(!prescriptionService.fillEligible(id)){
            redir.addFlashAttribute("fillMessage", "Sorry, this prescription is not eligible to be filled. The days supply since last fill has not run out yet.");
        }else{
            Fills fill = new Fills();
            fill.setUser(userService.loggedInUser());
            Calendar now = Calendar.getInstance();
            now.setTime(new Date());
            fill.setFill_date(now.getTime());
            fill.setFill_number(1);
            fill.setPrescription(prescription);
            fillsDao.save(fill);

            for(PrescriptionRequests request : requestsDao.findAllByPrescriptionId(prescription.getId())){
                System.out.println(request.getId());
                request.setIs_Fulfilled(1);
                requestsDao.save(request);
            }

            redir.addFlashAttribute("fillMessage", "You have successfully filled the prescription for " + prescription.getPatient().getFullName());
        }
        return "redirect:/prescription/{id}";
    }

    @PostMapping("/prescription/{id}/verify")
    public String verifyPrescription(@PathVariable long id, RedirectAttributes redir) {
        Prescriptions prescription = prescriptionsDao.getOne(id);
        prescription.setIs_verified(1);
        prescriptionsDao.save(prescription);

        redir.addFlashAttribute("fillMessage", "You have successfully verified the prescription for " + prescription.getPatient().getFullName());
        return "redirect:/prescription/{id}";
    }

    @PostMapping("/prescription/{id}/verifyAndFill")
    public String verifyAndFill(@PathVariable long id, RedirectAttributes redir) {
        Prescriptions prescription = prescriptionsDao.getOne(id);
        prescription.setIs_verified(1);
        prescriptionsDao.save(prescription);

        if(!prescriptionService.fillEligible(id)){
            redir.addFlashAttribute("fillMessage", "Sorry, this prescription is not eligible to be filled. The days supply since last fill has not run out yet. Your prescription has still been verified");
        }else{
            Fills fill = new Fills();
            fill.setUser(userService.loggedInUser());
            Calendar now = Calendar.getInstance();
            now.setTime(new Date());
            fill.setFill_date(now.getTime());
            fill.setFill_number(1);
            fill.setPrescription(prescription);
            fillsDao.save(fill);

            for(PrescriptionRequests request : requestsDao.findAllByPrescriptionId(prescription.getId())){
                System.out.println(request.getId());
                request.setIs_Fulfilled(1);
                requestsDao.save(request);
            }

            redir.addFlashAttribute("fillMessage", "You have successfully verified and filled the prescription for " + prescription.getPatient().getFullName());
        }
        return "redirect:/prescription/{id}";

    }

    @GetMapping("/prescription-request/{prescriptionId}")
    public String requestForm(@PathVariable long prescriptionId, Model model){
        PrescriptionRequests request = new PrescriptionRequests();
        model.addAttribute("request", request);
        model.addAttribute("prescription", prescriptionsDao.getOne(prescriptionId));

        return "views/patient/requestForm";
    }

    @PostMapping("/prescription-request/{prescriptionId}")
    public String submitRequest(@ModelAttribute PrescriptionRequests request, @PathVariable long prescriptionId, RedirectAttributes redir){
        if(!prescriptionService.fillEligible(prescriptionId)){
            redir.addFlashAttribute("requestMessage", "Sorry, this prescription is not eligible to be filled. The days supply since last fill has not run out yet. Please wait to request a fill once this prescription becomes eligible.");
        }else{
            long d = System.currentTimeMillis();
            Date date = new Date(d);
            request.setCreated_at(date);
            request.setIs_Fulfilled(0);
            request.setPatient(userService.loggedInUser());
            request.setPrescription(prescriptionsDao.getOne(prescriptionId));

            requestsDao.save(request);
            redir.addFlashAttribute("requestMessage", "You have successfully requested a fill for your prescription of " + prescriptionsDao.getOne(prescriptionId).getDrug().getDrug_name() + "!");
        }
        return "redirect:/patientProfile/" + userService.loggedInUser().getId();
    }

    @GetMapping("/prescription-list")
    public String prescriptionList(Model model){
        User user = userService.loggedInUser();
        List<Prescriptions> prescriptionsList = null;

        switch (userService.loggedInUser().getRole().getRole_name()){
            case "ROLE_PATIENT":
                prescriptionsList = prescriptionsDao.findAllByPatientId(user.getId());
                break;
            case "ROLE_PHARMACIST":
                prescriptionsList =  prescriptionsDao.findAll();
                break;
            case "ROLE_DOCTOR":
                prescriptionsList =  prescriptionsDao.findAllByPrescriberId(user.getId());
                break;
        }

        model.addAttribute("prescriptions", prescriptionsList);
        model.addAttribute("user", user);
        return "views/prescription-list";
    }

    @GetMapping("/request-list")
    public String requestList(Model model){
        User user = userService.loggedInUser();
        model.addAttribute("user", user);
        model.addAttribute("requests", requestsDao.findAllUnfulfilled());

        return "views/request-list";
    }
}


/**   Prescriptions prescription = prescriptionsDao.getOne(prescriptionId);
 //        get the most recent fill date unless no fills have been performed
 //        Calendar fillCheck = Calendar.getInstance();
 //        Calendar now = Calendar.getInstance();
 //        now.setTime(new Date());
 //        System.out.println(fillsDao.fillCount(prescriptionId));
 //        if (fillsDao.fillCount(prescriptionId) != 0) {
 //            fillCheck.setTime(fillsDao.mostRecent(prescriptionId).getFill_date());
 //            fillCheck.add(Calendar.DATE, prescription.getDays_supply());
 //
 //       check the current date against the most recent fill date plus days supply and redirect if it is within the range
 //            if (fillCheck.getTime().after(now.getTime())) {
 //                redir.addFlashAttribute("requestMessage", "Sorry, this prescription is not eligible to be filled. The days supply since last fill has not run out yet. Please wait to request a fill once this prescription becomes eligible.");
 //                return "redirect:/patientProfile/" + userService.loggedInUser().getId();
 //            }
 //        }*/