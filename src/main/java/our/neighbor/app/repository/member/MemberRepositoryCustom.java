package our.neighbor.app.repository.member;

public interface MemberRepositoryCustom {

    Boolean checkExistingMember(String snsId, String joinRoute);

    Boolean checkExistingNickname(String nickname);
}
