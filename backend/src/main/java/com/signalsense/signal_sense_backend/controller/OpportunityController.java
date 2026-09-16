package com.signalsense.signal_sense_backend.controller;

import com.signalsense.signal_sense_backend.model.Opportunity;
import com.signalsense.signal_sense_backend.repository.OpportunityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/opportunities")
@CrossOrigin(origins = "*") // For development only
public class OpportunityController {

    @Autowired
    private OpportunityRepository opportunityRepository;

    @GetMapping
    public List<Opportunity> getAllOpportunities() {
        return opportunityRepository.findAll();
    }
    
    @GetMapping("/org/{orgId}")
    public List<Opportunity> getOpportunitiesByOrg(@PathVariable Long orgId) {
        return opportunityRepository.findByOrganizationId(orgId);
    }

    @PostMapping
    public Opportunity createOpportunity(@RequestBody Opportunity opportunity) {
        return opportunityRepository.save(opportunity);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Opportunity> getOpportunityById(@PathVariable Long id) {
        return opportunityRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
