package com.backpacking.guide.guide.service;

import com.backpacking.guide.guide.domain.model.GuideInfo;
import com.backpacking.member.domain.model.Member;

import java.util.Set;

public interface GuideInfoService {

    GuideInfo register(GuideInfo guideInfo);
    GuideInfo update(GuideInfo guideInfo, String introduce, Set<String> mainThemes);
    boolean isAlreadyRegistered(Member member);
    Long delete(Long guideInfoId);
    GuideInfo findGuideInfo(Long guideInfoId);
    Member findMember(Long guideInfoId);

}
