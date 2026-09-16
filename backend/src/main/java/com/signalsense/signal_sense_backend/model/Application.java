package com.signalsense.signal_sense_backend.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "applications")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId; // volunteer applying
    private Long opportunityId;
    
    private String status; // APPLIED, UNDER_REVIEW, ACCEPTED, REJECTED, COMPLETED
    
    private LocalDateTime appliedAt;
    private LocalDateTime completedAt;
    
    private String feedback;
}
