package com.pharmhands.repositories;

import com.pharmhands.models.PrescriptionRequests;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PrescriptionRequestsRepository extends JpaRepository<PrescriptionRequests,Long> {

    @Query(value="select count(*) from PrescriptionRequests pr JOIN prescriptions p on pr.prescriptions = p.id WHERE p.id = ?1", nativeQuery = true)
    int requestCount(long id);

}