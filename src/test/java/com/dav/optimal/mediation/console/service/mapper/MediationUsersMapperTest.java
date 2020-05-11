package com.dav.optimal.mediation.console.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class MediationUsersMapperTest {

    private MediationUsersMapper mediationUsersMapper;

    @BeforeEach
    public void setUp() {
        mediationUsersMapper = new MediationUsersMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(mediationUsersMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(mediationUsersMapper.fromId(null)).isNull();
    }
}
