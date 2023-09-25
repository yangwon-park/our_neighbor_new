package our.neighbor.app.domain.member;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "member_additional_info", schema = "neighbor")
public class MemberAdditionalInfo {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_additional_info_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private String name;

    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    private String gender;

    private Integer age;

    private MemberRole role;

    @Builder
    public MemberAdditionalInfo(Long id, Member member, String name,
                                String email, String phoneNumber,
                                String gender, Integer age, MemberRole role) {
        this.id = id;
        this.member = member;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.age = age;
        this.role = role;
    }
}
