package our.neighbor.app.domain.store.dto;

import lombok.*;


public class StoreSearchRequest {

    @Getter
    @ToString
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Keyword {

        private String keyword;

        private Double myLat;

        private Double myLon;

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
