package com.signalsense.signal_sense_backend.service;

import com.signalsense.signal_sense_backend.model.Opportunity;
import com.signalsense.signal_sense_backend.model.User;
import com.signalsense.signal_sense_backend.repository.OpportunityRepository;
import com.signalsense.signal_sense_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class RecommendationService {

    @Autowired
    private OpportunityRepository opportunityRepository;

    @Autowired
    private UserRepository userRepository;

    public List<OpportunityScore> getRecommendationsForUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        
        List<Opportunity> allOpportunities = opportunityRepository.findAll();
        
        // Combine user skills and interests into one list of terms
        List<String> userTerms = new ArrayList<>();
        if (user.getSkills() != null) userTerms.addAll(user.getSkills());
        if (user.getInterests() != null) userTerms.addAll(user.getInterests());
        
        Map<String, Double> userTf = TfIdfUtils.computeTf(userTerms);
        
        List<OpportunityScore> scoredOpportunities = new ArrayList<>();
        
        for (Opportunity opp : allOpportunities) {
            List<String> oppTerms = new ArrayList<>();
            if (opp.getCategory() != null) oppTerms.add(opp.getCategory());
            if (opp.getRequiredSkills() != null) oppTerms.addAll(opp.getRequiredSkills());
            
            Map<String, Double> oppTf = TfIdfUtils.computeTf(oppTerms);
            
            double score = TfIdfUtils.cosineSimilarity(userTf, oppTf);
            
            // Hard filter example: basic exact string location match check
            if (opp.getLocation() != null && user.getLocation() != null) {
                if (opp.getLocation().equalsIgnoreCase(user.getLocation())) {
                    score += 0.5; // Boost score for same location
                }
            }
            
            scoredOpportunities.add(new OpportunityScore(opp, score));
        }
        
        Collections.sort(scoredOpportunities);
        
        return scoredOpportunities;
    }
}
