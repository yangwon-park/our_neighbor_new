package our.neighbor.app.domain.admin;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import our.neighbor.app.domain.admin.dto.AdminDTO;
import our.neighbor.app.domain.common.BaseTimeEntity;

import java.util.Optional;

import static our.neighbor.app.core.util.CommonUtils.getIfNotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicUpdate
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

    public void updateAdminInfo(AdminDTO.Update request) {
        this.name = getIfNotNull(this.name, request.getName());
        this.phoneNumber = getIfNotNull(this.phoneNumber, request.getPhoneNumber());
        this.role = getIfNotNull(this.role, request.getRole());
    }
}
