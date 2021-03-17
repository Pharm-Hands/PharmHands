package com.pharmhands.repositories;

import com.pharmhands.models.Drugs;
import com.pharmhands.models.UserRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DrugsRepository extends JpaRepository<Drugs,Long> {
    @Query("FROM Drugs WHERE drug_name = ?1")
    Drugs findDrugsByDrug_name(String drug_name);
}

