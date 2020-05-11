package com.dav.optimal.mediation.console.service.mapper;


import com.dav.optimal.mediation.console.domain.*;
import com.dav.optimal.mediation.console.service.dto.MediationUserRoleMappingsDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MediationUserRoleMappings} and its DTO {@link MediationUserRoleMappingsDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MediationUserRoleMappingsMapper extends EntityMapper<MediationUserRoleMappingsDTO, MediationUserRoleMappings> {


    @Mapping(target = "mediationUserIds", ignore = true)
    @Mapping(target = "removeMediationUserId", ignore = true)
    @Mapping(target = "mediationRoleIds", ignore = true)
    @Mapping(target = "removeMediationRoleId", ignore = true)
    MediationUserRoleMappings toEntity(MediationUserRoleMappingsDTO mediationUserRoleMappingsDTO);

    default MediationUserRoleMappings fromId(Long id) {
        if (id == null) {
            return null;
        }
        MediationUserRoleMappings mediationUserRoleMappings = new MediationUserRoleMappings();
        mediationUserRoleMappings.setId(id);
        return mediationUserRoleMappings;
    }
}
