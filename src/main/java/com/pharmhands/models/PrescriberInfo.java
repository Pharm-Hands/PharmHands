package com.pharmhands.models;

import javax.persistence.*;

@Entity
@Table(name = "prescriber_info")
public class PrescriberInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private long npi;

    @OneToOne
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public PrescriberInfo(){}

    public PrescriberInfo(long id, User user, long npi) {
        this.id = id;
        this.npi = npi;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getNpi() {
        return npi;
    }

    public void setNpi(long npi) {
        this.npi = npi;
    }
}
