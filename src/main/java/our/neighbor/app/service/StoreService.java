package our.neighbor.app.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import our.neighbor.app.domain.store.dto.StoreSearchRequest;
import our.neighbor.app.domain.store.dto.StoreSearchResponse;
import our.neighbor.app.repository.store.StoreRepository;

import java.util.List;

import static our.neighbor.app.core.util.distance.DistanceUtil.*;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional(transactionManager = "appTransactionManager", readOnly = true)
public class StoreService {

    private final StoreRepository storeRepository;

    public List<StoreSearchResponse> getStoresByKeyword(StoreSearchRequest.Keyword request) {
        return storeRepository.getStoresByKeyword(
                request.getKeyword(),
                request.getDist(),
                getPolygon(request.getMyLat(), request.getMyLon(), request.getDist()));
    }


}
