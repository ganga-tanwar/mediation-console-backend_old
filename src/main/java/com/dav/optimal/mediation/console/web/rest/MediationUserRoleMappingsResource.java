package com.dav.optimal.mediation.console.web.rest;

import com.dav.optimal.mediation.console.service.MediationUserRoleMappingsService;
import com.dav.optimal.mediation.console.web.rest.errors.BadRequestAlertException;
import com.dav.optimal.mediation.console.service.dto.MediationUserRoleMappingsDTO;

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
 * REST controller for managing {@link com.dav.optimal.mediation.console.domain.MediationUserRoleMappings}.
 */
@RestController
@RequestMapping("/api")
public class MediationUserRoleMappingsResource {

    private final Logger log = LoggerFactory.getLogger(MediationUserRoleMappingsResource.class);

    private static final String ENTITY_NAME = "mediationConsoleMediationUserRoleMappings";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MediationUserRoleMappingsService mediationUserRoleMappingsService;

    public MediationUserRoleMappingsResource(MediationUserRoleMappingsService mediationUserRoleMappingsService) {
        this.mediationUserRoleMappingsService = mediationUserRoleMappingsService;
    }

    /**
     * {@code POST  /mediation-user-role-mappings} : Create a new mediationUserRoleMappings.
     *
     * @param mediationUserRoleMappingsDTO the mediationUserRoleMappingsDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mediationUserRoleMappingsDTO, or with status {@code 400 (Bad Request)} if the mediationUserRoleMappings has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/mediation-user-role-mappings")
    public ResponseEntity<MediationUserRoleMappingsDTO> createMediationUserRoleMappings(@Valid @RequestBody MediationUserRoleMappingsDTO mediationUserRoleMappingsDTO) throws URISyntaxException {
        log.debug("REST request to save MediationUserRoleMappings : {}", mediationUserRoleMappingsDTO);
        if (mediationUserRoleMappingsDTO.getId() != null) {
            throw new BadRequestAlertException("A new mediationUserRoleMappings cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MediationUserRoleMappingsDTO result = mediationUserRoleMappingsService.save(mediationUserRoleMappingsDTO);
        return ResponseEntity.created(new URI("/api/mediation-user-role-mappings/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /mediation-user-role-mappings} : Updates an existing mediationUserRoleMappings.
     *
     * @param mediationUserRoleMappingsDTO the mediationUserRoleMappingsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mediationUserRoleMappingsDTO,
     * or with status {@code 400 (Bad Request)} if the mediationUserRoleMappingsDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mediationUserRoleMappingsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/mediation-user-role-mappings")
    public ResponseEntity<MediationUserRoleMappingsDTO> updateMediationUserRoleMappings(@Valid @RequestBody MediationUserRoleMappingsDTO mediationUserRoleMappingsDTO) throws URISyntaxException {
        log.debug("REST request to update MediationUserRoleMappings : {}", mediationUserRoleMappingsDTO);
        if (mediationUserRoleMappingsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MediationUserRoleMappingsDTO result = mediationUserRoleMappingsService.save(mediationUserRoleMappingsDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, mediationUserRoleMappingsDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /mediation-user-role-mappings} : get all the mediationUserRoleMappings.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mediationUserRoleMappings in body.
     */
    @GetMapping("/mediation-user-role-mappings")
    public List<MediationUserRoleMappingsDTO> getAllMediationUserRoleMappings() {
        log.debug("REST request to get all MediationUserRoleMappings");
        return mediationUserRoleMappingsService.findAll();
    }

    /**
     * {@code GET  /mediation-user-role-mappings/:id} : get the "id" mediationUserRoleMappings.
     *
     * @param id the id of the mediationUserRoleMappingsDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mediationUserRoleMappingsDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/mediation-user-role-mappings/{id}")
    public ResponseEntity<MediationUserRoleMappingsDTO> getMediationUserRoleMappings(@PathVariable Long id) {
        log.debug("REST request to get MediationUserRoleMappings : {}", id);
        Optional<MediationUserRoleMappingsDTO> mediationUserRoleMappingsDTO = mediationUserRoleMappingsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mediationUserRoleMappingsDTO);
    }

    /**
     * {@code DELETE  /mediation-user-role-mappings/:id} : delete the "id" mediationUserRoleMappings.
     *
     * @param id the id of the mediationUserRoleMappingsDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/mediation-user-role-mappings/{id}")
    public ResponseEntity<Void> deleteMediationUserRoleMappings(@PathVariable Long id) {
        log.debug("REST request to delete MediationUserRoleMappings : {}", id);
        mediationUserRoleMappingsService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
