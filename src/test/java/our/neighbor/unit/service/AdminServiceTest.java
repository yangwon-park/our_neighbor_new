package our.neighbor.unit.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;
import our.neighbor.app.domain.admin.Admin;
import our.neighbor.app.domain.admin.AdminRole;
import our.neighbor.app.domain.admin.dto.AdminDTO;
import our.neighbor.app.repository.admin.AdminRepository;
import our.neighbor.app.service.admin.AdminService;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class AdminServiceTest {

    @Mock
    AdminRepository adminRepository;

    @InjectMocks
    AdminService adminService;

    AdminDTO.Save saveRequest;

    @BeforeEach
    void setUp() {
        saveRequest = AdminDTO.Save
                .builder()
                .name("슈퍼 관리자")
                .phoneNumber("010-0000-1111")
                .role(AdminRole.SUPER)
                .build();

    }

    @Test
    @DisplayName("새 관리자 가입")
    void should_JoinNewAdmin_ThenReturnAdminId() {
        Admin savedAdmin = saveRequest.toEntity();

        given(adminRepository.save(any(Admin.class))).willAnswer(invocation -> invocation.getArgument(0));

        Long id = adminService.joinNewAdmin(saveRequest);

        ArgumentCaptor<Admin> argumentCaptor = ArgumentCaptor.forClass(Admin.class);

        then(adminRepository).should(times(1)).save(argumentCaptor.capture());

        assertThat(id).isEqualTo(savedAdmin.getId());
    }
}
