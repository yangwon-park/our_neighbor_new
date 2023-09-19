package our.neighbor.app.domain.embedded;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class Address {

    @Column(name = "road_addr")
    private String roadAddr;

    @Column(name = "number_addr")
    private String numberAddr;

    private String zipcode;

    private String detail;

    public Address(String roadAddr, String numberAddr, String zipcode, String detail) {
        this.roadAddr = roadAddr;
        this.numberAddr = numberAddr;
        this.zipcode = zipcode;
        this.detail = detail;
    }
}
