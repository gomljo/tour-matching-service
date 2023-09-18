package com.backpacking.global.guide.information.controller;

import com.backpacking.global.guide.information.dto.GuideInfoDeleteDto;
import com.backpacking.global.guide.information.service.GuideInformationService;
import com.backpacking.global.guide.information.dto.GuideInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/member/guideInfo")
@RequiredArgsConstructor
public class GuideInfoController {

    private final GuideInformationService guideInformationService;

    @PostMapping("/register/{email}")
    public GuideInfoDto.Response register(@PathVariable String email,
                                          @RequestBody GuideInfoDto.Request request){
        return GuideInfoDto.Response.from(
                guideInformationService.register(email, request));
    }

    @PutMapping("/update/{guideInfoId}")
    public GuideInfoDto.Response updateGuideInfo(@PathVariable Long guideInfoId,
                                                 @RequestBody GuideInfoDto.Request request){
        return GuideInfoDto.Response.from(
                guideInformationService.update(guideInfoId, request));
    }

    @DeleteMapping("/delete/{guideInfoId}")
    public GuideInfoDeleteDto deleteGuideInfo(@PathVariable long guideInfoId){
        return GuideInfoDeleteDto.from(guideInformationService.delete(guideInfoId));
    }

    @GetMapping("/{guideInfoId}")
    public GuideInfoDto.Response findGuideInfo(@PathVariable Long guideInfoId){
        return GuideInfoDto.Response.from(guideInformationService.findGuideInfo(guideInfoId));
    }
}
