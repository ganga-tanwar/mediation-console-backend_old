package com.dav.optimal.mediation.console.service;

import com.dav.optimal.mediation.console.domain.MediationUsers;
import com.dav.optimal.mediation.console.repository.MediationUsersRepository;
import com.dav.optimal.mediation.console.service.dto.MediationUsersDTO;
import com.dav.optimal.mediation.console.service.mapper.MediationUsersMapper;
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
 * Service Implementation for managing {@link MediationUsers}.
 */
@Service
@Transactional
public class MediationUsersService {

    private final Logger log = LoggerFactory.getLogger(MediationUsersService.class);

    private final MediationUsersRepository mediationUsersRepository;

    private final MediationUsersMapper mediationUsersMapper;

    public MediationUsersService(MediationUsersRepository mediationUsersRepository, MediationUsersMapper mediationUsersMapper) {
        this.mediationUsersRepository = mediationUsersRepository;
        this.mediationUsersMapper = mediationUsersMapper;
    }

    /**
     * Save a mediationUsers.
     *
     * @param mediationUsersDTO the entity to save.
     * @return the persisted entity.
     */
    public MediationUsersDTO save(MediationUsersDTO mediationUsersDTO) {
        log.debug("Request to save MediationUsers : {}", mediationUsersDTO);
        MediationUsers mediationUsers = mediationUsersMapper.toEntity(mediationUsersDTO);
        mediationUsers = mediationUsersRepository.save(mediationUsers);
        return mediationUsersMapper.toDto(mediationUsers);
    }

    /**
     * Get all the mediationUsers.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<MediationUsersDTO> findAll() {
        log.debug("Request to get all MediationUsers");
        return mediationUsersRepository.findAll().stream()
            .map(mediationUsersMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     *  Get all the mediationUsers where MediationUserId is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true) 
    public List<MediationUsersDTO> findAllWhereMediationUserIdIsNull() {
        log.debug("Request to get all mediationUsers where MediationUserId is null");
        return StreamSupport
            .stream(mediationUsersRepository.findAll().spliterator(), false)
            .filter(mediationUsers -> mediationUsers.getMediationUserId() == null)
            .map(mediationUsersMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one mediationUsers by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MediationUsersDTO> findOne(Long id) {
        log.debug("Request to get MediationUsers : {}", id);
        return mediationUsersRepository.findById(id)
            .map(mediationUsersMapper::toDto);
    }

    /**
     * Delete the mediationUsers by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MediationUsers : {}", id);
        mediationUsersRepository.deleteById(id);
    }
}
