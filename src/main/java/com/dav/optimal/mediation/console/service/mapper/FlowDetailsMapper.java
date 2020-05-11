package com.dav.optimal.mediation.console.service.mapper;


import com.dav.optimal.mediation.console.domain.*;
import com.dav.optimal.mediation.console.service.dto.FlowDetailsDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link FlowDetails} and its DTO {@link FlowDetailsDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface FlowDetailsMapper extends EntityMapper<FlowDetailsDTO, FlowDetails> {



    default FlowDetails fromId(Long id) {
        if (id == null) {
            return null;
        }
        FlowDetails flowDetails = new FlowDetails();
        flowDetails.setId(id);
        return flowDetails;
    }
}
