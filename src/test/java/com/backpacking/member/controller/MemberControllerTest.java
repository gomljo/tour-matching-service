package com.backpacking.member.controller;

import com.backpacking.global.security.config.SecurityConfig;
import com.backpacking.global.security.filter.JwtAuthenticationFilter;
import com.backpacking.global.security.provider.token.JwtTokenProvider;
import com.backpacking.member.domain.model.Member;
import com.backpacking.member.dto.MemberRegisterDto;
import com.backpacking.member.dto.VerificationDto;
import com.backpacking.member.exception.MemberException;
import com.backpacking.member.service.MemberService;
import com.backpacking.member.type.Roles;
import com.backpacking.member.util.MemberHelper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static com.backpacking.member.exception.MemberExceptionCode.ALREADY_REGISTERED;
import static com.backpacking.member.exception.MemberExceptionCode.INVALID_AUTHENTICATION_CODE;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = MemberController.class,
        excludeFilters = {
                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SecurityConfig.class),
                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = JwtAuthenticationFilter.class),
                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = JwtTokenProvider.class),
        })
class MemberControllerTest {

    @MockBean
    private MemberService memberService;

    @MockBean
    private MockMailService mailService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    private final MemberHelper memberHelper = new MemberHelper();


    @Test
    @WithMockUser
    @DisplayName("회원 가입 요청 성공")
    void success_register() throws Exception {

        // given
        Member member = memberHelper.generateMember();
        given(mailService.generateAuthenticationCode())
                .willReturn("123ER21122");
        given(memberService.register(any(), anyString()))
                .willReturn(member);
        doNothing().when(mailService).sendMail(any());

        MemberRegisterDto.Request request = new MemberRegisterDto.Request(member.getEmail(),
                member.getPhoneNumber(), member.getPassword(), List.of(Roles.ROLE_TOURIST), member.getAddress());

        // when

        // then
        mockMvc.perform(post("/member/join")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(request))
                        .with(csrf())
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.memberId").value(member.getMemberId()))
                .andExpect(jsonPath("$.email").value(member.getEmail()));

    }

    @Test
    @WithMockUser
    @DisplayName("회원 가입 요청 실패 - 이미 가입된 회원")
    void fail_register() throws Exception {

        // given
        Member member = memberHelper.generateMember();
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
                        .with(csrf())
                )

                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorCode").value(ALREADY_REGISTERED.name()))
                .andExpect(jsonPath("$.descriptions").value(ALREADY_REGISTERED.getDescription()));
    }

    @Test
    @WithMockUser
    @DisplayName("인증 코드 검증 성공")
    void success_verify() throws Exception {

        // given
        Member member = memberHelper.generateMember();
        Member updatedMember = memberHelper.generateVerifiedMember();

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
                        .with(csrf())
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userEmail").value(updatedMember.getEmail()))
                .andExpect(jsonPath("$.verifiedStatus").value(updatedMember.getVerifiedStatus().name()));
    }


    @Test
    @WithMockUser
    @DisplayName("인증 코드 검증 실패 - 검증 코드 불일치")
    void fail_verify() throws Exception {

        // given
        Member member = memberHelper.generateMember();

        given(memberService.verifyAuthenticationCode(any()))
                .willThrow(new MemberException(INVALID_AUTHENTICATION_CODE));


        VerificationDto.Request request = new VerificationDto.Request(member.getEmail(), member.getAuthenticationCode());
        // when

        // then
        mockMvc.perform(put("/member/verify")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(request))
                        .with(csrf())
                )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorCode").value(INVALID_AUTHENTICATION_CODE.name()))
                .andExpect(jsonPath("$.descriptions").value(INVALID_AUTHENTICATION_CODE.getDescription()));
    }

}