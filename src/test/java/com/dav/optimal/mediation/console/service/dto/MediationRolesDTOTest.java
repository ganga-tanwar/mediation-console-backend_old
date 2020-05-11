package com.dav.optimal.mediation.console.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.dav.optimal.mediation.console.web.rest.TestUtil;

public class MediationRolesDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MediationRolesDTO.class);
        MediationRolesDTO mediationRolesDTO1 = new MediationRolesDTO();
        mediationRolesDTO1.setId(1L);
        MediationRolesDTO mediationRolesDTO2 = new MediationRolesDTO();
        assertThat(mediationRolesDTO1).isNotEqualTo(mediationRolesDTO2);
        mediationRolesDTO2.setId(mediationRolesDTO1.getId());
        assertThat(mediationRolesDTO1).isEqualTo(mediationRolesDTO2);
        mediationRolesDTO2.setId(2L);
        assertThat(mediationRolesDTO1).isNotEqualTo(mediationRolesDTO2);
        mediationRolesDTO1.setId(null);
        assertThat(mediationRolesDTO1).isNotEqualTo(mediationRolesDTO2);
    }
}
