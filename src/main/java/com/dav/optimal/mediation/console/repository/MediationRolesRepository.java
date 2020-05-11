package com.dav.optimal.mediation.console.repository;

import com.dav.optimal.mediation.console.domain.MediationRoles;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the MediationRoles entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MediationRolesRepository extends JpaRepository<MediationRoles, Long> {
}
