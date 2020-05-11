package com.dav.optimal.mediation.console.web.rest;

import com.dav.optimal.mediation.console.service.MediationRolesService;
import com.dav.optimal.mediation.console.web.rest.errors.BadRequestAlertException;
import com.dav.optimal.mediation.console.service.dto.MediationRolesDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.dav.optimal.mediation.console.domain.MediationRoles}.
 */
@RestController
@RequestMapping("/api")
public class MediationRolesResource {

    private final Logger log = LoggerFactory.getLogger(MediationRolesResource.class);

    private static final String ENTITY_NAME = "mediationConsoleMediationRoles";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MediationRolesService mediationRolesService;

    public MediationRolesResource(MediationRolesService mediationRolesService) {
        this.mediationRolesService = mediationRolesService;
    }

    /**
     * {@code POST  /mediation-roles} : Create a new mediationRoles.
     *
     * @param mediationRolesDTO the mediationRolesDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mediationRolesDTO, or with status {@code 400 (Bad Request)} if the mediationRoles has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/mediation-roles")
    public ResponseEntity<MediationRolesDTO> createMediationRoles(@Valid @RequestBody MediationRolesDTO mediationRolesDTO) throws URISyntaxException {
        log.debug("REST request to save MediationRoles : {}", mediationRolesDTO);
        if (mediationRolesDTO.getId() != null) {
            throw new BadRequestAlertException("A new mediationRoles cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MediationRolesDTO result = mediationRolesService.save(mediationRolesDTO);
        return ResponseEntity.created(new URI("/api/mediation-roles/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /mediation-roles} : Updates an existing mediationRoles.
     *
     * @param mediationRolesDTO the mediationRolesDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mediationRolesDTO,
     * or with status {@code 400 (Bad Request)} if the mediationRolesDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mediationRolesDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/mediation-roles")
    public ResponseEntity<MediationRolesDTO> updateMediationRoles(@Valid @RequestBody MediationRolesDTO mediationRolesDTO) throws URISyntaxException {
        log.debug("REST request to update MediationRoles : {}", mediationRolesDTO);
        if (mediationRolesDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MediationRolesDTO result = mediationRolesService.save(mediationRolesDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, mediationRolesDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /mediation-roles} : get all the mediationRoles.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mediationRoles in body.
     */
    @GetMapping("/mediation-roles")
    public List<MediationRolesDTO> getAllMediationRoles() {
        log.debug("REST request to get all MediationRoles");
        return mediationRolesService.findAll();
    }

    /**
     * {@code GET  /mediation-roles/:id} : get the "id" mediationRoles.
     *
     * @param id the id of the mediationRolesDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mediationRolesDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/mediation-roles/{id}")
    public ResponseEntity<MediationRolesDTO> getMediationRoles(@PathVariable Long id) {
        log.debug("REST request to get MediationRoles : {}", id);
        Optional<MediationRolesDTO> mediationRolesDTO = mediationRolesService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mediationRolesDTO);
    }

    /**
     * {@code DELETE  /mediation-roles/:id} : delete the "id" mediationRoles.
     *
     * @param id the id of the mediationRolesDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/mediation-roles/{id}")
    public ResponseEntity<Void> deleteMediationRoles(@PathVariable Long id) {
        log.debug("REST request to delete MediationRoles : {}", id);
        mediationRolesService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
