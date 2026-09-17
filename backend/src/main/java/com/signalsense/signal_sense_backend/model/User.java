package com.signalsense.signal_sense_backend.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.Set;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String password;
    private int age;
    private String location;

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> skills;

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> interests;

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> availability;

    private String role; // e.g., "VOLUNTEER", "ADMIN"
}
