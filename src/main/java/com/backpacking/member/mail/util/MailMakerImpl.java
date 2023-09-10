package com.backpacking.member.mail.util;

import com.backpacking.member.mail.dto.MailDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

import static com.backpacking.member.mail.constants.SendingMailConstants.*;
import static com.backpacking.member.mail.constants.SendingMailConstants.AUTH_CODE_QUERY;

@Component
public class MailMakerImpl implements MailMaker{
    @Value("${spring.mail.username}")
    private String username;

    @Value("${link-address}")
    private String linkAddress;

    @Override
    public SimpleMailMessage generateMailContent(MailDto mailDto, String linkAddress) {
        SimpleMailMessage message = new SimpleMailMessage();
         message.setFrom(username);
        message.setTo(mailDto.getUserEmail());
        message.setSubject(SUBJECT);
        message.setText(CONTENT);
        message.setText(linkAddress);
        return message;
    }

    @Override
    public String generateLinkAddress(MailDto mailDto) {
        return LINK + linkAddress + LINK_END_POINT
                + EMAIL_QUERY + mailDto.getUserEmail() + AUTH_CODE_QUERY
                + mailDto.getAuthenticationCode();
    }
}
