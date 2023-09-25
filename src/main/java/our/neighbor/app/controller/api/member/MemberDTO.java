package our.neighbor.app.controller.api.member;

import lombok.*;
import our.neighbor.app.domain.member.Member;
import our.neighbor.app.domain.member.MemberAdditionalInfo;
import our.neighbor.app.domain.member.MemberRole;

public class MemberDTO {

    @Getter
    @ToString
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Join {

        private String snsId;

        private String pushToken;

        private String joinRoute;

        private String nickname;

        private String name;

        private String email;

        private String phoneNumber;

        private String gender;

        private int age;

        private MemberRole role;

        private String duplicateInfo;

        @Builder
        public Join(String snsId, String pushToken, String joinRoute,
                    String name, String email, String phoneNumber,
                    String gender, int age, MemberRole role, String duplicateInfo) {
            this.snsId = snsId;
            this.pushToken = pushToken;
            this.joinRoute = joinRoute;
            this.name = name;
            this.email = email;
            this.phoneNumber = phoneNumber;
            this.gender = gender;
            this.age = age;
            this.role = role;
            this.duplicateInfo = duplicateInfo;
        }

        public MemberAdditionalInfo toMemberAdditionalInfoEntity() {
            return MemberAdditionalInfo
                    .builder()
                    .name(name)
                    .email(email)
                    .phoneNumber(phoneNumber)
                    .gender(gender)
                    .age(age)
                    .role(role)
                    .build();
        }

        public Member toMemberEntity() {
            return Member
                    .builder()
                    .snsId(snsId)
                    .jointRoute(joinRoute)
                    .nickname(nickname)
                    .duplicateInfo(duplicateInfo)
                    .build();
        }
    }
}