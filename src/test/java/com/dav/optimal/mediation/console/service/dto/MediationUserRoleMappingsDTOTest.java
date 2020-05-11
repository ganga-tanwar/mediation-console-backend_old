package com.dav.optimal.mediation.console.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.dav.optimal.mediation.console.web.rest.TestUtil;

public class MediationUserRoleMappingsDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MediationUserRoleMappingsDTO.class);
        MediationUserRoleMappingsDTO mediationUserRoleMappingsDTO1 = new MediationUserRoleMappingsDTO();
        mediationUserRoleMappingsDTO1.setId(1L);
        MediationUserRoleMappingsDTO mediationUserRoleMappingsDTO2 = new MediationUserRoleMappingsDTO();
        assertThat(mediationUserRoleMappingsDTO1).isNotEqualTo(mediationUserRoleMappingsDTO2);
        mediationUserRoleMappingsDTO2.setId(mediationUserRoleMappingsDTO1.getId());
        assertThat(mediationUserRoleMappingsDTO1).isEqualTo(mediationUserRoleMappingsDTO2);
        mediationUserRoleMappingsDTO2.setId(2L);
        assertThat(mediationUserRoleMappingsDTO1).isNotEqualTo(mediationUserRoleMappingsDTO2);
        mediationUserRoleMappingsDTO1.setId(null);
        assertThat(mediationUserRoleMappingsDTO1).isNotEqualTo(mediationUserRoleMappingsDTO2);
    }
}
