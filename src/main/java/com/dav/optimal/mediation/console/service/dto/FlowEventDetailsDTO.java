package com.dav.optimal.mediation.console.service.dto;

import io.swagger.annotations.ApiModel;
import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the {@link com.dav.optimal.mediation.console.domain.FlowEventDetails} entity.
 */
@ApiModel(description = "Class representing flowEventDetails")
public class FlowEventDetailsDTO implements Serializable {
    
    private Long id;

    @NotNull
    private UUID eventId;

    @NotNull
    @Size(max = 100)
    private String eventName;

    @NotNull
    private UUID flowId;

    @NotNull
    private LocalDate transactionDate;

    @NotNull
    @Size(max = 100)
    private String parameters;

    @NotNull
    @Size(max = 100)
    private String status;

    @NotNull
    @Size(max = 100)
    private String errorCode;

    @NotNull
    @Size(max = 250)
    private String errorInfo;

    @NotNull
    private String errorCodeRetriable;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UUID getEventId() {
        return eventId;
    }

    public void setEventId(UUID eventId) {
        this.eventId = eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public UUID getFlowId() {
        return flowId;
    }

    public void setFlowId(UUID flowId) {
        this.flowId = flowId;
    }

    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getParameters() {
        return parameters;
    }

    public void setParameters(String parameters) {
        this.parameters = parameters;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorInfo() {
        return errorInfo;
    }

    public void setErrorInfo(String errorInfo) {
        this.errorInfo = errorInfo;
    }

    public String getErrorCodeRetriable() {
        return errorCodeRetriable;
    }

    public void setErrorCodeRetriable(String errorCodeRetriable) {
        this.errorCodeRetriable = errorCodeRetriable;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        FlowEventDetailsDTO flowEventDetailsDTO = (FlowEventDetailsDTO) o;
        if (flowEventDetailsDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), flowEventDetailsDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "FlowEventDetailsDTO{" +
            "id=" + getId() +
            ", eventId='" + getEventId() + "'" +
            ", eventName='" + getEventName() + "'" +
            ", flowId='" + getFlowId() + "'" +
            ", transactionDate='" + getTransactionDate() + "'" +
            ", parameters='" + getParameters() + "'" +
            ", status='" + getStatus() + "'" +
            ", errorCode='" + getErrorCode() + "'" +
            ", errorInfo='" + getErrorInfo() + "'" +
            ", errorCodeRetriable='" + getErrorCodeRetriable() + "'" +
            "}";
    }
}
