package com.pharmhands;

import com.pharmhands.models.*;
import com.pharmhands.repositories.*;
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
    private final FillsRepository fillsDao;
    private final DrugsRepository drugsDao;
    private final PasswordEncoder encoder;

    public PostStartupRunner(UserRepository userDao, UserRolesRepository userRolesDao, PrescriberInfoRepository prescriberInfoDao, PrescriptionsRepository prescriptionsDao, FillsRepository fillsDao, DrugsRepository drugsDao, PasswordEncoder encoder) {
        this.userDao = userDao;
        this.userRolesDao = userRolesDao;
        this.prescriberInfoDao = prescriberInfoDao;
        this.prescriptionsDao = prescriptionsDao;
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
        doc.setRole_name("doctor");
        UserRoles docRole = userRolesDao.save(doc);

        UserRoles pat = new UserRoles();
        pat.setRole_name("patient");
        UserRoles patRole = userRolesDao.save(pat);

        UserRoles pharm = new UserRoles();
        pharm.setRole_name("pharmacist");
        UserRoles pharmRole = userRolesDao.save(pharm);

//      doctor role
        User doctor = new User();
        String hashDoc = encoder.encode("joe1996");
        doctor.setPassword(hashDoc);
        doctor.setFull_name("Joe Cuthbert");
        doctor.setIs_deleted(0);
        doctor.setPhone_number("2102596441");
        doctor.setUsername("joe");
        doctor.setEmail("josiah.thomas.cuthbert@gmail.com");
        doctor.setRole(docRole);
        User joe = userDao.save(doctor);

        PrescriberInfo info = new PrescriberInfo();
        info.setNpi(6567777);
        info.setUser(joe);
        PrescriberInfo savedInfo = prescriberInfoDao.save(info);

//      patient roles
        User patient1 = new User();
        String hashPat1 = encoder.encode("jaya");
        patient1.setPassword(hashPat1);
        patient1.setFull_name("Jaya Seyyadri");
        patient1.setIs_deleted(0);
        patient1.setPhone_number("098740987");
        patient1.setUsername("jaya");
        patient1.setEmail("jaya@jaya.com");
        patient1.setRole(patRole);
        User jaya = userDao.save(patient1);

        User patient2 = new User();
        String hashPat2 = encoder.encode("james");
        patient2.setPassword(hashPat2);
        patient2.setFull_name("James Gemes");
        patient2.setIs_deleted(0);
        patient2.setPhone_number("098740987");
        patient2.setUsername("james");
        patient2.setEmail("james@james.com");
        patient2.setRole(patRole);
        User james = userDao.save(patient2);

//      Pharmacist role
        User pharmacist = new User();
        String hashPharm = encoder.encode("rod");
        pharmacist.setPassword(hashPharm);
        pharmacist.setFull_name("Rod Sanati");
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
        Prescriptions prescription = new Prescriptions();
        long d = System.currentTimeMillis();
        Date date = new Date(d);
        prescription.setCreated_at(date);
        prescription.setDays_supply(40);
        prescription.setDose(2);
        prescription.setDrug(savedDrug1);
        prescription.setDrug_form("pill");
        prescription.setDrug_Strength(2);
        prescription.setIs_verified(0);
        prescription.setQuantity(4);
        prescription.setSig("idk what this is");
        prescription.setIs_deleted(0);
        prescription.setUser(jaya);

//      I think we will need to refactor this in the model to accept a long instead of int
        prescription.setPrescriber_id((int) joe.getId());

        Prescriptions savedPrescription = prescriptionsDao.save(prescription);

        Fills fill = new Fills();
        fill.setUser(rod);
        fill.setFill_date(date);
        fill.setFill_number(1);
        fill.setPrescription(savedPrescription);
        Fills savedFill = fillsDao.save(fill);

    }
}
