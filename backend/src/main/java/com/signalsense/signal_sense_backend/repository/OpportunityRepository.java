package com.signalsense.signal_sense_backend.repository;

import com.signalsense.signal_sense_backend.model.Opportunity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OpportunityRepository extends JpaRepository<Opportunity, Long> {
    List<Opportunity> findByOrganizationId(Long organizationId);
}
