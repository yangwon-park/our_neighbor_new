package our.neighbor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import our.neighbor.domain.Store;

public interface StoreRepository extends JpaRepository<Store, Long> {
}
