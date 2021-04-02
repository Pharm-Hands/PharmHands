package com.pharmhands.controllers;


import com.pharmhands.models.PatientInfo;
import com.pharmhands.models.PrescriberInfo;
import com.pharmhands.models.User;
import com.pharmhands.repositories.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


//Takes you to the registration page.
@Controller
public class registerController {

    private final UserRepository userDao;
    private final UserRolesRepository userRolesDao;
    private final PrescriberInfoRepository prescriberInfoDao;
    private final PrescriptionsRepository prescriptionsDao;
    private final PatientInfoRepository patientInfoDao;
    private final FillsRepository fillsDao;
    private final DrugsRepository drugsDao;
    private final PasswordEncoder encoder;

    public registerController(UserRepository userDao, UserRolesRepository userRolesDao, PrescriberInfoRepository prescriberInfoDao, PrescriptionsRepository prescriptionsDao, PatientInfoRepository patientInfoDao, FillsRepository fillsDao, DrugsRepository drugsDao, PasswordEncoder encoder) {

        this.userDao = userDao;
        this.userRolesDao = userRolesDao;
        this.prescriberInfoDao = prescriberInfoDao;
        this.prescriptionsDao = prescriptionsDao;
        this.patientInfoDao = patientInfoDao;
        this.fillsDao = fillsDao;
        this.drugsDao = drugsDao;
        this.encoder = encoder;
    }

    @GetMapping("/register")
    public String registerForm(Model model){
        model.addAttribute("user", new User());
        return "views/register";
    }

//    Takes you to the patient registration form
    @GetMapping("/patientRegister")
     public String patientRegister(Model model){
        model.addAttribute("patient", new User());
        model.addAttribute("info", new PatientInfo());
        return "views/patient/patientRegister";
    }

//    Takes you to the doctor registration form
    @GetMapping("/doctorRegister")
    public String doctorRegister(Model model){
        model.addAttribute("doctor", new User());
        model.addAttribute("info", new PrescriberInfo());
        return "views/doctor/doctorRegister";
    }

//    Takes you to the pharmacist registration form
    @GetMapping("/pharmRegister")
    public String pharmRegister(Model model){
        model.addAttribute("pharmacist", new User());
        return "views/pharmacist/pharmacistRegister";
    }

    @PostMapping("/pharmRegister")
    public String pharmSave(@ModelAttribute User pharmacist){
        String hashPharm = encoder.encode(pharmacist.getPassword());
        pharmacist.setPassword(hashPharm);

        pharmacist.setFullName(pharmacist.getFullName());
        pharmacist.setIs_deleted(0);
        pharmacist.setPhone_number(pharmacist.getPhone_number());
        pharmacist.setUsername(pharmacist.getUsername());
        pharmacist.setEmail(pharmacist.getEmail());
        pharmacist.setRole(userRolesDao.findByRoleName("ROLE_PHARMACIST"));
        userDao.save(pharmacist);
        return "redirect:/";
    }

    @PostMapping("doctorRegister")
    public String doctorSave(@ModelAttribute User doctor, @ModelAttribute PrescriberInfo info){
        String hashDoc = encoder.encode(doctor.getPassword());
        doctor.setPassword(hashDoc);

        doctor.setFullName(doctor.getFullName());
        doctor.setIs_deleted(0);
        doctor.setPhone_number(doctor.getPhone_number());
        doctor.setUsername(doctor.getUsername());
        doctor.setEmail(doctor.getEmail());
        doctor.setRole(userRolesDao.findByRoleName("ROLE_DOCTOR"));
        userDao.save(doctor);

        info.setNpi(info.getNpi());
        info.setUser(doctor);
        prescriberInfoDao.save(info);
        return "redirect:/";
    }

    @PostMapping("patientRegister")
    public String patientSave(@ModelAttribute User patient, @ModelAttribute PatientInfo info){
        String hashPat = encoder.encode(patient.getPassword());
        patient.setPassword(hashPat);
        patient.setIs_deleted(0);
        patient.setPhone_number(patient.getPhone_number());
        patient.setUsername(patient.getUsername());
        patient.setEmail(patient.getEmail());
        patient.setRole(userRolesDao.findByRoleName("ROLE_PATIENT"));
        userDao.save(patient);

        info.setUser(patient);
        info.setZip(info.getZip());
        info.setState(info.getState());
        info.setSex(info.getSex());
        info.setDob(info.getDob());
        info.setCity(info.getCity());
        info.setAddress(info.getAddress());
        patientInfoDao.save(info);
        return "redirect:/";
    }




/**BUFFER*/
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
