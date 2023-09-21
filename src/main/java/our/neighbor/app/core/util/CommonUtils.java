package our.neighbor.app.core.util;

import java.util.Optional;

public class CommonUtils {

    private CommonUtils() {
        // 유틸리티 클래스: 인스턴스화를 방지하기 위해 private 생성자를 가짐
    }

    /**
     * newValue != null 경우 newValue룰 반환
     * newValue == null 경우 currentValue를 그대로 반환
     * 
     * @param <T> Generic
     * @param currentValue: 기존 값
     * @param newValue: 새로 비교할 값
     * @return newValue != null ? newValue : currentValue
     */
    public static <T> T getIfNotNull(T currentValue, T newValue) {
        return Optional.ofNullable(newValue).orElse(currentValue);
    }
}
