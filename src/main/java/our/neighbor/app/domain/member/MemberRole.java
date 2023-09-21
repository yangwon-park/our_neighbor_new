package our.neighbor.app.domain.member;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MemberRole {
    
    OWNER("사업자"), NORMAL("일반 회원");
    
    private final String description;
}
