package com.pharmhands.repositories;

import com.pharmhands.models.Drugs;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DrugsRepository extends JpaRepository<Drugs,Long> {

}
