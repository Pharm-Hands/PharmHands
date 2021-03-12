package com.pharmhands.repositories;

import com.pharmhands.models.PatientInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientInfoRepository extends JpaRepository<PatientInfo, Long> {


}
