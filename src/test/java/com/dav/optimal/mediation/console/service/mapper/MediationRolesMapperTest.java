package com.dav.optimal.mediation.console.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class MediationRolesMapperTest {

    private MediationRolesMapper mediationRolesMapper;

    @BeforeEach
    public void setUp() {
        mediationRolesMapper = new MediationRolesMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(mediationRolesMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(mediationRolesMapper.fromId(null)).isNull();
    }
}
