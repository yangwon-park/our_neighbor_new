package our.neighbor.app.service.admin;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import our.neighbor.app.domain.admin.dto.AdminDTO;
import our.neighbor.app.repository.admin.AdminRepository;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional(transactionManager = "appTransactionManager", readOnly = true)
public class AdminService {

    private final AdminRepository adminRepository;

    @Transactional(transactionManager = "appTransactionManager")
    public Long joinNewAdmin(AdminDTO.Save request) {
        return adminRepository.save(request.toEntity()).getId();
    }
}
