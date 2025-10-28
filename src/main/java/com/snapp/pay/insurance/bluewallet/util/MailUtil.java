package com.snapp.pay.insurance.bluewallet.util;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class MailUtil {
    private final JavaMailSender sender;

    //TODO read subject and body from the messages
    @Async
    public void sendOtp(String mail, String otp) {
        String subject = "test";
        String text = "".concat(otp);

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("blue.farid.masjedi@gmail.com");
        message.setSubject(subject);
        message.setText(text);
        message.setTo(mail);

        log.info("otp for {} is {}", mail, otp);

//        sender.send(message);
    }
}
