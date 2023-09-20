package our.neighbor.app.domain.admin.dto;

import lombok.*;
import org.hibernate.result.UpdateCountOutput;
import our.neighbor.app.domain.admin.Admin;
import our.neighbor.app.domain.admin.AdminRole;

public class AdminDTO {

    @Getter
    @ToString
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Save {

        private String name;

        private String phoneNumber;

        private AdminRole role;

        @Builder
        public Save(String name, String phoneNumber, AdminRole role) {
            this.name = name;
            this.phoneNumber = phoneNumber;
            this.role = role;
        }

        public Admin toEntity() {
            return Admin
                    .builder()
                    .name(name)
                    .phoneNumber(phoneNumber)
                    .role(role)
                    .build();
        }
    }

}
