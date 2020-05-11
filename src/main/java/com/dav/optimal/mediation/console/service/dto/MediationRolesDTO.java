package com.dav.optimal.mediation.console.service.dto;

import io.swagger.annotations.ApiModel;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the {@link com.dav.optimal.mediation.console.domain.MediationRoles} entity.
 */
@ApiModel(description = "Class representing Roles")
public class MediationRolesDTO implements Serializable {
    
    private Long id;

    @NotNull
    private UUID mediationRoleId;

    @NotNull
    @Size(max = 100)
    private String roleName;

    @Size(max = 100)
    private String roleDescription;


    private Long mediationRoleIdId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UUID getMediationRoleId() {
        return mediationRoleId;
    }

    public void setMediationRoleId(UUID mediationRoleId) {
        this.mediationRoleId = mediationRoleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDescription() {
        return roleDescription;
    }

    public void setRoleDescription(String roleDescription) {
        this.roleDescription = roleDescription;
    }

    public Long getMediationRoleIdId() {
        return mediationRoleIdId;
    }

    public void setMediationRoleIdId(Long mediationUserRoleMappingsId) {
        this.mediationRoleIdId = mediationUserRoleMappingsId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MediationRolesDTO mediationRolesDTO = (MediationRolesDTO) o;
        if (mediationRolesDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mediationRolesDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MediationRolesDTO{" +
            "id=" + getId() +
            ", mediationRoleId='" + getMediationRoleId() + "'" +
            ", roleName='" + getRoleName() + "'" +
            ", roleDescription='" + getRoleDescription() + "'" +
            ", mediationRoleIdId=" + getMediationRoleIdId() +
            "}";
    }
}
