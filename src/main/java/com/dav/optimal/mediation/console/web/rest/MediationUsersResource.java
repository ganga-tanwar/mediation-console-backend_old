package com.dav.optimal.mediation.console.web.rest;

import com.dav.optimal.mediation.console.service.MediationUsersService;
import com.dav.optimal.mediation.console.web.rest.errors.BadRequestAlertException;
import com.dav.optimal.mediation.console.service.dto.MediationUsersDTO;

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
 * REST controller for managing {@link com.dav.optimal.mediation.console.domain.MediationUsers}.
 */
@RestController
@RequestMapping("/api")
public class MediationUsersResource {

    private final Logger log = LoggerFactory.getLogger(MediationUsersResource.class);

    private static final String ENTITY_NAME = "mediationConsoleMediationUsers";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MediationUsersService mediationUsersService;

    public MediationUsersResource(MediationUsersService mediationUsersService) {
        this.mediationUsersService = mediationUsersService;
    }

    /**
     * {@code POST  /mediation-users} : Create a new mediationUsers.
     *
     * @param mediationUsersDTO the mediationUsersDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mediationUsersDTO, or with status {@code 400 (Bad Request)} if the mediationUsers has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/mediation-users")
    public ResponseEntity<MediationUsersDTO> createMediationUsers(@Valid @RequestBody MediationUsersDTO mediationUsersDTO) throws URISyntaxException {
        log.debug("REST request to save MediationUsers : {}", mediationUsersDTO);
        if (mediationUsersDTO.getId() != null) {
            throw new BadRequestAlertException("A new mediationUsers cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MediationUsersDTO result = mediationUsersService.save(mediationUsersDTO);
        return ResponseEntity.created(new URI("/api/mediation-users/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /mediation-users} : Updates an existing mediationUsers.
     *
     * @param mediationUsersDTO the mediationUsersDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mediationUsersDTO,
     * or with status {@code 400 (Bad Request)} if the mediationUsersDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mediationUsersDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/mediation-users")
    public ResponseEntity<MediationUsersDTO> updateMediationUsers(@Valid @RequestBody MediationUsersDTO mediationUsersDTO) throws URISyntaxException {
        log.debug("REST request to update MediationUsers : {}", mediationUsersDTO);
        if (mediationUsersDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MediationUsersDTO result = mediationUsersService.save(mediationUsersDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, mediationUsersDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /mediation-users} : get all the mediationUsers.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mediationUsers in body.
     */
    @GetMapping("/mediation-users")
    public List<MediationUsersDTO> getAllMediationUsers() {
        log.debug("REST request to get all MediationUsers");
        return mediationUsersService.findAll();
    }

    /**
     * {@code GET  /mediation-users/:id} : get the "id" mediationUsers.
     *
     * @param id the id of the mediationUsersDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mediationUsersDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/mediation-users/{id}")
    public ResponseEntity<MediationUsersDTO> getMediationUsers(@PathVariable Long id) {
        log.debug("REST request to get MediationUsers : {}", id);
        Optional<MediationUsersDTO> mediationUsersDTO = mediationUsersService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mediationUsersDTO);
    }

    /**
     * {@code DELETE  /mediation-users/:id} : delete the "id" mediationUsers.
     *
     * @param id the id of the mediationUsersDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/mediation-users/{id}")
    public ResponseEntity<Void> deleteMediationUsers(@PathVariable Long id) {
        log.debug("REST request to delete MediationUsers : {}", id);
        mediationUsersService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
