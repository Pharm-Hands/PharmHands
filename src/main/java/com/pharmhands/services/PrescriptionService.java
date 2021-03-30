package com.pharmhands.services;

import com.pharmhands.models.Prescriptions;
import com.pharmhands.repositories.FillsRepository;
import com.pharmhands.repositories.PrescriptionsRepository;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;


@Service("prescriptionService")
public class PrescriptionService {

    private final PrescriptionsRepository prescriptionsDao;

    private final FillsRepository fillsDao;

    public PrescriptionService(PrescriptionsRepository prescriptionsDao, FillsRepository fillsDao){
        this.prescriptionsDao=prescriptionsDao;
        this.fillsDao = fillsDao;
    }

    public Boolean fillEligible(long prescriptionId){

        Prescriptions prescription = prescriptionsDao.getOne(prescriptionId);

        Calendar fillCheck = Calendar.getInstance();
        Calendar now = Calendar.getInstance();
        now.setTime(new Date());
        System.out.println(fillsDao.fillCount(prescriptionId));
        if (fillsDao.fillCount(prescriptionId) != 0) {
            fillCheck.setTime(fillsDao.mostRecent(prescriptionId).getFill_date());
            fillCheck.add(Calendar.DATE, prescription.getDays_supply());

            return !fillCheck.getTime().after(now.getTime());
        }   else{
            return true;
        }

    }
}