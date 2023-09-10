package com.backpacking.member.controller;

import com.backpacking.member.dto.MemberRegisterDto;
import com.backpacking.member.dto.VerificationDto;
import com.backpacking.member.mail.dto.MailDto;
import com.backpacking.member.mail.service.MailService;
import com.backpacking.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final MailService mailService;
    @PostMapping("/join")
    public MemberRegisterDto.Response register(@RequestBody MemberRegisterDto.Request request) {

        String authenticationCode = mailService.generateAuthenticationCode();

        MemberRegisterDto.Response response = MemberRegisterDto.Response
                .fromEntity(memberService.register(request, authenticationCode));

        mailService.sendMail(new MailDto(request.getEmail(), authenticationCode));

        return response;
    }

    @PutMapping("/verify")
    public VerificationDto.Response verify(@RequestBody VerificationDto.Request request) {
        return VerificationDto.Response.fromEntity(memberService.verify(request));
    }



}
