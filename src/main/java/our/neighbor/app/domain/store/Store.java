package our.neighbor.app.domain.store;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.geolatte.geom.G2D;
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

    @Column(columnDefinition = "Point")
    private Point<G2D> point;

    @Column(name = "phone_number")
    private String phoneNumber;

    private String notice;

    private String intro;

    @Column(name = "rating_total")
    private Integer ratingTotal;

    private String homepage;

    @Enumerated(EnumType.STRING)
    private StoreStatus status = StoreStatus.OPEN;               // 가게 오픈 상황 (default: OPEN)

    @Enumerated(EnumType.STRING)
    private ParkAvailable park;

    private String parkDetail;


    /*
        임베디드 타입
     */
    @Embedded
    private Address address;

    @Embedded
    private BusinessTime businessTime;
}
