package our.neighbor.app.domain.member.dto;

import lombok.*;
import our.neighbor.app.domain.member.Member;
import our.neighbor.app.domain.member.MemberAdditionalInfo;
import our.neighbor.app.domain.member.MemberRole;

public class MemberDTO {

    @Getter
    @ToString
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Sns {

        private String snsId;

        private String joinRoute;

        @Builder
        public Sns(String snsId, String joinRoute) {
            this.snsId = snsId;
            this.joinRoute = joinRoute;
        }
    }


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
                    String nickname, String name, String email, String phoneNumber,
                    String gender, int age, MemberRole role, String duplicateInfo) {
            this.snsId = snsId;
            this.pushToken = pushToken;
            this.joinRoute = joinRoute;
            this.nickname = nickname;
            this.name = name;
            this.email = email;
            this.phoneNumber = phoneNumber;
            this.gender = gender;
            this.age = age;
            this.role = role;
            this.duplicateInfo = duplicateInfo;
        }

        public MemberAdditionalInfo toMemberAdditionalInfoEntity(Member member) {
            return MemberAdditionalInfo
                    .builder()
                    .name(name)
                    .email(email)
                    .phoneNumber(phoneNumber)
                    .gender(gender)
                    .age(age)
                    .role(role)
                    .member(member)
                    .build();
        }

        public Member toMemberEntity() {
            return Member
                    .builder()
                    .snsId(snsId)
                    .joinRoute(joinRoute)
                    .nickname(nickname)
                    .duplicateInfo(duplicateInfo)
                    .build();
        }
    }

    @Getter
    @ToString
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class JoinResponse {


        private String nickname;

        private String snsId;

        private String joinRoute;
        @Builder
        public JoinResponse(String nickname, String snsId, String joinRoute) {
            this.nickname = nickname;
            this.snsId = snsId;
            this.joinRoute = joinRoute;
        }

    }
}
