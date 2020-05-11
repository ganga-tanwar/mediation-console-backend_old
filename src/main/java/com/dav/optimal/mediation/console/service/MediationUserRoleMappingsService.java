package com.dav.optimal.mediation.console.service;

import com.dav.optimal.mediation.console.domain.MediationUserRoleMappings;
import com.dav.optimal.mediation.console.repository.MediationUserRoleMappingsRepository;
import com.dav.optimal.mediation.console.service.dto.MediationUserRoleMappingsDTO;
import com.dav.optimal.mediation.console.service.mapper.MediationUserRoleMappingsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link MediationUserRoleMappings}.
 */
@Service
@Transactional
public class MediationUserRoleMappingsService {

    private final Logger log = LoggerFactory.getLogger(MediationUserRoleMappingsService.class);

    private final MediationUserRoleMappingsRepository mediationUserRoleMappingsRepository;

    private final MediationUserRoleMappingsMapper mediationUserRoleMappingsMapper;

    public MediationUserRoleMappingsService(MediationUserRoleMappingsRepository mediationUserRoleMappingsRepository, MediationUserRoleMappingsMapper mediationUserRoleMappingsMapper) {
        this.mediationUserRoleMappingsRepository = mediationUserRoleMappingsRepository;
        this.mediationUserRoleMappingsMapper = mediationUserRoleMappingsMapper;
    }

    /**
     * Save a mediationUserRoleMappings.
     *
     * @param mediationUserRoleMappingsDTO the entity to save.
     * @return the persisted entity.
     */
    public MediationUserRoleMappingsDTO save(MediationUserRoleMappingsDTO mediationUserRoleMappingsDTO) {
        log.debug("Request to save MediationUserRoleMappings : {}", mediationUserRoleMappingsDTO);
        MediationUserRoleMappings mediationUserRoleMappings = mediationUserRoleMappingsMapper.toEntity(mediationUserRoleMappingsDTO);
        mediationUserRoleMappings = mediationUserRoleMappingsRepository.save(mediationUserRoleMappings);
        return mediationUserRoleMappingsMapper.toDto(mediationUserRoleMappings);
    }

    /**
     * Get all the mediationUserRoleMappings.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<MediationUserRoleMappingsDTO> findAll() {
        log.debug("Request to get all MediationUserRoleMappings");
        return mediationUserRoleMappingsRepository.findAll().stream()
            .map(mediationUserRoleMappingsMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one mediationUserRoleMappings by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MediationUserRoleMappingsDTO> findOne(Long id) {
        log.debug("Request to get MediationUserRoleMappings : {}", id);
        return mediationUserRoleMappingsRepository.findById(id)
            .map(mediationUserRoleMappingsMapper::toDto);
    }

    /**
     * Delete the mediationUserRoleMappings by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MediationUserRoleMappings : {}", id);
        mediationUserRoleMappingsRepository.deleteById(id);
    }
}
