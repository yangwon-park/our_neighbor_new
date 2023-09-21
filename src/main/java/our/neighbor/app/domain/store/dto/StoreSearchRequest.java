package our.neighbor.app.domain.store.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;


public class StoreSearchRequest {

    @Getter
    @ToString
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Keyword {

        @NotBlank
        private String keyword;

        @NotNull
        private Double myLat;

        @NotNull
        private Double myLon;

        @NotNull
        private Double dist;

        @Builder
        public Keyword(String keyword, Double myLat, Double myLon, Double dist) {
            this.keyword = keyword;
            this.myLat = myLat;
            this.myLon = myLon;
            this.dist = dist;
        }
    }

}
