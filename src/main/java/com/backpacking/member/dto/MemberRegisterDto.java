package com.backpacking.member.dto;

import com.backpacking.member.domain.model.Member;
import com.backpacking.member.domain.vo.Address;
import com.backpacking.member.validation.register.address.ValidAddress;
import com.backpacking.member.validation.register.email.Email;
import com.backpacking.member.validation.register.password.Password;
import com.backpacking.member.validation.register.phonenumber.Phone;
import com.backpacking.member.validation.register.roles.Roles;
import lombok.*;

import java.util.List;

public class MemberRegisterDto {

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class Request {

        @NonNull
        @Email
        private String email;
        @NonNull
        @Phone
        private String phoneNumber;
        @NonNull
        @Password
        private String password;
        @NonNull
        @Roles
        private List<String> roles;
        @NonNull
        @ValidAddress
        private Address address;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    public static class Response {
        private long memberId;
        private String email;

        public static Response fromEntity(Member member){
            return new Response(member.getMemberId(), member.getEmail());
        }
    }
}
