package com.signalsense.signal_sense_backend.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.List;

@Entity
@Table(name = "organizations")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Organization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String password;
    
    private String category; // NGO, school, shelter, etc.
    
    @ElementCollection
    private List<String> locations;
    
    private String verificationStatus; // e.g., PENDING, VERIFIED
    
    private String contactPerson;
    
    private Double rating; // average rating
}
