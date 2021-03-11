package com.pharmhands.models;

import javax.persistence.*;
import java.util.Date;

@Table(name = "prescription_notes")
@Entity
public class PrescriptionNotes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "prescriptions")
    private Prescriptions prescription;


    @Column(nullable= false, length =1000000)
    private String note;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable= false)
    private Date created_at;

}
