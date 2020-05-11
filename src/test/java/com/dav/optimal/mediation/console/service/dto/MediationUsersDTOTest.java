package com.dav.optimal.mediation.console.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.dav.optimal.mediation.console.web.rest.TestUtil;

public class MediationUsersDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MediationUsersDTO.class);
        MediationUsersDTO mediationUsersDTO1 = new MediationUsersDTO();
        mediationUsersDTO1.setId(1L);
        MediationUsersDTO mediationUsersDTO2 = new MediationUsersDTO();
        assertThat(mediationUsersDTO1).isNotEqualTo(mediationUsersDTO2);
        mediationUsersDTO2.setId(mediationUsersDTO1.getId());
        assertThat(mediationUsersDTO1).isEqualTo(mediationUsersDTO2);
        mediationUsersDTO2.setId(2L);
        assertThat(mediationUsersDTO1).isNotEqualTo(mediationUsersDTO2);
        mediationUsersDTO1.setId(null);
        assertThat(mediationUsersDTO1).isNotEqualTo(mediationUsersDTO2);
    }
}
