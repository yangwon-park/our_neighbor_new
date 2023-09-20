package our.neighbor.app.domain.store.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import our.neighbor.app.domain.embedded.Address;
import our.neighbor.app.domain.embedded.BusinessTime;
import our.neighbor.app.domain.store.ParkAvailable;
import our.neighbor.app.domain.store.Store;

import java.time.LocalTime;
import java.util.ArrayList;

public class StoreDTO {

    @Getter
    @ToString
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Save {

        @NotBlank
        private String name;

        @NotNull
        private Double lat;

        @NotNull
        private Double lon;

        private String phoneNumber;

        private String notice;

        private String intro;

        private ParkAvailable park;

        private String parkDetail;

        private String homepage;

        @NotBlank
        private String zipcode;

        @NotBlank
        private String roadAddr;

        @NotBlank
        private String numberAddr;

        private String detail;

        @NotNull
        @DateTimeFormat(pattern = "HH:mm")
        private LocalTime openingTime;

        @NotNull
        @DateTimeFormat(pattern = "HH:mm")
        private LocalTime closingTime;

        @DateTimeFormat(pattern = "HH:mm")
        private LocalTime breakStartTime;

        @DateTimeFormat(pattern = "HH:mm")
        private LocalTime breakEndTime;

        @DateTimeFormat(pattern = "HH:mm")
        private LocalTime lastOrderTime;

        @Builder
        public Save(String name, Double lat, Double lon, String phoneNumber, String notice,
                    String intro, ParkAvailable park, String parkDetail, String homepage,
                    String zipcode, String roadAddr, String numberAddr, String detail,
                    LocalTime openingTime, LocalTime closingTime, LocalTime breakStartTime,
                    LocalTime breakEndTime, LocalTime lastOrderTime) {
            this.name = name;
            this.lat = lat;
            this.lon = lon;
            this.phoneNumber = phoneNumber;
            this.notice = notice;
            this.intro = intro;
            this.park = park;
            this.parkDetail = parkDetail;
            this.homepage = homepage;
            this.zipcode = zipcode;
            this.roadAddr = roadAddr;
            this.numberAddr = numberAddr;
            this.detail = detail;
            this.openingTime = openingTime;
            this.closingTime = closingTime;
            this.breakStartTime = breakStartTime;
            this.breakEndTime = breakEndTime;
            this.lastOrderTime = lastOrderTime;
        }

        public Store toEntity() {
            return Store.builder()
                    .name(name)
                    .phoneNumber(phoneNumber)
                    .businessTime(new BusinessTime(openingTime, closingTime, breakStartTime, breakEndTime, lastOrderTime))
                    .lat(lat)
                    .lon(lon)
                    .homepage(homepage)
                    .notice(notice)
                    .intro(intro)
                    .park(park)
                    .parkDetail(parkDetail)
                    .address(new Address(roadAddr, numberAddr, zipcode, detail))
                    .build();
        }
    }

}
