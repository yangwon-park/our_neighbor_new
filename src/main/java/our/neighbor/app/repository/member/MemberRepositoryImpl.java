package our.neighbor.app.repository.member;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import our.neighbor.app.domain.member.QMember;

import static our.neighbor.app.domain.member.QMember.*;

@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Boolean checkAlreadyExistingNickname(String nickname) {
        Integer fetchOne =
                queryFactory
                        .selectOne()
                        .from(member)
                        .where(member.nickname.eq(nickname))
                        .fetchFirst();

        return fetchOne != null;
    }
}
