package com.dav.optimal.mediation.console.service.mapper;


import com.dav.optimal.mediation.console.domain.*;
import com.dav.optimal.mediation.console.service.dto.MediationRolesDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MediationRoles} and its DTO {@link MediationRolesDTO}.
 */
@Mapper(componentModel = "spring", uses = {MediationUserRoleMappingsMapper.class})
public interface MediationRolesMapper extends EntityMapper<MediationRolesDTO, MediationRoles> {

    @Mapping(source = "mediationRoleId.id", target = "mediationRoleIdId")
    MediationRolesDTO toDto(MediationRoles mediationRoles);

    @Mapping(source = "mediationRoleIdId", target = "mediationRoleId")
    MediationRoles toEntity(MediationRolesDTO mediationRolesDTO);

    default MediationRoles fromId(Long id) {
        if (id == null) {
            return null;
        }
        MediationRoles mediationRoles = new MediationRoles();
        mediationRoles.setId(id);
        return mediationRoles;
    }
}
