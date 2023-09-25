package our.neighbor.app.service.member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import our.neighbor.app.exception.DuplicateException;
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

    public String checkExistingMember(String snsId, String joinRoute) {
        Boolean isExistingMember = memberRepository.checkExistingMember(snsId, joinRoute);

        if (isExistingMember) {
            log.error("::: Duplicate Member Exception OCCUR :::");
            throw new DuplicateException("SNS ID", snsId);
        }

        return "newUser";
    }

    @Transactional(transactionManager = "appTransactionManager")
    public MemberDTO.JoinResponse joinMember(MemberDTO.Join request) {
        checkExistingNickname(request);

        Member member = memberRepository.save(request.toMemberEntity());

        memberAdditionalInfoRepository.save(request.toMemberAdditionalInfoEntity(member));

        return MemberDTO.JoinResponse
                .builder()
                .nickname(member.getNickname())
                .joinRoute(member.getJoinRoute())
                .snsId(member.getSnsId())
                .build();
    }

    private void checkExistingNickname(MemberDTO.Join request) {
        Boolean isAlreadyExistingNickname = memberRepository.checkExistingNickname(request.getNickname());

        if (isAlreadyExistingNickname) {
            log.error("::: Duplicate Nickname Exception OCCUR :::");
            throw new DuplicateException("닉네임", request.getNickname());
        }
    }

}
