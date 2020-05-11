package com.dav.optimal.mediation.console.repository;

import com.dav.optimal.mediation.console.domain.MediationUsers;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the MediationUsers entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MediationUsersRepository extends JpaRepository<MediationUsers, Long> {
}
