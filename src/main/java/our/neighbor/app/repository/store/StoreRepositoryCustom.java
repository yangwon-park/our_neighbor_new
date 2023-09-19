package our.neighbor.app.repository.store;

import org.geolatte.geom.G2D;
import org.geolatte.geom.Polygon;
import our.neighbor.app.domain.store.dto.StoreSearchResponse;

import java.util.List;

public interface StoreRepositoryCustom {

    List<StoreSearchResponse> getStoresByKeyword(String keyword, Double dist, Polygon<G2D> polygon);
}
