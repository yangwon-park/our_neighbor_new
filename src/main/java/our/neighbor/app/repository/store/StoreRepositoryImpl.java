package our.neighbor.app.repository.store;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.geolatte.geom.G2D;
import org.geolatte.geom.Polygon;
import org.springframework.beans.factory.annotation.Qualifier;
import our.neighbor.app.domain.store.dto.StoreSearchResponse;

import java.util.List;

import static our.neighbor.app.domain.store.QStore.*;

@RequiredArgsConstructor
public class StoreRepositoryImpl implements StoreRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<StoreSearchResponse> getStoresByKeyword(String keyword, Double dist, Polygon<G2D> polygon) {
        return queryFactory
                .select(
                        Projections.constructor(
                                StoreSearchResponse.class,
                                store.id,
                                store.name,
                                store.lat,
                                store.lon,
                                store.phoneNumber,
                                store.status,
                                store.businessTime,
                                store.address,
                                store.ratingTotal
                        ))
                .from(store)
                .fetch();
    }
}
