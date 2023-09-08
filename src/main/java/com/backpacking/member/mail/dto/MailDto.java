package com.backpacking.member.mail.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MailDto {

    private String userEmail;
    private String AuthenticationCode;


}
