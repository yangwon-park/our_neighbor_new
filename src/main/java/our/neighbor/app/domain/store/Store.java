package our.neighbor.app.domain.store;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.geolatte.geom.G2D;
import org.geolatte.geom.Geometry;
import org.geolatte.geom.Point;
import our.neighbor.app.domain.embedded.Address;
import our.neighbor.app.domain.embedded.BusinessTime;

@Slf4j
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "store", schema = "neighbor")
public class Store {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_id")
    private Long id;

    private String name;

    private Double lat;

    private Double lon;

    @Column(columnDefinition = "geometry(Point,4326)")
    private Point<G2D> point;

    @Column(name = "phone_number")
    private String phoneNumber;

    private String notice;

    private String intro;

    @Column(name = "rating_total", columnDefinition = "integer default 0")
    private Integer ratingTotal;

    private String homepage;

    @Column(columnDefinition = "varchar(20) default OPEN")
    @Enumerated(EnumType.STRING)
    private StoreStatus status;

    @Enumerated(EnumType.STRING)
    private ParkAvailable park;

    private String parkDetail;

    @Embedded
    private Address address;

    @Embedded
    private BusinessTime businessTime;

    public void addPoint(Point<G2D> point) {
        this.point = point;
    }

    @Builder
    public Store(Long id, String name, Double lat, Double lon, Point<G2D> point,
                 String phoneNumber, String notice, String intro, Integer ratingTotal,
                 String homepage, StoreStatus status, ParkAvailable park, String parkDetail,
                 Address address, BusinessTime businessTime) {
        this.id = id;
        this.name = name;
        this.lat = lat;
        this.lon = lon;
        this.point = point;
        this.phoneNumber = phoneNumber;
        this.notice = notice;
        this.intro = intro;
        this.ratingTotal = ratingTotal;
        this.homepage = homepage;
        this.status = status;
        this.park = park;
        this.parkDetail = parkDetail;
        this.address = address;
        this.businessTime = businessTime;
    }
}
