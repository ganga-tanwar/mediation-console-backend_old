package com.dav.optimal.mediation.console.service;

import com.dav.optimal.mediation.console.domain.MediationRoles;
import com.dav.optimal.mediation.console.repository.MediationRolesRepository;
import com.dav.optimal.mediation.console.service.dto.MediationRolesDTO;
import com.dav.optimal.mediation.console.service.mapper.MediationRolesMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Service Implementation for managing {@link MediationRoles}.
 */
@Service
@Transactional
public class MediationRolesService {

    private final Logger log = LoggerFactory.getLogger(MediationRolesService.class);

    private final MediationRolesRepository mediationRolesRepository;

    private final MediationRolesMapper mediationRolesMapper;

    public MediationRolesService(MediationRolesRepository mediationRolesRepository, MediationRolesMapper mediationRolesMapper) {
        this.mediationRolesRepository = mediationRolesRepository;
        this.mediationRolesMapper = mediationRolesMapper;
    }

    /**
     * Save a mediationRoles.
     *
     * @param mediationRolesDTO the entity to save.
     * @return the persisted entity.
     */
    public MediationRolesDTO save(MediationRolesDTO mediationRolesDTO) {
        log.debug("Request to save MediationRoles : {}", mediationRolesDTO);
        MediationRoles mediationRoles = mediationRolesMapper.toEntity(mediationRolesDTO);
        mediationRoles = mediationRolesRepository.save(mediationRoles);
        return mediationRolesMapper.toDto(mediationRoles);
    }

    /**
     * Get all the mediationRoles.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<MediationRolesDTO> findAll() {
        log.debug("Request to get all MediationRoles");
        return mediationRolesRepository.findAll().stream()
            .map(mediationRolesMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     *  Get all the mediationRoles where MediationRoleId is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true) 
    public List<MediationRolesDTO> findAllWhereMediationRoleIdIsNull() {
        log.debug("Request to get all mediationRoles where MediationRoleId is null");
        return StreamSupport
            .stream(mediationRolesRepository.findAll().spliterator(), false)
            .filter(mediationRoles -> mediationRoles.getMediationRoleId() == null)
            .map(mediationRolesMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one mediationRoles by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MediationRolesDTO> findOne(Long id) {
        log.debug("Request to get MediationRoles : {}", id);
        return mediationRolesRepository.findById(id)
            .map(mediationRolesMapper::toDto);
    }

    /**
     * Delete the mediationRoles by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MediationRoles : {}", id);
        mediationRolesRepository.deleteById(id);
    }
}
