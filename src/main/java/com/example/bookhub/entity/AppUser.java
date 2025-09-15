package com.example.bookhub.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class AppUser {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(unique = true)
    private String username;
    private String password;
}
