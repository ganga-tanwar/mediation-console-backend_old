package com.dav.optimal.mediation.console.service.mapper;


import com.dav.optimal.mediation.console.domain.*;
import com.dav.optimal.mediation.console.service.dto.MediationUserRoleMappingsDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MediationUserRoleMappings} and its DTO {@link MediationUserRoleMappingsDTO}.
 */
@Mapper(componentModel = "spring", uses = {MediationUsersMapper.class, MediationRolesMapper.class})
public interface MediationUserRoleMappingsMapper extends EntityMapper<MediationUserRoleMappingsDTO, MediationUserRoleMappings> {

    @Mapping(source = "mediationUserId.id", target = "mediationUserIdId")
    @Mapping(source = "mediationRoleId.id", target = "mediationRoleIdId")
    MediationUserRoleMappingsDTO toDto(MediationUserRoleMappings mediationUserRoleMappings);

    @Mapping(source = "mediationUserIdId", target = "mediationUserId")
    @Mapping(source = "mediationRoleIdId", target = "mediationRoleId")
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
