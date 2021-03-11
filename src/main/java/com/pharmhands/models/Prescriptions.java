package com.pharmhands.models;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "prescriptions")
public class Prescriptions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    @OneToOne
    private Drugs drug;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "prescription")
    private List<Fills> fills;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "prescription")
    private List<PrescriptionNotes> prescription_notes;

    @Column(nullable = false)
    private int prescriber_id;


    @Column(nullable = false)
    private String drug_form;

    @Column(nullable = false)
    private int drug_Strength;

    @Column(nullable = false)
    private String sig;

    @Column(nullable = false)
    private int dose;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false, length = 2)
    private int is_deleted;

    @Column(nullable = false, length = 2)
    private int is_verified;


    @Column(nullable = false)
    private int days_supply;

    @Column(nullable = false)
    private Date created_at;
//https://vladmihalcea.com/date-timestamp-jpa-hibernate/

}
