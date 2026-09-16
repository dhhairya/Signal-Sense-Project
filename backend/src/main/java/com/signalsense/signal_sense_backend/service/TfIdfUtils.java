package com.signalsense.signal_sense_backend.service;

import java.util.*;

public class TfIdfUtils {

    public static double cosineSimilarity(Map<String, Double> vec1, Map<String, Double> vec2) {
        Set<String> allKeys = new HashSet<>(vec1.keySet());
        allKeys.addAll(vec2.keySet());
        
        double dotProduct = 0.0;
        double norm1 = 0.0;
        double norm2 = 0.0;
        
        for (String key : allKeys) {
            double v1 = vec1.getOrDefault(key, 0.0);
            double v2 = vec2.getOrDefault(key, 0.0);
            dotProduct += v1 * v2;
            norm1 += v1 * v1;
            norm2 += v2 * v2;
        }
        
        if (norm1 == 0 || norm2 == 0) return 0.0;
        return dotProduct / (Math.sqrt(norm1) * Math.sqrt(norm2));
    }
    
    public static Map<String, Double> computeTf(List<String> terms) {
        Map<String, Double> tf = new HashMap<>();
        if (terms == null || terms.isEmpty()) return tf;
        
        for (String term : terms) {
            String lower = term.toLowerCase().trim();
            tf.put(lower, tf.getOrDefault(lower, 0.0) + 1.0);
        }
        
        int total = terms.size();
        for (Map.Entry<String, Double> entry : tf.entrySet()) {
            entry.setValue(entry.getValue() / total);
        }
        return tf;
    }
}
