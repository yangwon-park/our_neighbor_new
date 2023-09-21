package our.neighbor.unit.service;

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

import java.util.Optional;

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

    Admin admin;

    AdminDTO.Detail detailResponse;

    AdminDTO.Save saveRequest;

    AdminDTO.Update updateRequest;

    @BeforeEach
    void setUp() {
        admin = Admin
                .builder()
                .id(1L)
                .name("기존 관리자")
                .phoneNumber("010-1234-1234")
                .role(AdminRole.NORMAL)
                .build();

        detailResponse = AdminDTO.Detail
                .builder()
                .id(admin.getId())
                .name(admin.getName())
                .phoneNumber(admin.getPhoneNumber())
                .role(AdminRole.NORMAL)
                .build();

        saveRequest = AdminDTO.Save
                .builder()
                .name("슈퍼 관리자")
                .phoneNumber("010-0000-1111")
                .role(AdminRole.SUPER)
                .build();

        updateRequest = AdminDTO.Update
                .builder()
                .name("기존 관리자 업데이트")
                .phoneNumber("010-1234-1234")
                .role(AdminRole.SUPER)
                .build();
    }

    @Test
    @DisplayName("관리자 조회")
    void should_GetAdminDetailByAdminId() {
        given(adminRepository.getAdminDetailByAdminId(admin.getId())).willReturn(detailResponse);

        AdminDTO.Detail findAdminDetail = adminService.getAdminDetail(admin.getId());

        then(adminRepository).should(times(1)).getAdminDetailByAdminId(admin.getId());

        assertThat(findAdminDetail.getName()).isEqualTo(detailResponse.getName());
    }

    @Test
    @DisplayName("새 관리자 가입")
    void should_JoinNewAdmin_ThenReturnAdminId() {
        Admin savedAdmin = saveRequest.toEntity();

        given(adminRepository.save(any(Admin.class))).willAnswer(invocation -> invocation.getArgument(0));

        Long id = adminService.joinAdmin(saveRequest);

        ArgumentCaptor<Admin> argumentCaptor = ArgumentCaptor.forClass(Admin.class);

        then(adminRepository).should(times(1)).save(argumentCaptor.capture());

        assertThat(id).isEqualTo(savedAdmin.getId());
    }

    @Test
    @DisplayName("관리자 정보 업데이트")
    void should_UpdateAdminInfo() {
        given(adminRepository.findById(admin.getId())).willReturn(Optional.ofNullable(admin));

        Long adminId = adminService.updateAdminInfo(admin.getId(), updateRequest);

        then(adminRepository).should(times(1)).findById(admin.getId());

        Admin updatedAdmin = adminRepository.findById(admin.getId()).get();

        assertThat(adminId).isEqualTo(admin.getId());
        assertThat(updatedAdmin.getName()).isEqualTo(updateRequest.getName());
        assertThat(updatedAdmin.getRole()).isEqualTo(updateRequest.getRole());
    }
}
