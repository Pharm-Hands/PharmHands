package com.pharmhands.repositories;

import com.pharmhands.models.Drugs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DrugsRepository extends JpaRepository<Drugs,Long> {

}
