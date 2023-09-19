package our.neighbor.app.repository.store;

import org.springframework.data.jpa.repository.JpaRepository;
import our.neighbor.app.domain.store.Store;

public interface StoreRepository extends JpaRepository<Store, Long>, StoreRepositoryCustom {

}
