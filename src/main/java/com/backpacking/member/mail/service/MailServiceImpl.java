package com.backpacking.member.mail.service;

import com.backpacking.member.mail.dto.MailDto;
import com.backpacking.member.mail.util.MailMaker;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import static com.backpacking.member.mail.constants.GenerateCodeConstants.*;

@Component
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {
    private final MailMaker mailMaker;
    private final JavaMailSender mailSender;

    @Override
    public void sendMail(MailDto mailDto) {
        String linkAddress = mailMaker.generateLinkAddress(mailDto);
        SimpleMailMessage message = mailMaker.generateMailContent(mailDto, linkAddress);
        mailSender.send(message);
    }

    public String generateAuthenticationCode() {
        return RandomStringUtils.random(CODE_LENGTH, USE_LETTER, USE_NUMBER);
    }

}

