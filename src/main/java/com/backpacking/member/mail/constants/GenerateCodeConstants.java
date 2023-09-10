package com.backpacking.member.mail.constants;

import org.springframework.stereotype.Component;

@Component
public final class GenerateCodeConstants {

    private GenerateCodeConstants(){}

    public static final int CODE_LENGTH = 10;
    public static final boolean USE_NUMBER = true;
    public static final boolean USE_LETTER = true;

}
