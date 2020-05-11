package com.dav.optimal.mediation.console.service;

import com.dav.optimal.mediation.console.domain.FlowDetails;
import com.dav.optimal.mediation.console.repository.FlowDetailsRepository;
import com.dav.optimal.mediation.console.service.dto.FlowDetailsDTO;
import com.dav.optimal.mediation.console.service.mapper.FlowDetailsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link FlowDetails}.
 */
@Service
@Transactional
public class FlowDetailsService {

    private final Logger log = LoggerFactory.getLogger(FlowDetailsService.class);

    private final FlowDetailsRepository flowDetailsRepository;

    private final FlowDetailsMapper flowDetailsMapper;

    public FlowDetailsService(FlowDetailsRepository flowDetailsRepository, FlowDetailsMapper flowDetailsMapper) {
        this.flowDetailsRepository = flowDetailsRepository;
        this.flowDetailsMapper = flowDetailsMapper;
    }

    /**
     * Save a flowDetails.
     *
     * @param flowDetailsDTO the entity to save.
     * @return the persisted entity.
     */
    public FlowDetailsDTO save(FlowDetailsDTO flowDetailsDTO) {
        log.debug("Request to save FlowDetails : {}", flowDetailsDTO);
        FlowDetails flowDetails = flowDetailsMapper.toEntity(flowDetailsDTO);
        flowDetails = flowDetailsRepository.save(flowDetails);
        return flowDetailsMapper.toDto(flowDetails);
    }

    /**
     * Get all the flowDetails.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<FlowDetailsDTO> findAll() {
        log.debug("Request to get all FlowDetails");
        return flowDetailsRepository.findAll().stream()
            .map(flowDetailsMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one flowDetails by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<FlowDetailsDTO> findOne(Long id) {
        log.debug("Request to get FlowDetails : {}", id);
        return flowDetailsRepository.findById(id)
            .map(flowDetailsMapper::toDto);
    }

    /**
     * Delete the flowDetails by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete FlowDetails : {}", id);
        flowDetailsRepository.deleteById(id);
    }
}
