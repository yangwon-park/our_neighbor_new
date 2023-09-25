package our.neighbor.app.repository.member;

import org.springframework.data.jpa.repository.JpaRepository;
import our.neighbor.app.domain.member.Member;

public interface MemberRepository extends JpaRepository<Member, Long>, MemberRepositoryCustom{
}
