package com.pharmhands.repositories;

import com.pharmhands.models.Fills;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FillsRepository extends JpaRepository<Fills, Long> {

//    @Query("SELECT COUNT(ALL Fills) FROM Fills WHERE prescription.id = ?1")
    @Query(value="select count(*) from Fills JOIN prescriptions p on fills.prescriptions = p.id WHERE p.id = ?1", nativeQuery = true)
    int fillCount(long id);

    @Query(value = "SELECT * FROM Fills JOIN prescriptions p on fills.prescriptions = p.id WHERE p.id = ?1 ORDER BY fill_date LIMIT 1", nativeQuery = true)
    Fills mostRecent(long id);


//    Fills findTop1ByPrescriptionIdOrderByFill_date(long id);

}
