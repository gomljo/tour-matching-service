package com.backpacking.global.guide.information.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GuideInfoDeleteDto {

    private Long deletedGuideInfo;

    public static GuideInfoDeleteDto from(Long deletedGuideInfo){
        return new GuideInfoDeleteDto(deletedGuideInfo);
    }
}
