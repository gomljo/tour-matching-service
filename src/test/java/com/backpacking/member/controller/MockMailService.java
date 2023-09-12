package com.backpacking.member.controller;

import com.backpacking.member.mail.dto.MailDto;
import com.backpacking.member.mail.service.MailService;

public class MockMailService implements MailService {
    @Override
    public void sendMail(MailDto mailDto) {

    }

    @Override
    public String generateAuthenticationCode() {
        return "1234ERT";
    }
}
