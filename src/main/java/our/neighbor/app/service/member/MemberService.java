package our.neighbor.app.service.member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import our.neighbor.app.domain.member.dto.MemberDTO;
import our.neighbor.app.domain.member.Member;
import our.neighbor.app.domain.member.MemberAdditionalInfo;
import our.neighbor.app.repository.member.MemberRepository;
import our.neighbor.app.repository.memberadditionalinfo.MemberAdditionalInfoRepository;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional(transactionManager = "appTransactionManager", readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;

    private final MemberAdditionalInfoRepository memberAdditionalInfoRepository;

    @Transactional(transactionManager = "appTransactionManager")
    public void joinMember(MemberDTO.Join request) {
        Member member = memberRepository.save(request.toMemberEntity());
        MemberAdditionalInfo memberAdditionalInfo =
                memberAdditionalInfoRepository.save(request.toMemberAdditionalInfoEntity(member));

        memberAdditionalInfo.linkMember(member);
    }
}
