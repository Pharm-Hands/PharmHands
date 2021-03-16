package com.pharmhands.repositories;
import com.pharmhands.models.Prescriptions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrescriptionsRepository extends JpaRepository<Prescriptions,Long> {

    @Query("FROM Prescriptions WHERE prescriber_id = ?1 AND is_deleted = 0")
    List<Prescriptions> findAllByPrescriberId(long id);

}




