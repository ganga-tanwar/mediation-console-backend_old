package com.dav.optimal.mediation.console.service.dto;

import io.swagger.annotations.ApiModel;
import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the {@link com.dav.optimal.mediation.console.domain.MediationUserRoleMappings} entity.
 */
@ApiModel(description = "Class representing Roles")
public class MediationUserRoleMappingsDTO implements Serializable {
    
    private Long id;

    @NotNull
    private UUID mediationUserId;

    @NotNull
    private UUID mediationRoleId;

    @NotNull
    @Size(max = 100)
    private String createdBy;

    @NotNull
    private Instant createdDate;

    @Size(max = 100)
    private String modifiedBy;

    private Instant modifiedDate;


    private Long mediationUserIdId;

    private Long mediationRoleIdId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UUID getMediationUserId() {
        return mediationUserId;
    }

    public void setMediationUserId(UUID mediationUserId) {
        this.mediationUserId = mediationUserId;
    }

    public UUID getMediationRoleId() {
        return mediationRoleId;
    }

    public void setMediationRoleId(UUID mediationRoleId) {
        this.mediationRoleId = mediationRoleId;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Instant getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Instant modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Long getMediationUserIdId() {
        return mediationUserIdId;
    }

    public void setMediationUserIdId(Long mediationUsersId) {
        this.mediationUserIdId = mediationUsersId;
    }

    public Long getMediationRoleIdId() {
        return mediationRoleIdId;
    }

    public void setMediationRoleIdId(Long mediationRolesId) {
        this.mediationRoleIdId = mediationRolesId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MediationUserRoleMappingsDTO mediationUserRoleMappingsDTO = (MediationUserRoleMappingsDTO) o;
        if (mediationUserRoleMappingsDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mediationUserRoleMappingsDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MediationUserRoleMappingsDTO{" +
            "id=" + getId() +
            ", mediationUserId='" + getMediationUserId() + "'" +
            ", mediationRoleId='" + getMediationRoleId() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", modifiedBy='" + getModifiedBy() + "'" +
            ", modifiedDate='" + getModifiedDate() + "'" +
            ", mediationUserIdId=" + getMediationUserIdId() +
            ", mediationRoleIdId=" + getMediationRoleIdId() +
            "}";
    }
}
