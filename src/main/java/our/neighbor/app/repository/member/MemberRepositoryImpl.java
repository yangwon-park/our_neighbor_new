package our.neighbor.app.repository.member;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import our.neighbor.app.domain.member.QMember;

import static our.neighbor.app.domain.member.QMember.*;

@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Boolean checkExistingMember(String snsId, String joinRoute) {
        return queryFactory
                .selectOne()
                .from(member)
                .where(
                        member.snsId.eq(snsId)
                                .and(member.joinRoute.eq(joinRoute)))
                .fetchFirst() != null;
    }

    @Override
    public Boolean checkExistingNickname(String nickname) {
        return queryFactory
                .selectOne()
                .from(member)
                .where(member.nickname.eq(nickname))
                .fetchFirst() != null;
    }
}
