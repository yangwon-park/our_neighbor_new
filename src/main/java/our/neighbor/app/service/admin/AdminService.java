package our.neighbor.app.service.admin;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import our.neighbor.app.domain.admin.Admin;
import our.neighbor.app.domain.admin.dto.AdminDTO;
import our.neighbor.app.repository.admin.AdminRepository;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional(transactionManager = "appTransactionManager", readOnly = true)
public class AdminService {

    private final AdminRepository adminRepository;

    public AdminDTO.Detail getAdminDetail(Long adminId) {
        return adminRepository.getAdminDetailByAdminId(adminId);
    }

    @Transactional(transactionManager = "appTransactionManager")
    public Long joinAdmin(AdminDTO.Save request) {
        return adminRepository.save(request.toEntity()).getId();
    }

    @Transactional(transactionManager = "appTransactionManager")
    public Long updateAdminInfo(Long adminId, AdminDTO.Update request) {
        Admin admin = adminRepository.findById(adminId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 관리자입니다. ID :: {}" + adminId));

        admin.updateAdminInfo(request);

        return adminId;
    }
}
