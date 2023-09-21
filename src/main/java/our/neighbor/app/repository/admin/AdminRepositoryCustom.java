package our.neighbor.app.repository.admin;

import our.neighbor.app.domain.admin.dto.AdminDTO;

public interface AdminRepositoryCustom {

    AdminDTO.Detail getAdminDetailByAdminId(Long id);
}
