package com.pharmhands.models;


import javax.persistence.*;

@Entity
@Table(name = "drugs")
public class Drugs {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private String drug_name;


    public Drugs() {
    }

    public Drugs(long id, String drug_name) {
        this.id = id;
        this.drug_name = drug_name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDrug_name() {
        return drug_name;
    }

    public void setDrug_name(String drug_name) {
        this.drug_name = drug_name;
    }

}
