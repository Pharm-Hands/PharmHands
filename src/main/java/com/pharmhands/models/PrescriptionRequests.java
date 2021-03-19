package com.pharmhands.models;

import javax.persistence.*;
import java.util.Date;

@Table(name="prescription_requests")
@Entity
public class PrescriptionRequests {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 1000000)
    private String note;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private User patient;

    @Column(nullable = false)
    private Date created_at;

    @ManyToOne
    @JoinColumn(name = "prescriptions")
    private Prescriptions prescription;

    @Column(nullable = false)
    private int is_Fulfilled;

    public PrescriptionRequests(){};

    public PrescriptionRequests(long id, String note, User patient, Date created_at, Prescriptions prescription, int is_Fulfilled) {
        this.id = id;
        this.note = note;
        this.patient = patient;
        this.created_at = created_at;
        this.prescription = prescription;
        this.is_Fulfilled = is_Fulfilled;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public User getPatient() {
        return patient;
    }

    public void setPatient(User patient) {
        this.patient = patient;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Prescriptions getPrescription() {
        return prescription;
    }

    public void setPrescription(Prescriptions prescription) {
        this.prescription = prescription;
    }

    public int getIs_Fulfilled() {
        return is_Fulfilled;
    }

    public void setIs_Fulfilled(int is_Fulfilled) {
        this.is_Fulfilled = is_Fulfilled;
    }
}
