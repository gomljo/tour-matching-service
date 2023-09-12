package com.backpacking.member.dto;

import com.backpacking.member.constants.VerifiedStatus;
import com.backpacking.member.domain.model.Member;
import com.backpacking.member.validation.register.email.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


public class VerificationDto {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {
        @Email
        private String userEmail;
        private String AuthenticationCode;
    }


    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        private String userEmail;
        private VerifiedStatus verifiedStatus;

        public static VerificationDto.Response fromEntity(Member member) {
            return new Response(member.getEmail(), member.getVerifiedStatus());
        }
    }

}
