package our.neighbor.app.repository.admin;

import org.springframework.data.jpa.repository.JpaRepository;
import our.neighbor.app.domain.admin.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long> {
}
