package com.dav.optimal.mediation.console.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.dav.optimal.mediation.console.web.rest.TestUtil;

public class MediationRolesTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MediationRoles.class);
        MediationRoles mediationRoles1 = new MediationRoles();
        mediationRoles1.setId(1L);
        MediationRoles mediationRoles2 = new MediationRoles();
        mediationRoles2.setId(mediationRoles1.getId());
        assertThat(mediationRoles1).isEqualTo(mediationRoles2);
        mediationRoles2.setId(2L);
        assertThat(mediationRoles1).isNotEqualTo(mediationRoles2);
        mediationRoles1.setId(null);
        assertThat(mediationRoles1).isNotEqualTo(mediationRoles2);
    }
}
