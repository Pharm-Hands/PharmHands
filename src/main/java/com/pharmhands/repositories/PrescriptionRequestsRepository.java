package com.pharmhands.repositories;

import com.pharmhands.models.PrescriptionRequests;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrescriptionRequestsRepository extends JpaRepository<PrescriptionRequests,Long> {
}