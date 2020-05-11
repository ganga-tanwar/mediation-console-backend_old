package com.dav.optimal.mediation.console.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;
import java.time.Instant;
import java.util.UUID;

/**
 * Class representing Roles
 */
@Entity
@Table(name = "mediation_user_role_mappings")
public class MediationUserRoleMappings implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "mediation_user_id", nullable = false)
    private UUID mediationUserId;

    @NotNull
    @Column(name = "mediation_role_id", nullable = false)
    private UUID mediationRoleId;

    @NotNull
    @Size(max = 100)
    @Column(name = "created_by", length = 100, nullable = false)
    private String createdBy;

    @NotNull
    @Column(name = "created_date", nullable = false)
    private Instant createdDate;

    @Size(max = 100)
    @Column(name = "modified_by", length = 100)
    private String modifiedBy;

    @Column(name = "modified_date")
    private Instant modifiedDate;

    @OneToOne(optional = false)
    @NotNull
    @JoinColumn(unique = true)
    private MediationUsers mediationUserId;

    @OneToOne(optional = false)
    @NotNull
    @JoinColumn(unique = true)
    private MediationRoles mediationRoleId;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UUID getMediationUserId() {
        return mediationUserId;
    }

    public MediationUserRoleMappings mediationUserId(UUID mediationUserId) {
        this.mediationUserId = mediationUserId;
        return this;
    }

    public void setMediationUserId(UUID mediationUserId) {
        this.mediationUserId = mediationUserId;
    }

    public UUID getMediationRoleId() {
        return mediationRoleId;
    }

    public MediationUserRoleMappings mediationRoleId(UUID mediationRoleId) {
        this.mediationRoleId = mediationRoleId;
        return this;
    }

    public void setMediationRoleId(UUID mediationRoleId) {
        this.mediationRoleId = mediationRoleId;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public MediationUserRoleMappings createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public MediationUserRoleMappings createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public MediationUserRoleMappings modifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
        return this;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Instant getModifiedDate() {
        return modifiedDate;
    }

    public MediationUserRoleMappings modifiedDate(Instant modifiedDate) {
        this.modifiedDate = modifiedDate;
        return this;
    }

    public void setModifiedDate(Instant modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public MediationUsers getMediationUserId() {
        return mediationUserId;
    }

    public MediationUserRoleMappings mediationUserId(MediationUsers mediationUsers) {
        this.mediationUserId = mediationUsers;
        return this;
    }

    public void setMediationUserId(MediationUsers mediationUsers) {
        this.mediationUserId = mediationUsers;
    }

    public MediationRoles getMediationRoleId() {
        return mediationRoleId;
    }

    public MediationUserRoleMappings mediationRoleId(MediationRoles mediationRoles) {
        this.mediationRoleId = mediationRoles;
        return this;
    }

    public void setMediationRoleId(MediationRoles mediationRoles) {
        this.mediationRoleId = mediationRoles;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MediationUserRoleMappings)) {
            return false;
        }
        return id != null && id.equals(((MediationUserRoleMappings) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MediationUserRoleMappings{" +
            "id=" + getId() +
            ", mediationUserId='" + getMediationUserId() + "'" +
            ", mediationRoleId='" + getMediationRoleId() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", modifiedBy='" + getModifiedBy() + "'" +
            ", modifiedDate='" + getModifiedDate() + "'" +
            "}";
    }
}
