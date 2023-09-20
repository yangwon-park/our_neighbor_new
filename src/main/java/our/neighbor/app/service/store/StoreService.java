package our.neighbor.app.service.store;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.geolatte.geom.G2D;
import org.geolatte.geom.Point;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import our.neighbor.app.domain.store.Store;
import our.neighbor.app.domain.store.dto.StoreDTO;
import our.neighbor.app.repository.store.StoreRepository;

import static org.geolatte.geom.builder.DSL.g;
import static org.geolatte.geom.builder.DSL.point;
import static org.geolatte.geom.crs.CoordinateReferenceSystems.WGS84;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional(transactionManager = "appTransactionManager", readOnly = true)
public class StoreService {

    private final StoreRepository storeRepository;

    public Long save(StoreDTO.Save request) {
        Store store = request.toEntity();

        Point<G2D> point = point(WGS84, g(request.getLon(), request.getLat()));

        store.addPoint(point);

        storeRepository.save(store);

        return store.getId();
    }
}
