package com.pharmhands.repositories;

import com.pharmhands.models.PrescriptionRequests;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrescriptionRequestsRepository extends JpaRepository<PrescriptionRequests,Long> {

    @Query(value="select count(*) from PrescriptionRequests pr JOIN prescriptions p on pr.prescriptions = p.id WHERE p.id = ?1", nativeQuery = true)
    int requestCount(long id);

    @Query(value="select * from PrescriptionRequests where Prescriptions.doctor.id = ?1", nativeQuery = true)
    List<PrescriptionRequests> findAllByDoctor(long id);

    @Query("FROM PrescriptionRequests WHERE is_Fulfilled = 0")
    List<PrescriptionRequests> findAllUnfulfilled();

    @Query("FROM PrescriptionRequests WHERE prescription.id = ?1")
    List<PrescriptionRequests> findAllByPrescriptionId(long prescriptionId);
}