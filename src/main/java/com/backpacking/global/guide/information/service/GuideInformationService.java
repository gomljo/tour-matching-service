package com.backpacking.global.guide.information.service;

import com.backpacking.global.guide.information.dto.GuideInfoDto;
import com.backpacking.global.security.exception.security.SecurityException;
import com.backpacking.global.security.util.AuthenticationUtil;
import com.backpacking.guide.guide.domain.model.GuideInfo;
import com.backpacking.guide.guide.exception.GuideInfoException;
import com.backpacking.guide.guide.service.GuideInfoService;
import com.backpacking.member.domain.model.Member;
import com.backpacking.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.backpacking.global.security.exception.security.SecurityExceptionCode.INVALID_ACCESS;
import static com.backpacking.guide.guide.exception.GuideInfoExceptionCode.ALREADY_REGISTERED_GUIDE_INFO;

@Service
@RequiredArgsConstructor
public class GuideInformationService {

    private final MemberService memberService;
    private final GuideInfoService guideInfoService;
    private final AuthenticationUtil authenticationUtil;
    @Transactional
    @PreAuthorize(
            "isAuthenticated() " +
            "and hasRole('ROLE_TOURIST') ")
    public GuideInfo register(String email, GuideInfoDto.Request request){
        Member member = memberService.findMemberBy(email);

        if(authenticationUtil.isInvalidAccess(member.getEmail())){
            throw new SecurityException(INVALID_ACCESS);
        }

        if(guideInfoService.isAlreadyRegistered(member)){
            throw new GuideInfoException(ALREADY_REGISTERED_GUIDE_INFO);
        }

        Member guide = memberService.registerGuideRole(member);

        return guideInfoService.register(
                GuideInfo.builder()
                        .introduce(request.getIntroduce())
                        .mainThemes(request.getMainThemes())
                        .member(guide)
                        .build());
    }

    @Transactional
    @PreAuthorize(value = "isAuthenticated() " +
            "and hasRole('ROLE_GUIDE')")
    public GuideInfo update(long guideInfoId, GuideInfoDto.Request request){
        GuideInfo guideInfo = guideInfoService.findGuideInfo(guideInfoId);
        if(authenticationUtil.isInvalidAccess(guideInfo.getMember().getEmail())){
            throw new SecurityException(INVALID_ACCESS);
        }
        return guideInfoService.update(guideInfo, request.getIntroduce(), request.getMainThemes());
    }

    @Transactional
    @PreAuthorize("isAuthenticated() " +
            "and hasRole('ROLE_GUIDE')")
    public Long delete(long guideInfoId){
        Member member = guideInfoService.findMember(guideInfoId);
        if(authenticationUtil.isInvalidAccess(member.getEmail())){
            throw new SecurityException(INVALID_ACCESS);
        }
        Long deletedGuideInfoId = guideInfoService.delete(guideInfoId);
        memberService.deleteGuideRole(member);

        return deletedGuideInfoId;
    }

    @Transactional
    public GuideInfo findGuideInfo(Long guideInfoId){
        return guideInfoService.findGuideInfo(guideInfoId);
    }
}
