package com.dav.optimal.mediation.console.service.mapper;


import com.dav.optimal.mediation.console.domain.*;
import com.dav.optimal.mediation.console.service.dto.MediationUsersDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MediationUsers} and its DTO {@link MediationUsersDTO}.
 */
@Mapper(componentModel = "spring", uses = {MediationUserRoleMappingsMapper.class})
public interface MediationUsersMapper extends EntityMapper<MediationUsersDTO, MediationUsers> {

    @Mapping(source = "mediationUserId.id", target = "mediationUserIdId")
    MediationUsersDTO toDto(MediationUsers mediationUsers);

    @Mapping(source = "mediationUserIdId", target = "mediationUserId")
    MediationUsers toEntity(MediationUsersDTO mediationUsersDTO);

    default MediationUsers fromId(Long id) {
        if (id == null) {
            return null;
        }
        MediationUsers mediationUsers = new MediationUsers();
        mediationUsers.setId(id);
        return mediationUsers;
    }
}
