package com.dav.optimal.mediation.console.web.rest;

import com.dav.optimal.mediation.console.MediationConsoleApp;
import com.dav.optimal.mediation.console.domain.MediationUsers;
import com.dav.optimal.mediation.console.repository.MediationUsersRepository;
import com.dav.optimal.mediation.console.service.MediationUsersService;
import com.dav.optimal.mediation.console.service.dto.MediationUsersDTO;
import com.dav.optimal.mediation.console.service.mapper.MediationUsersMapper;

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
import java.math.BigDecimal;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link MediationUsersResource} REST controller.
 */
@SpringBootTest(classes = MediationConsoleApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class MediationUsersResourceIT {

    private static final UUID DEFAULT_MEDIATION_USERID = UUID.randomUUID();
    private static final UUID UPDATED_MEDIATION_USERID = UUID.randomUUID();

    private static final String DEFAULT_USER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_USER_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_PASSWORD = "AAAAAAAAAA";
    private static final String UPDATED_PASSWORD = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_CONTACT_NO = new BigDecimal(1);
    private static final BigDecimal UPDATED_CONTACT_NO = new BigDecimal(2);

    private static final Boolean DEFAULT_ACTIVE = false;
    private static final Boolean UPDATED_ACTIVE = true;

    private static final String DEFAULT_CREATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY = "BBBBBBBBBB";

    private static final Instant DEFAULT_CREATED_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_MODIFIED_BY = "AAAAAAAAAA";
    private static final String UPDATED_MODIFIED_BY = "BBBBBBBBBB";

    private static final Instant DEFAULT_MODIFIED_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_MODIFIED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private MediationUsersRepository mediationUsersRepository;

    @Autowired
    private MediationUsersMapper mediationUsersMapper;

    @Autowired
    private MediationUsersService mediationUsersService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restMediationUsersMockMvc;

    private MediationUsers mediationUsers;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MediationUsers createEntity(EntityManager em) {
        MediationUsers mediationUsers = new MediationUsers()
            .mediationUserid(DEFAULT_MEDIATION_USERID)
            .userName(DEFAULT_USER_NAME)
            .password(DEFAULT_PASSWORD)
            .email(DEFAULT_EMAIL)
            .contactNo(DEFAULT_CONTACT_NO)
            .active(DEFAULT_ACTIVE)
            .createdBy(DEFAULT_CREATED_BY)
            .createdDate(DEFAULT_CREATED_DATE)
            .modifiedBy(DEFAULT_MODIFIED_BY)
            .modifiedDate(DEFAULT_MODIFIED_DATE);
        return mediationUsers;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MediationUsers createUpdatedEntity(EntityManager em) {
        MediationUsers mediationUsers = new MediationUsers()
            .mediationUserid(UPDATED_MEDIATION_USERID)
            .userName(UPDATED_USER_NAME)
            .password(UPDATED_PASSWORD)
            .email(UPDATED_EMAIL)
            .contactNo(UPDATED_CONTACT_NO)
            .active(UPDATED_ACTIVE)
            .createdBy(UPDATED_CREATED_BY)
            .createdDate(UPDATED_CREATED_DATE)
            .modifiedBy(UPDATED_MODIFIED_BY)
            .modifiedDate(UPDATED_MODIFIED_DATE);
        return mediationUsers;
    }

    @BeforeEach
    public void initTest() {
        mediationUsers = createEntity(em);
    }

    @Test
    @Transactional
    public void createMediationUsers() throws Exception {
        int databaseSizeBeforeCreate = mediationUsersRepository.findAll().size();

        // Create the MediationUsers
        MediationUsersDTO mediationUsersDTO = mediationUsersMapper.toDto(mediationUsers);
        restMediationUsersMockMvc.perform(post("/api/mediation-users")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(mediationUsersDTO)))
            .andExpect(status().isCreated());

        // Validate the MediationUsers in the database
        List<MediationUsers> mediationUsersList = mediationUsersRepository.findAll();
        assertThat(mediationUsersList).hasSize(databaseSizeBeforeCreate + 1);
        MediationUsers testMediationUsers = mediationUsersList.get(mediationUsersList.size() - 1);
        assertThat(testMediationUsers.getMediationUserid()).isEqualTo(DEFAULT_MEDIATION_USERID);
        assertThat(testMediationUsers.getUserName()).isEqualTo(DEFAULT_USER_NAME);
        assertThat(testMediationUsers.getPassword()).isEqualTo(DEFAULT_PASSWORD);
        assertThat(testMediationUsers.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testMediationUsers.getContactNo()).isEqualTo(DEFAULT_CONTACT_NO);
        assertThat(testMediationUsers.isActive()).isEqualTo(DEFAULT_ACTIVE);
        assertThat(testMediationUsers.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testMediationUsers.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testMediationUsers.getModifiedBy()).isEqualTo(DEFAULT_MODIFIED_BY);
        assertThat(testMediationUsers.getModifiedDate()).isEqualTo(DEFAULT_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void createMediationUsersWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mediationUsersRepository.findAll().size();

        // Create the MediationUsers with an existing ID
        mediationUsers.setId(1L);
        MediationUsersDTO mediationUsersDTO = mediationUsersMapper.toDto(mediationUsers);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMediationUsersMockMvc.perform(post("/api/mediation-users")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(mediationUsersDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MediationUsers in the database
        List<MediationUsers> mediationUsersList = mediationUsersRepository.findAll();
        assertThat(mediationUsersList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkMediationUseridIsRequired() throws Exception {
        int databaseSizeBeforeTest = mediationUsersRepository.findAll().size();
        // set the field null
        mediationUsers.setMediationUserid(null);

        // Create the MediationUsers, which fails.
        MediationUsersDTO mediationUsersDTO = mediationUsersMapper.toDto(mediationUsers);

        restMediationUsersMockMvc.perform(post("/api/mediation-users")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(mediationUsersDTO)))
            .andExpect(status().isBadRequest());

        List<MediationUsers> mediationUsersList = mediationUsersRepository.findAll();
        assertThat(mediationUsersList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkUserNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = mediationUsersRepository.findAll().size();
        // set the field null
        mediationUsers.setUserName(null);

        // Create the MediationUsers, which fails.
        MediationUsersDTO mediationUsersDTO = mediationUsersMapper.toDto(mediationUsers);

        restMediationUsersMockMvc.perform(post("/api/mediation-users")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(mediationUsersDTO)))
            .andExpect(status().isBadRequest());

        List<MediationUsers> mediationUsersList = mediationUsersRepository.findAll();
        assertThat(mediationUsersList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPasswordIsRequired() throws Exception {
        int databaseSizeBeforeTest = mediationUsersRepository.findAll().size();
        // set the field null
        mediationUsers.setPassword(null);

        // Create the MediationUsers, which fails.
        MediationUsersDTO mediationUsersDTO = mediationUsersMapper.toDto(mediationUsers);

        restMediationUsersMockMvc.perform(post("/api/mediation-users")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(mediationUsersDTO)))
            .andExpect(status().isBadRequest());

        List<MediationUsers> mediationUsersList = mediationUsersRepository.findAll();
        assertThat(mediationUsersList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEmailIsRequired() throws Exception {
        int databaseSizeBeforeTest = mediationUsersRepository.findAll().size();
        // set the field null
        mediationUsers.setEmail(null);

        // Create the MediationUsers, which fails.
        MediationUsersDTO mediationUsersDTO = mediationUsersMapper.toDto(mediationUsers);

        restMediationUsersMockMvc.perform(post("/api/mediation-users")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(mediationUsersDTO)))
            .andExpect(status().isBadRequest());

        List<MediationUsers> mediationUsersList = mediationUsersRepository.findAll();
        assertThat(mediationUsersList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCreatedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = mediationUsersRepository.findAll().size();
        // set the field null
        mediationUsers.setCreatedBy(null);

        // Create the MediationUsers, which fails.
        MediationUsersDTO mediationUsersDTO = mediationUsersMapper.toDto(mediationUsers);

        restMediationUsersMockMvc.perform(post("/api/mediation-users")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(mediationUsersDTO)))
            .andExpect(status().isBadRequest());

        List<MediationUsers> mediationUsersList = mediationUsersRepository.findAll();
        assertThat(mediationUsersList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCreatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = mediationUsersRepository.findAll().size();
        // set the field null
        mediationUsers.setCreatedDate(null);

        // Create the MediationUsers, which fails.
        MediationUsersDTO mediationUsersDTO = mediationUsersMapper.toDto(mediationUsers);

        restMediationUsersMockMvc.perform(post("/api/mediation-users")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(mediationUsersDTO)))
            .andExpect(status().isBadRequest());

        List<MediationUsers> mediationUsersList = mediationUsersRepository.findAll();
        assertThat(mediationUsersList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMediationUsers() throws Exception {
        // Initialize the database
        mediationUsersRepository.saveAndFlush(mediationUsers);

        // Get all the mediationUsersList
        restMediationUsersMockMvc.perform(get("/api/mediation-users?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mediationUsers.getId().intValue())))
            .andExpect(jsonPath("$.[*].mediationUserid").value(hasItem(DEFAULT_MEDIATION_USERID.toString())))
            .andExpect(jsonPath("$.[*].userName").value(hasItem(DEFAULT_USER_NAME)))
            .andExpect(jsonPath("$.[*].password").value(hasItem(DEFAULT_PASSWORD)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].contactNo").value(hasItem(DEFAULT_CONTACT_NO.intValue())))
            .andExpect(jsonPath("$.[*].active").value(hasItem(DEFAULT_ACTIVE.booleanValue())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].modifiedBy").value(hasItem(DEFAULT_MODIFIED_BY)))
            .andExpect(jsonPath("$.[*].modifiedDate").value(hasItem(DEFAULT_MODIFIED_DATE.toString())));
    }
    
    @Test
    @Transactional
    public void getMediationUsers() throws Exception {
        // Initialize the database
        mediationUsersRepository.saveAndFlush(mediationUsers);

        // Get the mediationUsers
        restMediationUsersMockMvc.perform(get("/api/mediation-users/{id}", mediationUsers.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(mediationUsers.getId().intValue()))
            .andExpect(jsonPath("$.mediationUserid").value(DEFAULT_MEDIATION_USERID.toString()))
            .andExpect(jsonPath("$.userName").value(DEFAULT_USER_NAME))
            .andExpect(jsonPath("$.password").value(DEFAULT_PASSWORD))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL))
            .andExpect(jsonPath("$.contactNo").value(DEFAULT_CONTACT_NO.intValue()))
            .andExpect(jsonPath("$.active").value(DEFAULT_ACTIVE.booleanValue()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.modifiedBy").value(DEFAULT_MODIFIED_BY))
            .andExpect(jsonPath("$.modifiedDate").value(DEFAULT_MODIFIED_DATE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingMediationUsers() throws Exception {
        // Get the mediationUsers
        restMediationUsersMockMvc.perform(get("/api/mediation-users/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMediationUsers() throws Exception {
        // Initialize the database
        mediationUsersRepository.saveAndFlush(mediationUsers);

        int databaseSizeBeforeUpdate = mediationUsersRepository.findAll().size();

        // Update the mediationUsers
        MediationUsers updatedMediationUsers = mediationUsersRepository.findById(mediationUsers.getId()).get();
        // Disconnect from session so that the updates on updatedMediationUsers are not directly saved in db
        em.detach(updatedMediationUsers);
        updatedMediationUsers
            .mediationUserid(UPDATED_MEDIATION_USERID)
            .userName(UPDATED_USER_NAME)
            .password(UPDATED_PASSWORD)
            .email(UPDATED_EMAIL)
            .contactNo(UPDATED_CONTACT_NO)
            .active(UPDATED_ACTIVE)
            .createdBy(UPDATED_CREATED_BY)
            .createdDate(UPDATED_CREATED_DATE)
            .modifiedBy(UPDATED_MODIFIED_BY)
            .modifiedDate(UPDATED_MODIFIED_DATE);
        MediationUsersDTO mediationUsersDTO = mediationUsersMapper.toDto(updatedMediationUsers);

        restMediationUsersMockMvc.perform(put("/api/mediation-users")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(mediationUsersDTO)))
            .andExpect(status().isOk());

        // Validate the MediationUsers in the database
        List<MediationUsers> mediationUsersList = mediationUsersRepository.findAll();
        assertThat(mediationUsersList).hasSize(databaseSizeBeforeUpdate);
        MediationUsers testMediationUsers = mediationUsersList.get(mediationUsersList.size() - 1);
        assertThat(testMediationUsers.getMediationUserid()).isEqualTo(UPDATED_MEDIATION_USERID);
        assertThat(testMediationUsers.getUserName()).isEqualTo(UPDATED_USER_NAME);
        assertThat(testMediationUsers.getPassword()).isEqualTo(UPDATED_PASSWORD);
        assertThat(testMediationUsers.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testMediationUsers.getContactNo()).isEqualTo(UPDATED_CONTACT_NO);
        assertThat(testMediationUsers.isActive()).isEqualTo(UPDATED_ACTIVE);
        assertThat(testMediationUsers.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testMediationUsers.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testMediationUsers.getModifiedBy()).isEqualTo(UPDATED_MODIFIED_BY);
        assertThat(testMediationUsers.getModifiedDate()).isEqualTo(UPDATED_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingMediationUsers() throws Exception {
        int databaseSizeBeforeUpdate = mediationUsersRepository.findAll().size();

        // Create the MediationUsers
        MediationUsersDTO mediationUsersDTO = mediationUsersMapper.toDto(mediationUsers);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMediationUsersMockMvc.perform(put("/api/mediation-users")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(mediationUsersDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MediationUsers in the database
        List<MediationUsers> mediationUsersList = mediationUsersRepository.findAll();
        assertThat(mediationUsersList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMediationUsers() throws Exception {
        // Initialize the database
        mediationUsersRepository.saveAndFlush(mediationUsers);

        int databaseSizeBeforeDelete = mediationUsersRepository.findAll().size();

        // Delete the mediationUsers
        restMediationUsersMockMvc.perform(delete("/api/mediation-users/{id}", mediationUsers.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<MediationUsers> mediationUsersList = mediationUsersRepository.findAll();
        assertThat(mediationUsersList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
