package our.neighbor.app.domain.admin;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AdminRole {

    SUPER("슈퍼 관리자"), NORMAL("일반 관리자");

    private final String description;
}
