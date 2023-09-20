package our.neighbor.app.domain.admin;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import our.neighbor.app.domain.common.BaseTimeEntity;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "admin", schema = "neighbor")
public class Admin extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "admin_id")
    private Long id;

    private String name;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private AdminRole role;

    @Builder
    public Admin(Long id, String name, String phoneNumber, AdminRole role) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.role = role;
    }
}
