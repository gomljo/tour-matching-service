package com.backpacking.member.mail.service;

import com.backpacking.member.mail.dto.MailDto;
import com.backpacking.member.mail.util.MailMaker;
import com.backpacking.member.mail.util.MailMakerImpl;
import org.hibernate.validator.constraints.ru.INN;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import static com.backpacking.member.mail.constants.SendingMailConstants.*;
import static com.backpacking.member.mail.constants.SendingMailConstants.AUTH_CODE_QUERY;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MailServiceTest {
    @Mock
    private JavaMailSenderImpl javaMailSender;
    @Mock
    private MailMakerImpl mailMaker;

    @InjectMocks
    private MailServiceImpl mailService;

    @Test
    @DisplayName("메일 발송 검증")
    void verify_sendMail() {

        // given
        String mail = "gildong@gmail.com";
        String authCode = "1233EREW1";

        MailDto mailDto = new MailDto(mail, authCode);

        SimpleMailMessage message = new SimpleMailMessage();
        String linkText = LINK + LINK_END_POINT + EMAIL_QUERY + mail + AUTH_CODE_QUERY + authCode;
        message.setFrom("dooly@gmail.com");
        message.setTo(mailDto.getUserEmail());
        message.setSubject(SUBJECT);
        message.setText(CONTENT);
        message.setText(linkText);
        given(mailMaker.generateLinkAddress(any()))
                .willReturn(linkText);
        given(mailMaker.generateMailContent(any(), anyString()))
                .willReturn(message);
        doNothing().when(javaMailSender).send(any(SimpleMailMessage.class));
        // when
        mailService.sendMail(mailDto);

        // then
        verify(javaMailSender, times(1)).send(message);

    }

}