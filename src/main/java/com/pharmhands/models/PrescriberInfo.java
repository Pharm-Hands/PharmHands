package com.pharmhands.models;

import javax.persistence.*;

@Entity
@Table(name = "prescriber_info")
public class PrescriberInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long prescriber_id;

    @Column(nullable = false)
    private long npi;

    @OneToOne
    private User users_id;
}
