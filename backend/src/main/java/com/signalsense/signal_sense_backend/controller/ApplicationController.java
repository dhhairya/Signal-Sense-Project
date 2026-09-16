package com.signalsense.signal_sense_backend.controller;

import com.signalsense.signal_sense_backend.model.Application;
import com.signalsense.signal_sense_backend.repository.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/applications")
@CrossOrigin(origins = "*") // For development only
public class ApplicationController {

    @Autowired
    private ApplicationRepository applicationRepository;

    @GetMapping("/user/{userId}")
    public List<Application> getApplicationsByUser(@PathVariable Long userId) {
        return applicationRepository.findByUserId(userId);
    }

    @GetMapping("/opportunity/{opportunityId}")
    public List<Application> getApplicationsByOpportunity(@PathVariable Long opportunityId) {
        return applicationRepository.findByOpportunityId(opportunityId);
    }

    @PostMapping
    public Application createApplication(@RequestBody Application application) {
        application.setAppliedAt(LocalDateTime.now());
        application.setStatus("APPLIED");
        return applicationRepository.save(application);
    }
}
