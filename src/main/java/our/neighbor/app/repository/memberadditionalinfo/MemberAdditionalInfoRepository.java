package our.neighbor.app.repository.memberadditionalinfo;

import org.springframework.data.jpa.repository.JpaRepository;
import our.neighbor.app.domain.member.MemberAdditionalInfo;

public interface MemberAdditionalInfoRepository extends JpaRepository<MemberAdditionalInfo, Long>, MemberAdditionalInfoRepositoryCustom {
}
