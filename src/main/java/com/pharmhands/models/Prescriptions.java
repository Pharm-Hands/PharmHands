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

    public Prescriptions() {
    }

    public Prescriptions(long id, User user, Drugs drug, List<Fills> fills, List<PrescriptionNotes> prescription_notes, int prescriber_id, String drug_form, int drug_Strength, String sig, int dose, int quantity, int is_deleted, int is_verified, int days_supply, Date created_at) {
        this.id = id;
        this.user = user;
        this.drug = drug;
        this.fills = fills;
        this.prescription_notes = prescription_notes;
        this.prescriber_id = prescriber_id;
        this.drug_form = drug_form;
        this.drug_Strength = drug_Strength;
        this.sig = sig;
        this.dose = dose;
        this.quantity = quantity;
        this.is_deleted = is_deleted;
        this.is_verified = is_verified;
        this.days_supply = days_supply;
        this.created_at = created_at;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Drugs getDrug() {
        return drug;
    }

    public void setDrug(Drugs drug) {
        this.drug = drug;
    }

    public List<Fills> getFills() {
        return fills;
    }

    public void setFills(List<Fills> fills) {
        this.fills = fills;
    }

    public List<PrescriptionNotes> getPrescription_notes() {
        return prescription_notes;
    }

    public void setPrescription_notes(List<PrescriptionNotes> prescription_notes) {
        this.prescription_notes = prescription_notes;
    }

    public int getPrescriber_id() {
        return prescriber_id;
    }

    public void setPrescriber_id(int prescriber_id) {
        this.prescriber_id = prescriber_id;
    }

    public String getDrug_form() {
        return drug_form;
    }

    public void setDrug_form(String drug_form) {
        this.drug_form = drug_form;
    }

    public int getDrug_Strength() {
        return drug_Strength;
    }

    public void setDrug_Strength(int drug_Strength) {
        this.drug_Strength = drug_Strength;
    }

    public String getSig() {
        return sig;
    }

    public void setSig(String sig) {
        this.sig = sig;
    }

    public int getDose() {
        return dose;
    }

    public void setDose(int dose) {
        this.dose = dose;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getIs_deleted() {
        return is_deleted;
    }

    public void setIs_deleted(int is_deleted) {
        this.is_deleted = is_deleted;
    }

    public int getIs_verified() {
        return is_verified;
    }

    public void setIs_verified(int is_verified) {
        this.is_verified = is_verified;
    }

    public int getDays_supply() {
        return days_supply;
    }

    public void setDays_supply(int days_supply) {
        this.days_supply = days_supply;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }
}
