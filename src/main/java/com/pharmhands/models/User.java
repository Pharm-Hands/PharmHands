package com.pharmhands.models;

import javax.persistence.*;
import java.util.List;

    @Entity
    @Table(name = "users")
    public class User {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private long id;

        @Column(nullable = false)
        private String email;

        @Column(nullable = false)
        private String password;

        @Column(nullable = false)
        private String full_name;

        @Column(nullable = false)
        private int deleted;

        public User() {
        }
}
