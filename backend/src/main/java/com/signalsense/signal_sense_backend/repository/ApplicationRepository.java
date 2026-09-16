package com.signalsense.signal_sense_backend.repository;

import com.signalsense.signal_sense_backend.model.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {
    List<Application> findByUserId(Long userId);
    List<Application> findByOpportunityId(Long opportunityId);
}
