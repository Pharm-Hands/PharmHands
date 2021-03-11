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


}
