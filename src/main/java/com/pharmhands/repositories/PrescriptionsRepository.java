package com.pharmhands.repositories;
import com.pharmhands.models.Prescriptions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrescriptionsRepository extends JpaRepository<Prescriptions,Long> {

    @Query("FROM Prescriptions WHERE doctor.id = ?1 AND is_deleted = 0 ORDER BY created_at DESC")
    List<Prescriptions> findAllByPrescriberId(long id);

    @Query("FROM Prescriptions WHERE patient.id = ?1 AND is_deleted = 0 ORDER BY created_at DESC")
    List<Prescriptions> findAllByPatientId(long id);

    @Query("FROM Prescriptions WHERE is_deleted = 0 ORDER BY created_at DESC")
    List<Prescriptions> findAllNotDeleted();

    @Query("FROM Prescriptions WHERE is_verified = 0 AND is_deleted = 0")
    List<Prescriptions> findAllUnverified();



}




