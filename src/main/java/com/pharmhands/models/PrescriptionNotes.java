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


    @Column(nullable = false, length = 1000000)
    private String note;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private Date created_at;


    public PrescriptionNotes() {
    }

    public PrescriptionNotes(long id, Prescriptions prescription, String note, User user, Date created_at) {
        this.id = id;
        this.prescription = prescription;
        this.note = note;
        this.user = user;
        this.created_at = created_at;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Prescriptions getPrescription() {
        return prescription;
    }

    public void setPrescription(Prescriptions prescription) {
        this.prescription = prescription;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }
}
