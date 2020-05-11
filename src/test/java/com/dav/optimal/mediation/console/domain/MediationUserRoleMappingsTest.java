package com.dav.optimal.mediation.console.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.dav.optimal.mediation.console.web.rest.TestUtil;

public class MediationUserRoleMappingsTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MediationUserRoleMappings.class);
        MediationUserRoleMappings mediationUserRoleMappings1 = new MediationUserRoleMappings();
        mediationUserRoleMappings1.setId(1L);
        MediationUserRoleMappings mediationUserRoleMappings2 = new MediationUserRoleMappings();
        mediationUserRoleMappings2.setId(mediationUserRoleMappings1.getId());
        assertThat(mediationUserRoleMappings1).isEqualTo(mediationUserRoleMappings2);
        mediationUserRoleMappings2.setId(2L);
        assertThat(mediationUserRoleMappings1).isNotEqualTo(mediationUserRoleMappings2);
        mediationUserRoleMappings1.setId(null);
        assertThat(mediationUserRoleMappings1).isNotEqualTo(mediationUserRoleMappings2);
    }
}
