package com.dav.optimal.mediation.console.service.mapper;


import com.dav.optimal.mediation.console.domain.*;
import com.dav.optimal.mediation.console.service.dto.FlowDetailsDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link FlowDetails} and its DTO {@link FlowDetailsDTO}.
 */
@Mapper(componentModel = "spring", uses = {FlowEventDetailsMapper.class})
public interface FlowDetailsMapper extends EntityMapper<FlowDetailsDTO, FlowDetails> {

    @Mapping(source = "flowId.id", target = "flowIdId")
    FlowDetailsDTO toDto(FlowDetails flowDetails);

    @Mapping(source = "flowIdId", target = "flowId")
    FlowDetails toEntity(FlowDetailsDTO flowDetailsDTO);

    default FlowDetails fromId(Long id) {
        if (id == null) {
            return null;
        }
        FlowDetails flowDetails = new FlowDetails();
        flowDetails.setId(id);
        return flowDetails;
    }
}
