package com.backpacking.member.controller;

import com.backpacking.global.security.config.SecurityConfig;
import com.backpacking.global.security.filter.JwtAuthenticationFilter;
import com.backpacking.global.security.token.JwtTokenProvider;
import com.backpacking.member.constants.VerifiedStatus;
import com.backpacking.member.domain.model.Member;
import com.backpacking.member.domain.vo.Address;
import com.backpacking.member.dto.MemberRegisterDto;
import com.backpacking.member.dto.VerificationDto;
import com.backpacking.member.exception.MemberException;
import com.backpacking.member.service.MemberService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static com.backpacking.member.exception.MemberExceptionCode.ALREADY_REGISTERED;
import static com.backpacking.member.exception.MemberExceptionCode.INVALID_AUTHENTICATION_CODE;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = MemberController.class)
@Import({SecurityConfig.class})
@AutoConfigureMockMvc
class MemberControllerTest {

    @MockBean
    private MemberService memberService;

    @MockBean
    private MockMailService mailService;

    @MockBean
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Test
    @DisplayName("회원 가입 요청 성공")
    void success_register() throws Exception {

        // given
        Member member = generateMember();
        given(mailService.generateAuthenticationCode())
                .willReturn("123ER21122");
        given(memberService.register(any(), anyString()))
                .willReturn(member);
        doNothing().when(mailService).sendMail(any());

        MemberRegisterDto.Request request = new MemberRegisterDto.Request(member.getEmail(),
                member.getPhoneNumber(), member.getPassword(), member.getRoles(), member.getAddress());

        // when

        // then
        mockMvc.perform(post("/member/join")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(request))
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.memberId").value(member.getMemberId()))
                .andExpect(jsonPath("$.email").value(member.getEmail()));

    }

    @Test
    @DisplayName("회원 가입 요청 실패 - 이미 가입된 회원")
    void fail_register() throws Exception {

        // given
        Member member = generateMember();
        given(mailService.generateAuthenticationCode())
                .willReturn("123ER21122");
        given(memberService.register(any(), anyString()))
                .willThrow(new MemberException(ALREADY_REGISTERED));

        MemberRegisterDto.Request request = new MemberRegisterDto.Request(member.getEmail(),
                member.getPhoneNumber(), member.getPassword(), member.getRoles(), member.getAddress());

        // when

        // then
        mockMvc.perform(post("/member/join")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(request))
                )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorCode").value(ALREADY_REGISTERED.name()))
                .andExpect(jsonPath("$.descriptions").value(ALREADY_REGISTERED.getDescription()));
    }

    @Test
    @DisplayName("인증 코드 검증 성공")
    void success_verify() throws Exception {

        // given
        Member member = generateMember();
        Member updatedMember = generateVerifiedMember();

        given(memberService.verifyAuthenticationCode(any()))
                .willReturn(updatedMember);
        given(memberService.updateVerifiedStatus(any()))
                .willReturn(updatedMember);

        VerificationDto.Request request = new VerificationDto.Request(member.getEmail(), member.getAuthenticationCode());
        // when

        // then
        mockMvc.perform(put("/member/verify")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(request))
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userEmail").value(updatedMember.getEmail()))
                .andExpect(jsonPath("$.verifiedStatus").value(updatedMember.getVerifiedStatus().name()));
    }


    @Test
    @DisplayName("인증 코드 검증 실패 - 검증 코드 불일치")
    void fail_verify() throws Exception {

        // given
        Member member = generateMember();

        given(memberService.verifyAuthenticationCode(any()))
                .willThrow(new MemberException(INVALID_AUTHENTICATION_CODE));


        VerificationDto.Request request = new VerificationDto.Request(member.getEmail(), member.getAuthenticationCode());
        // when

        // then
        mockMvc.perform(put("/member/verify")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(request))
                )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorCode").value(INVALID_AUTHENTICATION_CODE.name()))
                .andExpect(jsonPath("$.descriptions").value(INVALID_AUTHENTICATION_CODE.getDescription()));
    }


    private Member generateMember(){
        return Member.builder()
                .memberId(1L)
                .email("lifi.jw@gmail.com")
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
                .email("lifi.jw@gmail.com")
                .password("1q!W23")
                .address(new Address("korea", "seoul", "detail Address"))
                .phoneNumber("010-111-1111")
                .verifiedStatus(VerifiedStatus.VERIFIED)
                .authenticationCode("123ER21122")
                .roles(List.of("ROLE_TOURIST"))
                .build();
    }

}