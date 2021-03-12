package com.pharmhands.models;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "fills")
public class Fills {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private long fill_number;

    @Column(nullable = false)
    private Date fill_date;

    @ManyToOne
    @JoinColumn(name = "prescriptions")
    private Prescriptions prescription;

    //many users ->1 fill
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Fills(){}

    public Fills(long id, long fill_number, Date fill_date, Prescriptions prescription, User user) {
        this.id = id;
        this.fill_number = fill_number;
        this.fill_date = fill_date;
        this.prescription = prescription;
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getFill_number() {
        return fill_number;
    }

    public void setFill_number(long fill_number) {
        this.fill_number = fill_number;
    }

    public Date getFill_date() {
        return fill_date;
    }

    public void setFill_date(Date fill_date) {
        this.fill_date = fill_date;
    }

    public Prescriptions getPrescription() {
        return prescription;
    }

    public void setPrescription(Prescriptions prescription) {
        this.prescription = prescription;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


}
