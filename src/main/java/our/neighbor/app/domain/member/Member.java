package our.neighbor.app.domain.member;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.TypeAlias;
import our.neighbor.app.domain.common.BaseTimeEntity;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "member", schema = "neighbor")
public class Member extends BaseTimeEntity {


    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(name = "sns_id")
    private String snsId;

    @Column(name = "join_route")
    private String jointRoute;

    private String nickname;

    @Column(name = "last_login_time")
    private LocalDateTime lastLoginTime;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "member")
    private MemberAdditionalInfo memberAdditionalInfo;
}
