package com.dav.optimal.mediation.console.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * Class representing Roles
 */
@Entity
@Table(name = "mediation_roles")
public class MediationRoles implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "mediation_role_id", nullable = false)
    private UUID mediationRoleId;

    @NotNull
    @Size(max = 100)
    @Column(name = "role_name", length = 100, nullable = false)
    private String roleName;

    @Size(max = 100)
    @Column(name = "role_description", length = 100)
    private String roleDescription;

    @ManyToOne
    @JsonIgnoreProperties("mediationRoleIds")
    private MediationUserRoleMappings mediationRoleId;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UUID getMediationRoleId() {
        return mediationRoleId;
    }

    public MediationRoles mediationRoleId(UUID mediationRoleId) {
        this.mediationRoleId = mediationRoleId;
        return this;
    }

    public void setMediationRoleId(UUID mediationRoleId) {
        this.mediationRoleId = mediationRoleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public MediationRoles roleName(String roleName) {
        this.roleName = roleName;
        return this;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDescription() {
        return roleDescription;
    }

    public MediationRoles roleDescription(String roleDescription) {
        this.roleDescription = roleDescription;
        return this;
    }

    public void setRoleDescription(String roleDescription) {
        this.roleDescription = roleDescription;
    }

    public MediationUserRoleMappings getMediationRoleId() {
        return mediationRoleId;
    }

    public MediationRoles mediationRoleId(MediationUserRoleMappings mediationUserRoleMappings) {
        this.mediationRoleId = mediationUserRoleMappings;
        return this;
    }

    public void setMediationRoleId(MediationUserRoleMappings mediationUserRoleMappings) {
        this.mediationRoleId = mediationUserRoleMappings;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MediationRoles)) {
            return false;
        }
        return id != null && id.equals(((MediationRoles) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MediationRoles{" +
            "id=" + getId() +
            ", mediationRoleId='" + getMediationRoleId() + "'" +
            ", roleName='" + getRoleName() + "'" +
            ", roleDescription='" + getRoleDescription() + "'" +
            "}";
    }
}
