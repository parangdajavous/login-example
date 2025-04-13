package com.example.loginexample.user;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;


import java.sql.Timestamp;

@NoArgsConstructor
@Getter
@Table(name = "user_tb")
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String username;
    private String password;
    private String email;
    private Timestamp createdAt;

}