package com.backpacking.member.mail.service;

import com.backpacking.member.mail.dto.MailDto;

public interface MailService {

    void sendMail(MailDto mailDto);
    String generateAuthenticationCode();


}
