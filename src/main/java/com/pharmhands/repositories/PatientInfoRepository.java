package com.pharmhands.repositories;

import com.pharmhands.models.PatientInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientInfoRepository extends JpaRepository<PatientInfo, Long> {


}
