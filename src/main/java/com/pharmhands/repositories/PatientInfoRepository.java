package com.pharmhands.repositories;

import com.pharmhands.models.PatientInfo;
import com.pharmhands.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientInfoRepository extends JpaRepository<PatientInfo, Long> {

    PatientInfo findByUser(User user);

}
