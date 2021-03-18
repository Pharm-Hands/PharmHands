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
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private Date created_at;

    @OneToOne
    private Prescriptions prescription;


    public PrescriptionRequests(){};

    public PrescriptionRequests(long id, String note, User user, Date created_at, Prescriptions prescription) {
        this.id = id;
        this.note = note;
        this.user = user;
        this.created_at = created_at;
        this.prescription = prescription;
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

    public Prescriptions getPrescription() {
        return prescription;
    }

    public void setPrescription(Prescriptions prescription) {
        this.prescription = prescription;
    }
}
