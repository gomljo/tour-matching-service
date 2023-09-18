package com.backpacking.global.guide.information.dto;

import com.backpacking.guide.guide.domain.model.GuideInfo;
import com.backpacking.member.type.Roles;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;


public class GuideInfoDto {
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {
        @NotBlank
        private String introduce;
        @NotNull
        private Set<String> mainThemes;

    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Response {
        private Long guideInfoId;

        private String userEmail;

        private String introduce;

        private Set<String> mainThemes;

        private double starRating;

        private int cumulatedReservation;
        private List<Roles> roles;

        public static GuideInfoDto.Response from(GuideInfo guideInfo) {
            return Response.builder()
                    .guideInfoId(guideInfo.getId())
                    .userEmail(guideInfo.getMember().getEmail())
                    .introduce(guideInfo.getIntroduce())
                    .mainThemes(guideInfo.getMainThemes())
                    .starRating(guideInfo.getStarRating())
                    .cumulatedReservation(guideInfo.getCumulatedReservation())
                    .roles(guideInfo.getMember().getRoles())
                    .build();
        }
    }


}
