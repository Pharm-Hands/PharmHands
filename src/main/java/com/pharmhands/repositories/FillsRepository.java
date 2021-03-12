package com.pharmhands.repositories;

import com.pharmhands.models.Fills;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FillsRepository extends JpaRepository<Fills, Long> {



}
