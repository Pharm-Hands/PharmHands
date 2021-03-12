package com.pharmhands.repositories;

import com.pharmhands.models.PrescriptionNotes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrescriptionNotesRepository extends JpaRepository<PrescriptionNotes, Long> {


}
