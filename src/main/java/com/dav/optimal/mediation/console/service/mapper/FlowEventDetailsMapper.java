package com.dav.optimal.mediation.console.service.mapper;


import com.dav.optimal.mediation.console.domain.*;
import com.dav.optimal.mediation.console.service.dto.FlowEventDetailsDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link FlowEventDetails} and its DTO {@link FlowEventDetailsDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface FlowEventDetailsMapper extends EntityMapper<FlowEventDetailsDTO, FlowEventDetails> {


    @Mapping(target = "flowIds", ignore = true)
    @Mapping(target = "removeFlowId", ignore = true)
    FlowEventDetails toEntity(FlowEventDetailsDTO flowEventDetailsDTO);

    default FlowEventDetails fromId(Long id) {
        if (id == null) {
            return null;
        }
        FlowEventDetails flowEventDetails = new FlowEventDetails();
        flowEventDetails.setId(id);
        return flowEventDetails;
    }
}
