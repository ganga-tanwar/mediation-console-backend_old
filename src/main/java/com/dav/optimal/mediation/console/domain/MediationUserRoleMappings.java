package com.dav.optimal.mediation.console.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
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
    private LocalDate createdDate;

    @Size(max = 100)
    @Column(name = "modified_by", length = 100)
    private String modifiedBy;

    @Column(name = "modified_date")
    private LocalDate modifiedDate;

    @OneToMany(mappedBy = "mediationUserId")
    private Set<MediationUsers> mediationUserIds = new HashSet<>();

    @OneToMany(mappedBy = "mediationRoleId")
    private Set<MediationRoles> mediationRoleIds = new HashSet<>();

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

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public MediationUserRoleMappings createdDate(LocalDate createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(LocalDate createdDate) {
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

    public LocalDate getModifiedDate() {
        return modifiedDate;
    }

    public MediationUserRoleMappings modifiedDate(LocalDate modifiedDate) {
        this.modifiedDate = modifiedDate;
        return this;
    }

    public void setModifiedDate(LocalDate modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Set<MediationUsers> getMediationUserIds() {
        return mediationUserIds;
    }

    public MediationUserRoleMappings mediationUserIds(Set<MediationUsers> mediationUsers) {
        this.mediationUserIds = mediationUsers;
        return this;
    }

    public MediationUserRoleMappings addMediationUserId(MediationUsers mediationUsers) {
        this.mediationUserIds.add(mediationUsers);
        mediationUsers.setMediationUserId(this);
        return this;
    }

    public MediationUserRoleMappings removeMediationUserId(MediationUsers mediationUsers) {
        this.mediationUserIds.remove(mediationUsers);
        mediationUsers.setMediationUserId(null);
        return this;
    }

    public void setMediationUserIds(Set<MediationUsers> mediationUsers) {
        this.mediationUserIds = mediationUsers;
    }

    public Set<MediationRoles> getMediationRoleIds() {
        return mediationRoleIds;
    }

    public MediationUserRoleMappings mediationRoleIds(Set<MediationRoles> mediationRoles) {
        this.mediationRoleIds = mediationRoles;
        return this;
    }

    public MediationUserRoleMappings addMediationRoleId(MediationRoles mediationRoles) {
        this.mediationRoleIds.add(mediationRoles);
        mediationRoles.setMediationRoleId(this);
        return this;
    }

    public MediationUserRoleMappings removeMediationRoleId(MediationRoles mediationRoles) {
        this.mediationRoleIds.remove(mediationRoles);
        mediationRoles.setMediationRoleId(null);
        return this;
    }

    public void setMediationRoleIds(Set<MediationRoles> mediationRoles) {
        this.mediationRoleIds = mediationRoles;
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
