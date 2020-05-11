package com.dav.optimal.mediation.console.service.mapper;


import com.dav.optimal.mediation.console.domain.*;
import com.dav.optimal.mediation.console.service.dto.MediationRolesDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MediationRoles} and its DTO {@link MediationRolesDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MediationRolesMapper extends EntityMapper<MediationRolesDTO, MediationRoles> {



    default MediationRoles fromId(Long id) {
        if (id == null) {
            return null;
        }
        MediationRoles mediationRoles = new MediationRoles();
        mediationRoles.setId(id);
        return mediationRoles;
    }
}
