package com.signalsense.signal_sense_backend.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.Set;

@Entity
@Table(name = "opportunities")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Opportunity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private Long organizationId;

    private String title;
    
    @Column(length = 2000)
    private String description;
    
    private String category;
    
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> requiredSkills;
    
    private String location;
    
    private String schedule; // e.g. one-time, recurring
    
    private Integer slotsAvailable;
}
