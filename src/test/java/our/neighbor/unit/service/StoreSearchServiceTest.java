package our.neighbor.unit.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;
import our.neighbor.app.core.util.distance.DistanceUtil;
import our.neighbor.app.domain.store.dto.StoreDTO;
import our.neighbor.app.domain.store.dto.StoreSearchRequest;
import our.neighbor.app.domain.store.dto.StoreSearchResponse;
import our.neighbor.app.repository.store.StoreRepository;
import our.neighbor.app.service.store.StoreSearchService;
import our.neighbor.app.service.store.StoreService;

import java.util.List;

import static org.mockito.BDDMockito.given;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class StoreSearchServiceTest {


    @Mock
    StoreRepository storeRepository;

    @InjectMocks
    private StoreSearchService storeSearchService;

    private final double DIST_TO_TARGET = 3;

    private StoreSearchRequest.Keyword keywordRequest;

    @BeforeEach
    void setUp() {
        keywordRequest = StoreSearchRequest.Keyword
                .builder()
                .keyword("하이")
                .myLat(32.1234)
                .myLon(123.234)
                .build();
    }

    @Test
    @DisplayName("키워드로 거리 내에 있는 매장 조회")
    void should_GetStoresByKeyword_InMyPolygon() {
        given(storeRepository.getStoresByKeyword(
                keywordRequest.getKeyword(),
                keywordRequest.getDist(),
                DistanceUtil.getPolygon(keywordRequest.getMyLat(), keywordRequest.getMyLon(), keywordRequest.getDist())));

        List<StoreSearchResponse> result = storeSearchService.getStoresByKeyword(keywordRequest);
    }
}
