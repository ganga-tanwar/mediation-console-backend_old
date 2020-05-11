package com.dav.optimal.mediation.console.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

/**
 * Class representing Users
 */
@Entity
@Table(name = "mediation_users")
public class MediationUsers implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "mediation_userid", nullable = false)
    private UUID mediationUserid;

    @NotNull
    @Size(max = 100)
    @Column(name = "user_name", length = 100, nullable = false)
    private String userName;

    @NotNull
    @Size(max = 100)
    @Column(name = "password", length = 100, nullable = false)
    private String password;

    @NotNull
    @Size(max = 100)
    @Column(name = "email", length = 100, nullable = false)
    private String email;

    @Column(name = "contact_no", precision = 21, scale = 2)
    private BigDecimal contactNo;

    @Column(name = "active")
    private Boolean active;

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

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UUID getMediationUserid() {
        return mediationUserid;
    }

    public MediationUsers mediationUserid(UUID mediationUserid) {
        this.mediationUserid = mediationUserid;
        return this;
    }

    public void setMediationUserid(UUID mediationUserid) {
        this.mediationUserid = mediationUserid;
    }

    public String getUserName() {
        return userName;
    }

    public MediationUsers userName(String userName) {
        this.userName = userName;
        return this;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public MediationUsers password(String password) {
        this.password = password;
        return this;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public MediationUsers email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public BigDecimal getContactNo() {
        return contactNo;
    }

    public MediationUsers contactNo(BigDecimal contactNo) {
        this.contactNo = contactNo;
        return this;
    }

    public void setContactNo(BigDecimal contactNo) {
        this.contactNo = contactNo;
    }

    public Boolean isActive() {
        return active;
    }

    public MediationUsers active(Boolean active) {
        this.active = active;
        return this;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public MediationUsers createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public MediationUsers createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public MediationUsers modifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
        return this;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Instant getModifiedDate() {
        return modifiedDate;
    }

    public MediationUsers modifiedDate(Instant modifiedDate) {
        this.modifiedDate = modifiedDate;
        return this;
    }

    public void setModifiedDate(Instant modifiedDate) {
        this.modifiedDate = modifiedDate;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MediationUsers)) {
            return false;
        }
        return id != null && id.equals(((MediationUsers) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MediationUsers{" +
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
            "}";
    }
}
