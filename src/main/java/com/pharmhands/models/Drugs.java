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
}
