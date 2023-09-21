package our.neighbor.app.domain.admin.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import our.neighbor.app.domain.admin.Admin;
import our.neighbor.app.domain.admin.AdminRole;

public class AdminDTO {

    @Getter
    @ToString
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Detail {

        private Long id;

        private String name;

        private String phoneNumber;

        private AdminRole role;

        @Builder
        public Detail(Long id, String name, String phoneNumber, AdminRole role) {
            this.id = id;
            this.name = name;
            this.phoneNumber = phoneNumber;
            this.role = role;
        }
    }


    @Getter
    @ToString
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Save {

        @NotBlank
        private String name;

        private String phoneNumber;

        @NotNull
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

    @Getter
    @ToString
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Update {

        @NotBlank
        private String name;

        private String phoneNumber;

        @NotNull
        private AdminRole role;

        @Builder
        public Update(String name, String phoneNumber, AdminRole role) {
            this.name = name;
            this.phoneNumber = phoneNumber;
            this.role = role;
        }
    }
}
