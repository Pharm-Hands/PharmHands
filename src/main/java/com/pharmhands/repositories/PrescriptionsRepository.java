package com.pharmhands.repositories;
import com.pharmhands.models.Prescriptions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrescriptionsRepository extends JpaRepository<Prescriptions,Long> {

}




