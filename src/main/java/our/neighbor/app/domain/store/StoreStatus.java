package our.neighbor.app.domain.store;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum StoreStatus {

    OPEN("영업중"), CLOSED("영업 종료"), BREAK("쉬는 시간");

    private final String description;
}
