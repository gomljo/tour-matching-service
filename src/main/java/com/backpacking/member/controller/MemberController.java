package com.backpacking.member.controller;

import com.backpacking.member.dto.MemberRegisterDto;
import com.backpacking.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/join")
    public MemberRegisterDto.Response register(@RequestBody MemberRegisterDto.Request request) {
        return MemberRegisterDto.Response.fromEntity(memberService.register(request));
    }


}
