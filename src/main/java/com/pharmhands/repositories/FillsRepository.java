package com.pharmhands.repositories;

import com.pharmhands.models.Fills;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface FillsRepository extends JpaRepository<Fills, Long> {

//    @Query("SELECT COUNT(ALL Fills) FROM Fills WHERE prescription.id = ?1")
    @Query(value="select count(*) from fills JOIN prescriptions p on fills.prescriptions = p.id WHERE p.id = ?1", nativeQuery = true)
    int fillCount(long id);

    @Query(value = "SELECT * FROM fills JOIN prescriptions p on fills.prescriptions = p.id WHERE p.id = ?1 ORDER BY fill_date LIMIT 1", nativeQuery = true)
    Fills mostRecent(long id);

    @Query(value = "SELECT * FROM fills WHERE prescription.id = ?1", nativeQuery = true)
    List<Fills> fillsByPrescription(long prescriptionId);

//    @Query(value = "SELECT CASE WHEN TRUE" +
//            "(SELECT * FROM Fills )" +
//            " THEN CAST(1 AS BIT) " +
//            "ELSE CAST(0 AS BIT) END", nativeQuery = true)
//    boolean canBeFilled(Date currentDate);

//    Fills findTop1ByPrescriptionIdOrderByFill_date(long id);

}
