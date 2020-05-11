package com.dav.optimal.mediation.console.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.dav.optimal.mediation.console.web.rest.TestUtil;

public class MediationUsersTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MediationUsers.class);
        MediationUsers mediationUsers1 = new MediationUsers();
        mediationUsers1.setId(1L);
        MediationUsers mediationUsers2 = new MediationUsers();
        mediationUsers2.setId(mediationUsers1.getId());
        assertThat(mediationUsers1).isEqualTo(mediationUsers2);
        mediationUsers2.setId(2L);
        assertThat(mediationUsers1).isNotEqualTo(mediationUsers2);
        mediationUsers1.setId(null);
        assertThat(mediationUsers1).isNotEqualTo(mediationUsers2);
    }
}
