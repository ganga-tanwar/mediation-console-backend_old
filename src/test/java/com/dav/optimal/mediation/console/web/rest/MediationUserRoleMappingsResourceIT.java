package com.dav.optimal.mediation.console.web.rest;

import com.dav.optimal.mediation.console.MediationConsoleApp;
import com.dav.optimal.mediation.console.domain.MediationUserRoleMappings;
import com.dav.optimal.mediation.console.domain.MediationUsers;
import com.dav.optimal.mediation.console.domain.MediationRoles;
import com.dav.optimal.mediation.console.repository.MediationUserRoleMappingsRepository;
import com.dav.optimal.mediation.console.service.MediationUserRoleMappingsService;
import com.dav.optimal.mediation.console.service.dto.MediationUserRoleMappingsDTO;
import com.dav.optimal.mediation.console.service.mapper.MediationUserRoleMappingsMapper;

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
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link MediationUserRoleMappingsResource} REST controller.
 */
@SpringBootTest(classes = MediationConsoleApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class MediationUserRoleMappingsResourceIT {

    private static final UUID DEFAULT_MEDIATION_USER_ID = UUID.randomUUID();
    private static final UUID UPDATED_MEDIATION_USER_ID = UUID.randomUUID();

    private static final UUID DEFAULT_MEDIATION_ROLE_ID = UUID.randomUUID();
    private static final UUID UPDATED_MEDIATION_ROLE_ID = UUID.randomUUID();

    private static final String DEFAULT_CREATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_CREATED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CREATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_MODIFIED_BY = "AAAAAAAAAA";
    private static final String UPDATED_MODIFIED_BY = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_MODIFIED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_MODIFIED_DATE = LocalDate.now(ZoneId.systemDefault());

    @Autowired
    private MediationUserRoleMappingsRepository mediationUserRoleMappingsRepository;

    @Autowired
    private MediationUserRoleMappingsMapper mediationUserRoleMappingsMapper;

    @Autowired
    private MediationUserRoleMappingsService mediationUserRoleMappingsService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restMediationUserRoleMappingsMockMvc;

    private MediationUserRoleMappings mediationUserRoleMappings;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MediationUserRoleMappings createEntity(EntityManager em) {
        MediationUserRoleMappings mediationUserRoleMappings = new MediationUserRoleMappings()
            .mediationUserId(DEFAULT_MEDIATION_USER_ID)
            .mediationRoleId(DEFAULT_MEDIATION_ROLE_ID)
            .createdBy(DEFAULT_CREATED_BY)
            .createdDate(DEFAULT_CREATED_DATE)
            .modifiedBy(DEFAULT_MODIFIED_BY)
            .modifiedDate(DEFAULT_MODIFIED_DATE);
        // Add required entity
        MediationUsers mediationUsers;
        if (TestUtil.findAll(em, MediationUsers.class).isEmpty()) {
            mediationUsers = MediationUsersResourceIT.createEntity(em);
            em.persist(mediationUsers);
            em.flush();
        } else {
            mediationUsers = TestUtil.findAll(em, MediationUsers.class).get(0);
        }
        mediationUserRoleMappings.getMediationUserIds().add(mediationUsers);
        // Add required entity
        MediationRoles mediationRoles;
        if (TestUtil.findAll(em, MediationRoles.class).isEmpty()) {
            mediationRoles = MediationRolesResourceIT.createEntity(em);
            em.persist(mediationRoles);
            em.flush();
        } else {
            mediationRoles = TestUtil.findAll(em, MediationRoles.class).get(0);
        }
        mediationUserRoleMappings.getMediationRoleIds().add(mediationRoles);
        return mediationUserRoleMappings;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MediationUserRoleMappings createUpdatedEntity(EntityManager em) {
        MediationUserRoleMappings mediationUserRoleMappings = new MediationUserRoleMappings()
            .mediationUserId(UPDATED_MEDIATION_USER_ID)
            .mediationRoleId(UPDATED_MEDIATION_ROLE_ID)
            .createdBy(UPDATED_CREATED_BY)
            .createdDate(UPDATED_CREATED_DATE)
            .modifiedBy(UPDATED_MODIFIED_BY)
            .modifiedDate(UPDATED_MODIFIED_DATE);
        // Add required entity
        MediationUsers mediationUsers;
        if (TestUtil.findAll(em, MediationUsers.class).isEmpty()) {
            mediationUsers = MediationUsersResourceIT.createUpdatedEntity(em);
            em.persist(mediationUsers);
            em.flush();
        } else {
            mediationUsers = TestUtil.findAll(em, MediationUsers.class).get(0);
        }
        mediationUserRoleMappings.getMediationUserIds().add(mediationUsers);
        // Add required entity
        MediationRoles mediationRoles;
        if (TestUtil.findAll(em, MediationRoles.class).isEmpty()) {
            mediationRoles = MediationRolesResourceIT.createUpdatedEntity(em);
            em.persist(mediationRoles);
            em.flush();
        } else {
            mediationRoles = TestUtil.findAll(em, MediationRoles.class).get(0);
        }
        mediationUserRoleMappings.getMediationRoleIds().add(mediationRoles);
        return mediationUserRoleMappings;
    }

    @BeforeEach
    public void initTest() {
        mediationUserRoleMappings = createEntity(em);
    }

    @Test
    @Transactional
    public void createMediationUserRoleMappings() throws Exception {
        int databaseSizeBeforeCreate = mediationUserRoleMappingsRepository.findAll().size();

        // Create the MediationUserRoleMappings
        MediationUserRoleMappingsDTO mediationUserRoleMappingsDTO = mediationUserRoleMappingsMapper.toDto(mediationUserRoleMappings);
        restMediationUserRoleMappingsMockMvc.perform(post("/api/mediation-user-role-mappings")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(mediationUserRoleMappingsDTO)))
            .andExpect(status().isCreated());

        // Validate the MediationUserRoleMappings in the database
        List<MediationUserRoleMappings> mediationUserRoleMappingsList = mediationUserRoleMappingsRepository.findAll();
        assertThat(mediationUserRoleMappingsList).hasSize(databaseSizeBeforeCreate + 1);
        MediationUserRoleMappings testMediationUserRoleMappings = mediationUserRoleMappingsList.get(mediationUserRoleMappingsList.size() - 1);
        assertThat(testMediationUserRoleMappings.getMediationUserId()).isEqualTo(DEFAULT_MEDIATION_USER_ID);
        assertThat(testMediationUserRoleMappings.getMediationRoleId()).isEqualTo(DEFAULT_MEDIATION_ROLE_ID);
        assertThat(testMediationUserRoleMappings.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testMediationUserRoleMappings.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testMediationUserRoleMappings.getModifiedBy()).isEqualTo(DEFAULT_MODIFIED_BY);
        assertThat(testMediationUserRoleMappings.getModifiedDate()).isEqualTo(DEFAULT_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void createMediationUserRoleMappingsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mediationUserRoleMappingsRepository.findAll().size();

        // Create the MediationUserRoleMappings with an existing ID
        mediationUserRoleMappings.setId(1L);
        MediationUserRoleMappingsDTO mediationUserRoleMappingsDTO = mediationUserRoleMappingsMapper.toDto(mediationUserRoleMappings);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMediationUserRoleMappingsMockMvc.perform(post("/api/mediation-user-role-mappings")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(mediationUserRoleMappingsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MediationUserRoleMappings in the database
        List<MediationUserRoleMappings> mediationUserRoleMappingsList = mediationUserRoleMappingsRepository.findAll();
        assertThat(mediationUserRoleMappingsList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkMediationUserIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mediationUserRoleMappingsRepository.findAll().size();
        // set the field null
        mediationUserRoleMappings.setMediationUserId(null);

        // Create the MediationUserRoleMappings, which fails.
        MediationUserRoleMappingsDTO mediationUserRoleMappingsDTO = mediationUserRoleMappingsMapper.toDto(mediationUserRoleMappings);

        restMediationUserRoleMappingsMockMvc.perform(post("/api/mediation-user-role-mappings")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(mediationUserRoleMappingsDTO)))
            .andExpect(status().isBadRequest());

        List<MediationUserRoleMappings> mediationUserRoleMappingsList = mediationUserRoleMappingsRepository.findAll();
        assertThat(mediationUserRoleMappingsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMediationRoleIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = mediationUserRoleMappingsRepository.findAll().size();
        // set the field null
        mediationUserRoleMappings.setMediationRoleId(null);

        // Create the MediationUserRoleMappings, which fails.
        MediationUserRoleMappingsDTO mediationUserRoleMappingsDTO = mediationUserRoleMappingsMapper.toDto(mediationUserRoleMappings);

        restMediationUserRoleMappingsMockMvc.perform(post("/api/mediation-user-role-mappings")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(mediationUserRoleMappingsDTO)))
            .andExpect(status().isBadRequest());

        List<MediationUserRoleMappings> mediationUserRoleMappingsList = mediationUserRoleMappingsRepository.findAll();
        assertThat(mediationUserRoleMappingsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCreatedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = mediationUserRoleMappingsRepository.findAll().size();
        // set the field null
        mediationUserRoleMappings.setCreatedBy(null);

        // Create the MediationUserRoleMappings, which fails.
        MediationUserRoleMappingsDTO mediationUserRoleMappingsDTO = mediationUserRoleMappingsMapper.toDto(mediationUserRoleMappings);

        restMediationUserRoleMappingsMockMvc.perform(post("/api/mediation-user-role-mappings")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(mediationUserRoleMappingsDTO)))
            .andExpect(status().isBadRequest());

        List<MediationUserRoleMappings> mediationUserRoleMappingsList = mediationUserRoleMappingsRepository.findAll();
        assertThat(mediationUserRoleMappingsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCreatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = mediationUserRoleMappingsRepository.findAll().size();
        // set the field null
        mediationUserRoleMappings.setCreatedDate(null);

        // Create the MediationUserRoleMappings, which fails.
        MediationUserRoleMappingsDTO mediationUserRoleMappingsDTO = mediationUserRoleMappingsMapper.toDto(mediationUserRoleMappings);

        restMediationUserRoleMappingsMockMvc.perform(post("/api/mediation-user-role-mappings")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(mediationUserRoleMappingsDTO)))
            .andExpect(status().isBadRequest());

        List<MediationUserRoleMappings> mediationUserRoleMappingsList = mediationUserRoleMappingsRepository.findAll();
        assertThat(mediationUserRoleMappingsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMediationUserRoleMappings() throws Exception {
        // Initialize the database
        mediationUserRoleMappingsRepository.saveAndFlush(mediationUserRoleMappings);

        // Get all the mediationUserRoleMappingsList
        restMediationUserRoleMappingsMockMvc.perform(get("/api/mediation-user-role-mappings?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mediationUserRoleMappings.getId().intValue())))
            .andExpect(jsonPath("$.[*].mediationUserId").value(hasItem(DEFAULT_MEDIATION_USER_ID.toString())))
            .andExpect(jsonPath("$.[*].mediationRoleId").value(hasItem(DEFAULT_MEDIATION_ROLE_ID.toString())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].modifiedBy").value(hasItem(DEFAULT_MODIFIED_BY)))
            .andExpect(jsonPath("$.[*].modifiedDate").value(hasItem(DEFAULT_MODIFIED_DATE.toString())));
    }
    
    @Test
    @Transactional
    public void getMediationUserRoleMappings() throws Exception {
        // Initialize the database
        mediationUserRoleMappingsRepository.saveAndFlush(mediationUserRoleMappings);

        // Get the mediationUserRoleMappings
        restMediationUserRoleMappingsMockMvc.perform(get("/api/mediation-user-role-mappings/{id}", mediationUserRoleMappings.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(mediationUserRoleMappings.getId().intValue()))
            .andExpect(jsonPath("$.mediationUserId").value(DEFAULT_MEDIATION_USER_ID.toString()))
            .andExpect(jsonPath("$.mediationRoleId").value(DEFAULT_MEDIATION_ROLE_ID.toString()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.modifiedBy").value(DEFAULT_MODIFIED_BY))
            .andExpect(jsonPath("$.modifiedDate").value(DEFAULT_MODIFIED_DATE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingMediationUserRoleMappings() throws Exception {
        // Get the mediationUserRoleMappings
        restMediationUserRoleMappingsMockMvc.perform(get("/api/mediation-user-role-mappings/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMediationUserRoleMappings() throws Exception {
        // Initialize the database
        mediationUserRoleMappingsRepository.saveAndFlush(mediationUserRoleMappings);

        int databaseSizeBeforeUpdate = mediationUserRoleMappingsRepository.findAll().size();

        // Update the mediationUserRoleMappings
        MediationUserRoleMappings updatedMediationUserRoleMappings = mediationUserRoleMappingsRepository.findById(mediationUserRoleMappings.getId()).get();
        // Disconnect from session so that the updates on updatedMediationUserRoleMappings are not directly saved in db
        em.detach(updatedMediationUserRoleMappings);
        updatedMediationUserRoleMappings
            .mediationUserId(UPDATED_MEDIATION_USER_ID)
            .mediationRoleId(UPDATED_MEDIATION_ROLE_ID)
            .createdBy(UPDATED_CREATED_BY)
            .createdDate(UPDATED_CREATED_DATE)
            .modifiedBy(UPDATED_MODIFIED_BY)
            .modifiedDate(UPDATED_MODIFIED_DATE);
        MediationUserRoleMappingsDTO mediationUserRoleMappingsDTO = mediationUserRoleMappingsMapper.toDto(updatedMediationUserRoleMappings);

        restMediationUserRoleMappingsMockMvc.perform(put("/api/mediation-user-role-mappings")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(mediationUserRoleMappingsDTO)))
            .andExpect(status().isOk());

        // Validate the MediationUserRoleMappings in the database
        List<MediationUserRoleMappings> mediationUserRoleMappingsList = mediationUserRoleMappingsRepository.findAll();
        assertThat(mediationUserRoleMappingsList).hasSize(databaseSizeBeforeUpdate);
        MediationUserRoleMappings testMediationUserRoleMappings = mediationUserRoleMappingsList.get(mediationUserRoleMappingsList.size() - 1);
        assertThat(testMediationUserRoleMappings.getMediationUserId()).isEqualTo(UPDATED_MEDIATION_USER_ID);
        assertThat(testMediationUserRoleMappings.getMediationRoleId()).isEqualTo(UPDATED_MEDIATION_ROLE_ID);
        assertThat(testMediationUserRoleMappings.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testMediationUserRoleMappings.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testMediationUserRoleMappings.getModifiedBy()).isEqualTo(UPDATED_MODIFIED_BY);
        assertThat(testMediationUserRoleMappings.getModifiedDate()).isEqualTo(UPDATED_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingMediationUserRoleMappings() throws Exception {
        int databaseSizeBeforeUpdate = mediationUserRoleMappingsRepository.findAll().size();

        // Create the MediationUserRoleMappings
        MediationUserRoleMappingsDTO mediationUserRoleMappingsDTO = mediationUserRoleMappingsMapper.toDto(mediationUserRoleMappings);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMediationUserRoleMappingsMockMvc.perform(put("/api/mediation-user-role-mappings")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(mediationUserRoleMappingsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MediationUserRoleMappings in the database
        List<MediationUserRoleMappings> mediationUserRoleMappingsList = mediationUserRoleMappingsRepository.findAll();
        assertThat(mediationUserRoleMappingsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMediationUserRoleMappings() throws Exception {
        // Initialize the database
        mediationUserRoleMappingsRepository.saveAndFlush(mediationUserRoleMappings);

        int databaseSizeBeforeDelete = mediationUserRoleMappingsRepository.findAll().size();

        // Delete the mediationUserRoleMappings
        restMediationUserRoleMappingsMockMvc.perform(delete("/api/mediation-user-role-mappings/{id}", mediationUserRoleMappings.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<MediationUserRoleMappings> mediationUserRoleMappingsList = mediationUserRoleMappingsRepository.findAll();
        assertThat(mediationUserRoleMappingsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
