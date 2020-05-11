package com.dav.optimal.mediation.console.service.dto;

import io.swagger.annotations.ApiModel;
import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the {@link com.dav.optimal.mediation.console.domain.MediationUsers} entity.
 */
@ApiModel(description = "Class representing Users")
public class MediationUsersDTO implements Serializable {
    
    private Long id;

    @NotNull
    private UUID mediationUserid;

    @NotNull
    @Size(max = 100)
    private String userName;

    @NotNull
    @Size(max = 100)
    private String password;

    @NotNull
    @Size(max = 100)
    private String email;

    private BigDecimal contactNo;

    private Boolean active;

    @NotNull
    @Size(max = 100)
    private String createdBy;

    @NotNull
    private LocalDate createdDate;

    @Size(max = 100)
    private String modifiedBy;

    private LocalDate modifiedDate;


    private Long mediationUserIdId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UUID getMediationUserid() {
        return mediationUserid;
    }

    public void setMediationUserid(UUID mediationUserid) {
        this.mediationUserid = mediationUserid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public BigDecimal getContactNo() {
        return contactNo;
    }

    public void setContactNo(BigDecimal contactNo) {
        this.contactNo = contactNo;
    }

    public Boolean isActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public LocalDate getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(LocalDate modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Long getMediationUserIdId() {
        return mediationUserIdId;
    }

    public void setMediationUserIdId(Long mediationUserRoleMappingsId) {
        this.mediationUserIdId = mediationUserRoleMappingsId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MediationUsersDTO mediationUsersDTO = (MediationUsersDTO) o;
        if (mediationUsersDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mediationUsersDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MediationUsersDTO{" +
            "id=" + getId() +
            ", mediationUserid='" + getMediationUserid() + "'" +
            ", userName='" + getUserName() + "'" +
            ", password='" + getPassword() + "'" +
            ", email='" + getEmail() + "'" +
            ", contactNo=" + getContactNo() +
            ", active='" + isActive() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", modifiedBy='" + getModifiedBy() + "'" +
            ", modifiedDate='" + getModifiedDate() + "'" +
            ", mediationUserIdId=" + getMediationUserIdId() +
            "}";
    }
}
