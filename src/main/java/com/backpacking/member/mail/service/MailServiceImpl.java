package com.backpacking.member.mail.service;

import com.backpacking.member.mail.dto.MailDto;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import static com.backpacking.member.mail.constants.GenerateCodeConstants.*;
import static com.backpacking.member.mail.constants.SendingMailConstants.*;

@Component
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {

    @Value("${spring.mail.username}")
    private String username;
    private final JavaMailSender mailSender;

    @Override
    public void sendMail(MailDto mailDto) {

        SimpleMailMessage message = generateMailContent(mailDto);
        mailSender.send(message);
    }

    public String generateAuthenticationCode() {
        return RandomStringUtils.random(CODE_LENGTH, USE_LETTER, USE_NUMBER);
    }

    public String generateLinkText(String userEmail, String authenticationCode) {
        return LINK.getValue() + EMAIL_QUERY.getValue() + userEmail + CODE_QUERY.getValue() + authenticationCode;
    }

    public SimpleMailMessage generateMailContent(MailDto mailDto) {
        SimpleMailMessage message = new SimpleMailMessage();
        String linkText = generateLinkText(mailDto.getUserEmail(), mailDto.getAuthenticationCode());
        message.setFrom(username);
        message.setTo(mailDto.getUserEmail());
        message.setSubject(SUBJECT.getValue());
        message.setText(CONTENT.getValue());
        message.setText(linkText);
        return message;
    }

    @Override
    public boolean verifyAuthenticationCode(String issuedAuthCode, String requestAuthCode) {
        return issuedAuthCode.equals(requestAuthCode);
    }

}

