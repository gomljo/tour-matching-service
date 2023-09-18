package com.backpacking.global.tour.reservation.dto;

import com.backpacking.guide.guide.domain.model.GuideInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.awt.print.Pageable;
import java.time.LocalDateTime;
import java.util.List;

public class FindGuideInfoListDto {
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {
        private LocalDateTime startDate;
        private Pageable pageable;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    public static class Response {
        private List<GuideInfo> guideInfos;
    }


}
