package com.backpacking.guide.guide.service;

import com.backpacking.guide.guide.domain.model.GuideInfo;
import com.backpacking.guide.guide.exception.GuideInfoException;
import com.backpacking.guide.guide.repository.GuideInfoRepository;
import com.backpacking.member.domain.model.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Set;

import static com.backpacking.guide.guide.exception.GuideInfoExceptionCode.GUIDE_INFO_NOT_FOUND;
import static com.backpacking.guide.guide.exception.GuideInfoExceptionCode.INVALID_ACCESS;

@Slf4j
@Service
@RequiredArgsConstructor
public class GuideInfoServiceImpl implements GuideInfoService {

    private final GuideInfoRepository guideInfoRepository;

    @Override
    public GuideInfo register(GuideInfo guideInfo) {
        return guideInfoRepository.save(guideInfo);
    }

    @Override
    public boolean isAlreadyRegistered(Member member) {
        return guideInfoRepository.findFirstByMemberEmail(member.getEmail()).isPresent();
    }

    @Override
    public GuideInfo update(GuideInfo guideInfo, String introduce, Set<String> mainThemes) {
        guideInfo.updateIntroduce(introduce);
        guideInfo.updateMainThemes(mainThemes);
        return guideInfoRepository.save(guideInfo);
    }

    @Override
    public GuideInfo findGuideInfo(Long guideInfoId) {
        return guideInfoRepository.findById(guideInfoId)
                .orElseThrow(() -> new GuideInfoException(GUIDE_INFO_NOT_FOUND));
    }

    @Override
    public Member findMember(Long guideInfoId) {
        return guideInfoRepository.findMemberByGuideInfoId(guideInfoId)
                .orElseThrow(() -> new GuideInfoException(GUIDE_INFO_NOT_FOUND));
    }

    @Override
    public Long delete(Long guideInfoId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();
        if (guideInfoRepository.findFirstByMemberEmail(userEmail).isEmpty()) {
            throw new GuideInfoException(INVALID_ACCESS);
        }
        return guideInfoRepository.deleteGuideInfoById(guideInfoId);
    }

}
