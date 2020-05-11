package com.dav.optimal.mediation.console.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class MediationUserRoleMappingsMapperTest {

    private MediationUserRoleMappingsMapper mediationUserRoleMappingsMapper;

    @BeforeEach
    public void setUp() {
        mediationUserRoleMappingsMapper = new MediationUserRoleMappingsMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(mediationUserRoleMappingsMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(mediationUserRoleMappingsMapper.fromId(null)).isNull();
    }
}
