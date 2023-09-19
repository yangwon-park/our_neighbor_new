package our.neighbor.app.domain.store;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ParkAvailable {

    YES("지원됨"), NO("지원 안 됨"),
    NO_PARKING_SPACE("주차 공간 없음");

    private final String description;
}
