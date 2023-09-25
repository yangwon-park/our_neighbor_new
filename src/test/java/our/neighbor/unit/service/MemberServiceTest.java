package our.neighbor.unit.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;
import our.neighbor.app.domain.member.dto.MemberDTO;
import our.neighbor.app.domain.member.Member;
import our.neighbor.app.domain.member.MemberAdditionalInfo;
import our.neighbor.app.exception.DuplicateNicknameException;
import our.neighbor.app.repository.member.MemberRepository;
import our.neighbor.app.repository.memberadditionalinfo.MemberAdditionalInfoRepository;
import our.neighbor.app.service.member.MemberService;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class MemberServiceTest {

    @Mock
    MemberRepository memberRepository;

    @Mock
    MemberAdditionalInfoRepository memberAdditionalInfoRepository;

    @InjectMocks
    MemberService memberService;

    MemberDTO.Join joinRequest;

    @BeforeEach
    void setUp() {
        joinRequest = MemberDTO.Join
                .builder()
                .snsId("snsId")
                .pushToken("paosudakl2anmlekzdamldsz")
                .joinRoute("NAVER")
                .name("회원가입할래")
                .nickname("닉네임")
                .email("email@email.com")
                .phoneNumber("010-1234-1242")
                .gender("MALE")
                .age(27)
                .duplicateInfo("dup_init")
                .build();
    }

    @Test
    @DisplayName("회원 가입 성공")
    void should_JoinMemberToBeSuccessful() {
        given(memberRepository.checkAlreadyExistingNickname(joinRequest.getNickname())).willReturn(false);
        given(memberAdditionalInfoRepository.save(any(MemberAdditionalInfo.class)))
                .willAnswer(invocation -> invocation.getArgument(0));
        given(memberRepository.save(any(Member.class)))
                .willAnswer(invocation -> invocation.getArgument(0));

        memberService.joinMember(joinRequest);

        ArgumentCaptor<MemberAdditionalInfo> memberAdditionalInfoArgumentCaptor =
                ArgumentCaptor.forClass(MemberAdditionalInfo.class);

        ArgumentCaptor<Member> memberArgumentCaptor = ArgumentCaptor.forClass(Member.class);

        then(memberRepository).should(times(1)).checkAlreadyExistingNickname(joinRequest.getNickname());
        then(memberAdditionalInfoRepository).should(times(1))
                .save(memberAdditionalInfoArgumentCaptor.capture());
        then(memberRepository).should(times(1)).save(memberArgumentCaptor.capture());

        MemberAdditionalInfo capturedMemberAdditionalInfo = memberAdditionalInfoArgumentCaptor.getValue();
        Member capturedMember = memberArgumentCaptor.getValue();

        assertThat(capturedMemberAdditionalInfo.getName()).isEqualTo(joinRequest.getName());
        assertThat(capturedMember.getJointRoute()).isEqualTo(joinRequest.getJoinRoute());
    }

    @Test
    @DisplayName("회원 가입 - 닉네임 중복으로 인한 실패")
    void should_GetExceptionWhenJoinMember_ByAlreadyExistingNickname() {
        given(memberRepository.checkAlreadyExistingNickname(joinRequest.getNickname())).willReturn(true);

        assertThatThrownBy(
                () -> memberService.joinMember(joinRequest)
        ).isInstanceOf(DuplicateNicknameException.class);

        then(memberRepository).should().checkAlreadyExistingNickname(joinRequest.getNickname());
        then(memberRepository).should(never()).save(any(Member.class));
        then(memberAdditionalInfoRepository).should(never()).save(any(MemberAdditionalInfo.class));
    }
}
