package com.signalsense.signal_sense_backend.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import com.signalsense.signal_sense_backend.model.Opportunity;

@Data
@AllArgsConstructor
public class OpportunityScore implements Comparable<OpportunityScore> {
    private Opportunity opportunity;
    private double score;

    @Override
    public int compareTo(OpportunityScore o) {
        // Sort descending
        return Double.compare(o.getScore(), this.score);
    }
}
