package our.neighbor.app.repository.admin;

import org.springframework.data.jpa.repository.JpaRepository;
import our.neighbor.app.domain.admin.Admin;
import our.neighbor.app.domain.admin.dto.AdminDTO;

public interface AdminRepository extends JpaRepository<Admin, Long>, AdminRepositoryCustom {

}
