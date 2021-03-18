package com.pharmhands.controllers;

import com.pharmhands.models.Fills;
import com.pharmhands.models.Prescriptions;
import com.pharmhands.repositories.FillsRepository;
import com.pharmhands.repositories.PrescriberInfoRepository;
import com.pharmhands.repositories.PrescriptionsRepository;
import com.pharmhands.repositories.UserRepository;
import com.pharmhands.services.EmailService;
import com.pharmhands.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Date;
import java.util.Calendar;

@Controller
public class JoeController {

    private final EmailService emailService;

    private final UserRepository userDao;

    private final PrescriptionsRepository prescriptionsDao;

    private final PrescriberInfoRepository prescriberDao;

    private final FillsRepository fillsDao;

    private final UserService userService;

    public JoeController(EmailService emailService, UserRepository userDao, PrescriptionsRepository prescriptionsDao, PrescriberInfoRepository prescriberDao, FillsRepository fillsDao, UserService userService) {
        this.emailService = emailService;
        this.userDao = userDao;
        this.prescriptionsDao = prescriptionsDao;
        this.prescriberDao = prescriberDao;
        this.fillsDao = fillsDao;
        this.userService = userService;
    }

    @GetMapping("/{id}/doctorProfile")
    public String doctorProfile(Model model, @PathVariable long id) {
        model.addAttribute("doctor", userDao.getOne(id));
        model.addAttribute("prescriptions", prescriptionsDao.findAllByPrescriberId(id));
        model.addAttribute("doctorInfo", prescriberDao.findByUser(userDao.getOne(id)));
        return "views/doctor/doctorProfile";
    }

    @GetMapping("/prescription/{id}")
    public String viewPrescription(Model model, @PathVariable long id) {
        model.addAttribute("prescription", prescriptionsDao.getOne(id));
        return "views/prescription";
    }

    @PostMapping("/prescription/{id}/fill")
    public String fillPrescription(@PathVariable long id) {
        Prescriptions prescription = prescriptionsDao.getOne(id);

//        get the most recent fill date unless no fills have been performed
        Calendar fillCheck = Calendar.getInstance();
        Calendar now = Calendar.getInstance();
        now.setTime(new Date());
        System.out.println(fillsDao.fillCount(id));
        if (fillsDao.fillCount(id) != 0) {
            fillCheck.setTime(fillsDao.mostRecent(id).getFill_date());
            fillCheck.add(Calendar.DATE, prescription.getDays_supply());

            System.out.println("now");
            System.out.println(now.getTime());
            System.out.println("fill");
            System.out.println(fillCheck.getTime());
//        check the current date against the most recent fill date plus days supply and redirect if it is within the range
            if (fillCheck.getTime().after(now.getTime())) {
                return "redirect:/";
            }
        }

        Fills fill = new Fills();
        fill.setUser(userService.loggedInUser());
        fill.setFill_date(now.getTime());
        fill.setFill_number(1);
        fill.setPrescription(prescription);
        fillsDao.save(fill);

        return "redirect:/prescription/{id}";
    }

    @PostMapping("/prescription/{id}/verify")
    public String verifyPrescription(@PathVariable long id) {
        Prescriptions prescription = prescriptionsDao.getOne(id);
        prescription.setIs_verified(1);
        prescriptionsDao.save(prescription);

        return "redirect:/prescription/{id}";
    }

    @PostMapping("/prescription/{id}/verifyAndFill")
    public String verifyAndFill(@PathVariable long id) {
        Prescriptions prescription = prescriptionsDao.getOne(id);
        prescription.setIs_verified(1);
        prescriptionsDao.save(prescription);

//        get the most recent fill date unless no fills have been performed
        Calendar fillCheck = Calendar.getInstance();
        Calendar now = Calendar.getInstance();
        now.setTime(new Date());
        System.out.println(fillsDao.fillCount(id));
        if (fillsDao.fillCount(id) != 0) {
            fillCheck.setTime(fillsDao.mostRecent(id).getFill_date());
            fillCheck.add(Calendar.DATE, prescription.getDays_supply());

            System.out.println("now");
            System.out.println(now.getTime());
            System.out.println("fill");
            System.out.println(fillCheck.getTime());
//        check the current date against the most recent fill date plus days supply and redirect if it is within the range
            if (fillCheck.getTime().after(now.getTime())) {
                return "redirect:/";
            }
        }

        Fills fill = new Fills();
        fill.setUser(userService.loggedInUser());
        fill.setFill_date(now.getTime());
        fill.setFill_number(1);
        fill.setPrescription(prescription);
        fillsDao.save(fill);

        return "redirect:/prescription/{id}";

    }
}
