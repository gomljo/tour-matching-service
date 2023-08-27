package com.backpacking.tourist.tourist.dto;

import com.backpacking.global.member.vo.Address;
import com.backpacking.tourist.tourist.domain.Tourist;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

public class CreateTourist {

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    public static class Request {

        private String email;
        private String phoneNumber;
        private String password;
        private List<String> roles;
        private Address address;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    public static class Response {
        private long touristId;
        private String email;

        public static Response fromEntity(Tourist tourist){
            return new Response(tourist.getId(), tourist.getEmail());
        }
    }
}
