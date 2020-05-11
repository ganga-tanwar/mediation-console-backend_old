package com.dav.optimal.mediation.console.web.rest;

import com.dav.optimal.mediation.console.MediationConsoleApp;
import com.dav.optimal.mediation.console.domain.MediationRoles;
import com.dav.optimal.mediation.console.repository.MediationRolesRepository;
import com.dav.optimal.mediation.console.service.MediationRolesService;
import com.dav.optimal.mediation.console.service.dto.MediationRolesDTO;
import com.dav.optimal.mediation.console.service.mapper.MediationRolesMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link MediationRolesResource} REST controller.
 */
@SpringBootTest(classes = MediationConsoleApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class MediationRolesResourceIT {

    private static final UUID DEFAULT_MEDIATION_ROLE_ID = UUID.randomUUID();
    private static final UUID UPDATED_MEDIATION_ROLE_ID = UUID.randomUUID();

    private static final String DEFAULT_ROLE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ROLE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ROLE_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_ROLE_DESCRIPTION = "BBBBBBBBBB";

    @Autowired
    private MediationRolesRepository mediationRolesRepository;

    @Autowired
    private MediationRolesMapper mediationRolesMapper;

    @Autowired
    private MediationRolesService mediationRolesService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restMediationRolesMockMvc;

    private MediationRoles mediationRoles;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MediationRoles createEntity(EntityManager em) {
        MediationRoles mediationRoles = new MediationRoles()
            .mediationRoleId(DEFAULT_MEDIATION_ROLE_ID)
            .roleName(DEFAULT_ROLE_NAME)
            .roleDescription(DEFAULT_ROLE_DESCRIPTION);
        return mediationRoles;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MediationRoles createUpdatedEntity(EntityManager em) {
        MediationRoles mediationRoles = new MediationRoles()
            .mediationRoleId(UPDATED_MEDIATION_ROLE_ID)
            .roleName(UPDATED_ROLE_NAME)
            .roleDescription(UPDATED_ROLE_DESCRIPTION);
        return mediationRoles;
    }

    @BeforeEach
    public void initTest() {
        mediationRoles = createEntity(em);
    }

    @Test
    @Transactional
    public void createMediationRoles() throws Exception {
        int databaseSizeBeforeCreate = mediationRolesRepository.findAll().size();

        // Create the MediationRoles
        MediationRolesDTO mediationRolesDTO = mediationRolesMapper.toDto(mediationRoles);
        restMediationRolesMockMvc.perform(post("/api/mediation-roles")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(mediationRolesDTO)))
            .andExpect(status().isCreated());

        // Validate the MediationRoles in the database
        List<MediationRoles> mediationRolesList = mediationRolesRepository.findAll();
        assertThat(mediationRolesList).hasSize(databaseSizeBeforeCreate + 1);
        MediationRoles testMediationRoles = mediationRolesList.get(mediationRolesList.size() - 1);
        assertThat(testMediationRoles.getMediationRoleId()).isEqualTo(DEFAULT_MEDIATION_ROLE_ID);
        assertThat(testMediationRoles.getRoleName()).isEqualTo(DEFAULT_ROLE_NAME);
        assertThat(testMediationRoles.getRoleDescription()).isEqualTo(DEFAULT_ROLE_DESCRIPTION);
    }

    @Test
    @Transactional
    public void createMediationRolesWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mediationRolesRepository.findAll().size();

        // Create the MediationRoles with an existing ID
        mediationRoles.setId(1L);
        MediationRolesDTO mediationRolesDTO = mediationRolesMapper.toDto(mediationRoles);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMediationRolesMockMvc.perform(post("/api/mediation-roles")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(mediationRolesDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MediationRoles in the database
        List<MediationRoles> mediationRolesList = mediationRolesRepository.findAll();
        assertThat(mediationRolesList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkMediationRoleIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mediationRolesRepository.findAll().size();
        // set the field null
        mediationRoles.setMediationRoleId(null);

        // Create the MediationRoles, which fails.
        MediationRolesDTO mediationRolesDTO = mediationRolesMapper.toDto(mediationRoles);

        restMediationRolesMockMvc.perform(post("/api/mediation-roles")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(mediationRolesDTO)))
            .andExpect(status().isBadRequest());

        List<MediationRoles> mediationRolesList = mediationRolesRepository.findAll();
        assertThat(mediationRolesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkRoleNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = mediationRolesRepository.findAll().size();
        // set the field null
        mediationRoles.setRoleName(null);

        // Create the MediationRoles, which fails.
        MediationRolesDTO mediationRolesDTO = mediationRolesMapper.toDto(mediationRoles);

        restMediationRolesMockMvc.perform(post("/api/mediation-roles")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(mediationRolesDTO)))
            .andExpect(status().isBadRequest());

        List<MediationRoles> mediationRolesList = mediationRolesRepository.findAll();
        assertThat(mediationRolesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMediationRoles() throws Exception {
        // Initialize the database
        mediationRolesRepository.saveAndFlush(mediationRoles);

        // Get all the mediationRolesList
        restMediationRolesMockMvc.perform(get("/api/mediation-roles?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mediationRoles.getId().intValue())))
            .andExpect(jsonPath("$.[*].mediationRoleId").value(hasItem(DEFAULT_MEDIATION_ROLE_ID.toString())))
            .andExpect(jsonPath("$.[*].roleName").value(hasItem(DEFAULT_ROLE_NAME)))
            .andExpect(jsonPath("$.[*].roleDescription").value(hasItem(DEFAULT_ROLE_DESCRIPTION)));
    }
    
    @Test
    @Transactional
    public void getMediationRoles() throws Exception {
        // Initialize the database
        mediationRolesRepository.saveAndFlush(mediationRoles);

        // Get the mediationRoles
        restMediationRolesMockMvc.perform(get("/api/mediation-roles/{id}", mediationRoles.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(mediationRoles.getId().intValue()))
            .andExpect(jsonPath("$.mediationRoleId").value(DEFAULT_MEDIATION_ROLE_ID.toString()))
            .andExpect(jsonPath("$.roleName").value(DEFAULT_ROLE_NAME))
            .andExpect(jsonPath("$.roleDescription").value(DEFAULT_ROLE_DESCRIPTION));
    }

    @Test
    @Transactional
    public void getNonExistingMediationRoles() throws Exception {
        // Get the mediationRoles
        restMediationRolesMockMvc.perform(get("/api/mediation-roles/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMediationRoles() throws Exception {
        // Initialize the database
        mediationRolesRepository.saveAndFlush(mediationRoles);

        int databaseSizeBeforeUpdate = mediationRolesRepository.findAll().size();

        // Update the mediationRoles
        MediationRoles updatedMediationRoles = mediationRolesRepository.findById(mediationRoles.getId()).get();
        // Disconnect from session so that the updates on updatedMediationRoles are not directly saved in db
        em.detach(updatedMediationRoles);
        updatedMediationRoles
            .mediationRoleId(UPDATED_MEDIATION_ROLE_ID)
            .roleName(UPDATED_ROLE_NAME)
            .roleDescription(UPDATED_ROLE_DESCRIPTION);
        MediationRolesDTO mediationRolesDTO = mediationRolesMapper.toDto(updatedMediationRoles);

        restMediationRolesMockMvc.perform(put("/api/mediation-roles")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(mediationRolesDTO)))
            .andExpect(status().isOk());

        // Validate the MediationRoles in the database
        List<MediationRoles> mediationRolesList = mediationRolesRepository.findAll();
        assertThat(mediationRolesList).hasSize(databaseSizeBeforeUpdate);
        MediationRoles testMediationRoles = mediationRolesList.get(mediationRolesList.size() - 1);
        assertThat(testMediationRoles.getMediationRoleId()).isEqualTo(UPDATED_MEDIATION_ROLE_ID);
        assertThat(testMediationRoles.getRoleName()).isEqualTo(UPDATED_ROLE_NAME);
        assertThat(testMediationRoles.getRoleDescription()).isEqualTo(UPDATED_ROLE_DESCRIPTION);
    }

    @Test
    @Transactional
    public void updateNonExistingMediationRoles() throws Exception {
        int databaseSizeBeforeUpdate = mediationRolesRepository.findAll().size();

        // Create the MediationRoles
        MediationRolesDTO mediationRolesDTO = mediationRolesMapper.toDto(mediationRoles);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMediationRolesMockMvc.perform(put("/api/mediation-roles")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(mediationRolesDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MediationRoles in the database
        List<MediationRoles> mediationRolesList = mediationRolesRepository.findAll();
        assertThat(mediationRolesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMediationRoles() throws Exception {
        // Initialize the database
        mediationRolesRepository.saveAndFlush(mediationRoles);

        int databaseSizeBeforeDelete = mediationRolesRepository.findAll().size();

        // Delete the mediationRoles
        restMediationRolesMockMvc.perform(delete("/api/mediation-roles/{id}", mediationRoles.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<MediationRoles> mediationRolesList = mediationRolesRepository.findAll();
        assertThat(mediationRolesList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
