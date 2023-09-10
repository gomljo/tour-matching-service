package com.backpacking.member.dto;

import com.backpacking.member.domain.model.Member;
import com.backpacking.member.domain.vo.Address;
import lombok.*;

import java.util.List;

public class MemberRegisterDto {

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class Request {

        @NonNull
        private String email;
        @NonNull
        private String phoneNumber;
        @NonNull
        private String password;
        @NonNull
        private List<String> roles;
        @NonNull
        private Address address;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    public static class Response {
        private long touristId;
        private String email;

        public static Response fromEntity(Member member){
            return new Response(member.getMemberId(), member.getEmail());
        }
    }
}
