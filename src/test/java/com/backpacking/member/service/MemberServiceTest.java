package com.backpacking.member.service;

import com.backpacking.member.constants.VerifiedStatus;
import com.backpacking.member.domain.model.Member;
import com.backpacking.member.domain.vo.Address;
import com.backpacking.member.dto.MemberRegisterDto;
import com.backpacking.member.dto.VerificationDto;
import com.backpacking.member.exception.MemberException;
import com.backpacking.member.exception.MemberExceptionCode;
import com.backpacking.member.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

    @Mock
    private MemberRepository memberRepository;

    @InjectMocks
    private MemberServiceImpl memberService;

    @Test
    @DisplayName("회원 가입 성공")
    void success_register() {
        // given

        String authCode = "123ER21122";

        Member member = Member.builder()
                .email("lifi.jw@gmail.com")
                .password("1q!W23")
                .address(new Address("korea", "seoul", "detail Address"))
                .phoneNumber("010-111-1111")
                .verifiedStatus(VerifiedStatus.NOT_VERIFIED)
                .authenticationCode(authCode)
                .roles(List.of("ROLE_TOURIST"))
                .build();

        MemberRegisterDto.Request request = new MemberRegisterDto.Request(member.getEmail(),
                member.getPhoneNumber(), member.getPassword(), member.getRoles(), member.getAddress());
        given(memberRepository.existsByEmail(anyString()))
                .willReturn(false);

        given(memberRepository.save(any()))
                .willReturn(member);

        // when
        ArgumentCaptor<Member> captor = ArgumentCaptor.forClass(Member.class);
        Member register = memberService.register(request, anyString());

        // then

        Member capturedMember = captor.getValue();

        assertEquals(capturedMember.getEmail(), register.getEmail());
        assertEquals(capturedMember.getPassword(), register.getPassword());
        assertArrayEquals(capturedMember.getRoles().toArray(), register.getRoles().toArray());
        assertEquals(capturedMember.getPhoneNumber(), register.getPhoneNumber());
        assertEquals(capturedMember.getAuthenticationCode(), register.getAuthenticationCode());
        assertEquals(capturedMember.getAddress(), register.getAddress());
    }

    @Test
    @DisplayName("회원 가입 실패 - 가입 요청 이메일 중복")
    void fail_register_redundantEmail() {
        // given
        Member member = Member.builder()
                .email("lifi.jw@gmail.com")
                .password("1q!W23")
                .address(new Address("korea", "seoul", "detail Address"))
                .phoneNumber("010-111-1111")
                .verifiedStatus(VerifiedStatus.NOT_VERIFIED)
                .authenticationCode("123ER21122")
                .roles(List.of("ROLE_TOURIST"))
                .build();

        MemberRegisterDto.Request request = new MemberRegisterDto.Request(member.getEmail(),
                member.getPhoneNumber(), member.getPassword(), member.getRoles(), member.getAddress());

        given(memberRepository.existsByEmail(anyString()))
                .willThrow(new MemberException(MemberExceptionCode.ALREADY_REGISTERED));

        // when
        MemberException memberException = assertThrows(MemberException.class, () -> memberService.register(request, "123ER21122"));

        // then
        assertEquals(memberException.getErrorCode(), MemberExceptionCode.ALREADY_REGISTERED.name());
    }

    @Test
    @DisplayName("인증 코드 검증 성공")
    void success_verify_authCode(){

        // given
        Member member = generateMember();

        given(memberRepository.findByEmail(anyString()))
                .willReturn(Optional.of(member));
        VerificationDto.Request request = generateVerificationDto();
        // when
        Member verifiedMember = memberService.verifyAuthenticationCode(request);

        // then
        assertEquals(verifiedMember.getMemberId(), member.getMemberId());
        assertEquals(verifiedMember.getAuthenticationCode(), request.getAuthenticationCode());
    }

    @Test
    @DisplayName("검증 상태 변경 성공")
    void success_update_verifiedStatus(){

        // given
        Member member = generateMember();
        Member verifiedMember = generateVerifiedMember();

        given(memberRepository.save(any()))
                .willReturn(verifiedMember);

        // when
        Member updatedMember = memberService.updateVerifiedStatus(member);

        // then
        assertEquals(member.getMemberId(), updatedMember.getMemberId());
        assertEquals(updatedMember.getVerifiedStatus(), VerifiedStatus.VERIFIED);
    }

    private Member generateMember(){
        return Member.builder()
                .memberId(1L)
                .email("gildong@gmail.com")
                .password("1q!W23")
                .address(new Address("korea", "seoul", "detail Address"))
                .phoneNumber("010-111-1111")
                .verifiedStatus(VerifiedStatus.NOT_VERIFIED)
                .authenticationCode("123ER21122")
                .roles(List.of("ROLE_TOURIST"))
                .build();
    }

    private Member generateVerifiedMember(){
        return Member.builder()
                .memberId(1L)
                .email("gildong@gmail.com")
                .password("1q!W23")
                .address(new Address("korea", "seoul", "detail Address"))
                .phoneNumber("010-111-1111")
                .verifiedStatus(VerifiedStatus.VERIFIED)
                .authenticationCode("123ER21122")
                .roles(List.of("ROLE_TOURIST"))
                .build();
    }

    private VerificationDto.Request generateVerificationDto(){
        return new VerificationDto.Request(
                "gildong@gmail.com",
                "123ER21122");
    }

}

