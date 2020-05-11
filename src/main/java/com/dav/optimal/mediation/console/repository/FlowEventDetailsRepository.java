package com.dav.optimal.mediation.console.repository;

import com.dav.optimal.mediation.console.domain.FlowEventDetails;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the FlowEventDetails entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FlowEventDetailsRepository extends JpaRepository<FlowEventDetails, Long> {
}
