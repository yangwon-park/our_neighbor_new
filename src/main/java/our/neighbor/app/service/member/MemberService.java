package our.neighbor.app.service.member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import our.neighbor.app.exception.DuplicateNicknameException;
import our.neighbor.app.domain.member.dto.MemberDTO;
import our.neighbor.app.domain.member.Member;
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
    public MemberDTO.JoinResponse joinMember(MemberDTO.Join request) {
        checkAlreadyExistingNickname(request);

        Member member = memberRepository.save(request.toMemberEntity());

        memberAdditionalInfoRepository.save(request.toMemberAdditionalInfoEntity(member));

        return MemberDTO.JoinResponse
                .builder()
                .nickname(member.getNickname())
                .joinRoute(member.getJointRoute())
                .snsId(member.getSnsId())
                .build();
    }

    private void checkAlreadyExistingNickname(MemberDTO.Join request) {
        Boolean isAlreadyExistingNickname = memberRepository.checkAlreadyExistingNickname(request.getNickname());

        if (isAlreadyExistingNickname) {
            log.error("::: Duplicate Nickname Exception OCCUR :::");
            throw new DuplicateNicknameException(request.getNickname());
        }
    }
}
