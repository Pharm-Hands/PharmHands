package com.pharmhands.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private int is_deleted;

    @Column(nullable = false)
    private String phone_number;

    @OneToOne
    private UserRoles role;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "patient")
    private List<Prescriptions> prescriptions;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "doctor")
    private List<Prescriptions> prescribed;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Fills> fills;

    public User() {
    }

    public User(long id, String username, String fullName, String email, String password, int is_deleted, String phone_number, UserRoles role) {
        this.id = id;
        this.username = username;
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.is_deleted = is_deleted;
        this.phone_number = phone_number;
        this.role = role;
    }

    public User(User copy) {
        id = copy.id;
        username = copy.username;
        fullName= copy.fullName;
//        full_name = copy.full_name;
        email = copy.email;
        password = copy.password;
        is_deleted = copy.is_deleted;
        phone_number = copy.phone_number;
        role = copy.role;
    }

    public UserRoles getRole() {
        return role;
    }

    public void setRole(UserRoles role) {
        this.role = role;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getIs_deleted() {
        return is_deleted;
    }

    public void setIs_deleted(int is_deleted) {
        this.is_deleted = is_deleted;
    }


    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public List<Prescriptions> getPrescriptions() {
        return prescriptions;
    }

    public void setPrescriptions(List<Prescriptions> prescriptions) {
        this.prescriptions = prescriptions;
    }

    public List<Fills> getFills() {
        return fills;
    }

    public void setFills(List<Fills> fills) {
        this.fills = fills;
    }
}
