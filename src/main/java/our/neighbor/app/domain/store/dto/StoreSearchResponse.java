package our.neighbor.app.domain.store.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import our.neighbor.app.domain.embedded.Address;
import our.neighbor.app.domain.embedded.BusinessTime;
import our.neighbor.app.domain.store.StoreStatus;

import java.util.List;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StoreSearchResponse {

    private Long storeId;

    private String name;

    private Double lon;

    private Double lat;

    private String phoneNumber;

    private StoreStatus status;

    private BusinessTime businessTime;

    private Address address;

    private int average;

    private String uploadImgUrl;

    private Double distance;

    private List<String> offDays;

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public StoreSearchResponse(Long storeId, String name, Double lon, Double lat,
                               String phoneNumber, StoreStatus status,
                               BusinessTime businessTime, Address address, int average) {
        this.storeId = storeId;
        this.name = name;
        this.lon = lon;
        this.lat = lat;
        this.phoneNumber = phoneNumber;
        this.status = status;
        this.businessTime = businessTime;
        this.address = address;
        this.average = average;
    }

    public StoreSearchResponse(Long storeId, String name, Double lon, Double lat, String phoneNumber,
                               StoreStatus status, BusinessTime businessTime, Address address, int average,
                               String uploadImgUrl, Double distance, List<String> offDays) {
        this.storeId = storeId;
        this.name = name;
        this.lon = lon;
        this.lat = lat;
        this.phoneNumber = phoneNumber;
        this.status = status;
        this.businessTime = businessTime;
        this.address = address;
        this.average = average;
        this.uploadImgUrl = uploadImgUrl;
        this.distance = distance;
        this.offDays = offDays;
    }
}
