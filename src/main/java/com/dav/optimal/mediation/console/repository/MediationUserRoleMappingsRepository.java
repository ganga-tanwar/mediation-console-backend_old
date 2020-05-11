package com.dav.optimal.mediation.console.repository;

import com.dav.optimal.mediation.console.domain.MediationUserRoleMappings;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the MediationUserRoleMappings entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MediationUserRoleMappingsRepository extends JpaRepository<MediationUserRoleMappings, Long> {
}
