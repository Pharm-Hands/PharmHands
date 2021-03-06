package com.pharmhands;

import com.pharmhands.models.*;
import com.pharmhands.repositories.*;
import com.pharmhands.models.PrescriberInfo;
import com.pharmhands.models.User;
import com.pharmhands.models.UserRoles;
import com.pharmhands.repositories.PrescriberInfoRepository;
import com.pharmhands.repositories.UserRepository;
import com.pharmhands.repositories.UserRolesRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.sql.Date;
import org.springframework.stereotype.Component;

@Component
public class PostStartupRunner implements CommandLineRunner {

    private final UserRepository userDao;
    private final UserRolesRepository userRolesDao;
    private final PrescriberInfoRepository prescriberInfoDao;
    private final PrescriptionsRepository prescriptionsDao;
    private final PatientInfoRepository patientInfoDao;
    private final FillsRepository fillsDao;
    private final DrugsRepository drugsDao;
    private final PasswordEncoder encoder;

    public PostStartupRunner(UserRepository userDao, UserRolesRepository userRolesDao, PrescriberInfoRepository prescriberInfoDao, PrescriptionsRepository prescriptionsDao, PatientInfoRepository patientInfoDao, FillsRepository fillsDao, DrugsRepository drugsDao, PasswordEncoder encoder) {

        this.userDao = userDao;
        this.userRolesDao = userRolesDao;
        this.prescriberInfoDao = prescriberInfoDao;
        this.prescriptionsDao = prescriptionsDao;
        this.patientInfoDao = patientInfoDao;
        this.fillsDao = fillsDao;
        this.drugsDao = drugsDao;
        this.encoder = encoder;
    }

    @Override
    public void run(String... args) throws Exception {
        // don't run if there's already any users in the database
        if (userDao.count() != 0) {
            return;
        }

//      Setting user roles
        UserRoles doc = new UserRoles();
        doc.setRole_name("ROLE_DOCTOR");
        UserRoles docRole = userRolesDao.save(doc);

        UserRoles pat = new UserRoles();
        pat.setRole_name("ROLE_PATIENT");
        UserRoles patRole = userRolesDao.save(pat);

        UserRoles pharm = new UserRoles();
        pharm.setRole_name("ROLE_PHARMACIST");
        UserRoles pharmRole = userRolesDao.save(pharm);

//      doctor role
        User doctor = new User();
        String hashDoc = encoder.encode("joe1996");
        doctor.setPassword(hashDoc);
        doctor.setFullName(("Joe Cuthbert"));
//        doctor.setFull_name("Joe Cuthbert");
        doctor.setIs_deleted(0);
        doctor.setPhone_number("2102596441");
        doctor.setUsername("joe");
        doctor.setEmail("josiah.thomas.cuthbert@gmail.com");
        doctor.setRole(docRole);
        User joe = userDao.save(doctor);

        PrescriberInfo prescriberInfo = new PrescriberInfo();
        prescriberInfo.setNpi(6567777);
        prescriberInfo.setUser(joe);
        PrescriberInfo savedInfo = prescriberInfoDao.save(prescriberInfo);

//      patient roles
        User patient1 = new User();
        String hashPat1 = encoder.encode("jaya");
        patient1.setPassword(hashPat1);
//        patient1.setFull_name("Jaya Seyyadri");
        patient1.setFullName("Jaya Seyyadri");
        patient1.setIs_deleted(0);
        patient1.setPhone_number("098740987");
        patient1.setUsername("jaya");
        patient1.setEmail("jaya@jaya.com");
        patient1.setRole(patRole);
        User jaya = userDao.save(patient1);

        PatientInfo patientInfo1 = new PatientInfo();
        patientInfo1.setAddress("123 mulberry ln");
        patientInfo1.setCity("San Antonio");
        Date dob1 = new Date(2001,1,1 );
        patientInfo1.setDob(dob1);
        patientInfo1.setSex("F");
        patientInfo1.setState("TX");
        patientInfo1.setZip(77777);
        patientInfo1.setUser(jaya);
        patientInfoDao.save(patientInfo1);

        User patient2 = new User();
        String hashPat2 = encoder.encode("james");
        patient2.setPassword(hashPat2);
//        patient2.setFull_name("James Gemes");
        patient2.setFullName("James Gemes");
        patient2.setIs_deleted(0);
        patient2.setPhone_number("098740987");
        patient2.setUsername("james");
        patient2.setEmail("james@james.com");
        patient2.setRole(patRole);
        User james = userDao.save(patient2);

        PatientInfo patientInfo2 = new PatientInfo();
        patientInfo2.setAddress("123 privet dr");
        patientInfo2.setCity("San Antonio");
        Date dob2 = new Date(199987687);
        patientInfo2.setDob(dob2);
        patientInfo2.setSex("M");
        patientInfo2.setState("TX");
        patientInfo2.setZip(77777);
        patientInfo2.setUser(james);
        patientInfoDao.save(patientInfo2);

//      Pharmacist role
        User pharmacist = new User();
        String hashPharm = encoder.encode("rod");
        pharmacist.setPassword(hashPharm);
//        pharmacist.setFull_name("Rod Sanati");
        pharmacist.setFullName("Rod Sanati");
        pharmacist.setIs_deleted(0);
        pharmacist.setPhone_number("098740987");
        pharmacist.setUsername("rod");
        pharmacist.setEmail("rod@rod.com");
        pharmacist.setRole(pharmRole);
        User rod = userDao.save(pharmacist);

//      setting a few drugs...
        Drugs drug1 = new Drugs();
        drug1.setDrug_name("Lisinopril");
        Drugs savedDrug1 = drugsDao.save(drug1);

        Drugs drug2 = new Drugs();
        drug2.setDrug_name("Lipitor");
        Drugs savedDrug2 = drugsDao.save(drug2);

        Drugs drug3 = new Drugs();
        drug3.setDrug_name("Vicodin");
        Drugs savedDrug3 = drugsDao.save(drug3);

        //      setting up a prescription
        Prescriptions prescription1 = new Prescriptions();
        long d = System.currentTimeMillis();
        Date date = new Date(d);
        prescription1.setCreated_at(date);
        prescription1.setDays_supply(40);
        prescription1.setDose(2);
        prescription1.setDrug(savedDrug1);
        prescription1.setDrug_form("pill");
        prescription1.setDrug_Strength(2);
        prescription1.setIs_verified(0);
        prescription1.setQuantity(4);
        prescription1.setSig("idk what this is");
        prescription1.setIs_deleted(0);
        prescription1.setPatient(jaya);

        prescription1.setDoctor(joe);

        Prescriptions savedPrescription = prescriptionsDao.save(prescription1);

        Prescriptions prescription2 = new Prescriptions();
        long d2 = System.currentTimeMillis();
        Date date2 = new Date(d2);
        prescription2.setCreated_at(date2);
        prescription2.setDays_supply(40);
        prescription2.setDose(2);
        prescription2.setDrug(savedDrug2);
        prescription2.setDrug_form("pill");
        prescription2.setDrug_Strength(2);
        prescription2.setIs_verified(0);
        prescription2.setQuantity(4);
        prescription2.setSig("idk what this is");
        prescription2.setIs_deleted(0);
        prescription2.setPatient(james);

        prescription2.setDoctor(joe);



        Prescriptions savedPrescription2 = prescriptionsDao.save(prescription2);

        Fills fill = new Fills();
        fill.setUser(rod);
        fill.setFill_date(date);
        fill.setFill_number(1);
        fill.setPrescription(savedPrescription);
        Fills savedFill = fillsDao.save(fill);



    }
}
