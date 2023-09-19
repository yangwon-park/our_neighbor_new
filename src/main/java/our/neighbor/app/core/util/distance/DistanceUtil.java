package our.neighbor.app.core.util.distance;

import org.geolatte.geom.G2D;
import org.geolatte.geom.Polygon;
import our.neighbor.app.domain.store.dto.StoreSearchResponse;

import java.util.List;

import static org.geolatte.geom.builder.DSL.*;
import static org.geolatte.geom.builder.DSL.g;
import static org.geolatte.geom.crs.CoordinateReferenceSystems.WGS84;

public class DistanceUtil {

    public static Polygon<G2D> getPolygon(double lat, double lon, double dist) {
        double sqrt = Math.sqrt(2);
        double toCorner = dist * sqrt;          // 빗변의 길이 (직각 이등변 삼각형의 성질)

        Location northEast = calculatePoint(lat, lon, toCorner, Direction.NORTHEAST.getAngle());
        Location northWest = calculatePoint(lat, lon, toCorner, Direction.NORTHWEST.getAngle());
        Location southWest = calculatePoint(lat, lon, toCorner, Direction.SOUTHWEST.getAngle());
        Location southEast = calculatePoint(lat, lon, toCorner, Direction.SOUTHEAST.getAngle());

        double nex = northEast.getLon();
        double ney = northEast.getLat();

        double nwx = northWest.getLon();
        double nwy = northWest.getLat();

        double swx = southWest.getLon();
        double swy = southWest.getLat();

        double sex = southEast.getLon();
        double sey = southEast.getLat();

        return polygon(WGS84, ring(g(nex, ney),
                g(nwx, nwy), g(swx, swy), g(sex, sey), g(nex, ney)));
    }

    public static void calculateHowFarToTheTarget(double myLat, double myLon,
                                                  List<StoreSearchResponse> findDTO) {
        findDTO.forEach(dto -> {
            double dist = DistanceUtil.calculateDistByHaversine(dto.getLat(), dto.getLon(),
                    myLat, myLon);

            double refineDist = Math.ceil(dist * 10) / 10.0;

            dto.setDistance(refineDist);
        });
    }

    public static double calculateDistByHaversine(double lat1, double lon1, double lat2, double lon2) {
        double distance;
        double radius = 6371;                   // 지구 반지름
        double toRadian = Math.PI / 180;        // pi * 1 라디안 = 180도

        double deltaLatitude = Math.abs(lat1 - lat2) * toRadian;
        double deltaLongitude = Math.abs(lon1 - lon2) * toRadian;

        double sinDeltaLat = Math.sin(deltaLatitude / 2);
        double sinDeltaLng = Math.sin(deltaLongitude / 2);
        double squareRoot = Math.sqrt(
                sinDeltaLat * sinDeltaLat +
                        Math.cos(lat1 * toRadian) * Math.cos(lat2 * toRadian) * sinDeltaLng * sinDeltaLng
        );

        distance = 2 * radius * Math.asin(squareRoot);

        return distance;
    }

    public static Location calculatePoint(double currentLat, double currentLon,
                                          double distance, double angle) {
        double radianLat = toRadian(currentLat);
        double radianLon = toRadian(currentLon);
        double radianAngle = toRadian(angle);
        double distanceRadius = distance / 6371.01;

        double lat = Math.asin(sin(radianLat) * cos(distanceRadius) +
                cos(radianLat) * sin(distanceRadius) * cos(radianAngle));

        double lon = radianLon + Math.atan2(sin(radianAngle) * sin(distanceRadius) *
                cos(radianLat), cos(distanceRadius) - sin(radianLat) * sin(lat));

        lon = normalizeLongitude(lon);

        return new Location(toDegree(lat), toDegree(lon));
    }

    private static double toRadian(double coordinate) {
        return coordinate * Math.PI / 180.0;
    }

    private static double toDegree(double coordinate) {
        return coordinate * 180.0 / Math.PI;
    }

    private static double sin(double coordinate) {
        return Math.sin(coordinate);
    }

    private static double cos(double coordinate) {
        return Math.cos(coordinate);
    }

    private static double normalizeLongitude(double longitude) {
        return (longitude + 540) % 360 - 180;
    }
}
