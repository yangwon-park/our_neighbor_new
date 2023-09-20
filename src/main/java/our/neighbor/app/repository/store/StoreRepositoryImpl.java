package our.neighbor.app.repository.store;

import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.querydsl.spatial.GeometryExpressions;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.geolatte.geom.G2D;
import org.geolatte.geom.Geometry;
import org.geolatte.geom.Polygon;
import our.neighbor.app.domain.store.dto.StoreSearchResponse;

import java.util.List;

import static org.springframework.util.StringUtils.hasText;
import static our.neighbor.app.domain.store.QStore.*;

@Slf4j
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
                .where(stContains(polygon), stDistance(polygon).loe(dist), keywordContains(keyword))
                .fetch();
    }

    private BooleanExpression stContains(Geometry<?> polygon) {
        return GeometryExpressions
                .asGeometry(polygon)
                .contains(store.point);
    }

    private NumberExpression<Double> stDistance(Geometry<G2D> polygon) {
        return GeometryExpressions
                .asGeometry(store.point)
                .distance(polygon);
    }

    private BooleanExpression keywordContains(String keyword) {
        if (!hasText(keyword)) {
            return null;
        }

        return store.name.contains(keyword);
    }
}
