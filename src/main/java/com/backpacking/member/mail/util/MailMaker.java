package com.backpacking.member.mail.util;

import com.backpacking.member.mail.dto.MailDto;
import org.springframework.mail.SimpleMailMessage;

public interface MailMaker {

    SimpleMailMessage generateMailContent(MailDto mailDto, String linkAddress);
    String generateLinkAddress(MailDto mailDto);


}
