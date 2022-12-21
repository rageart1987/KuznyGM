package com.example.registrationlogindemo.service.impl;


import com.example.registrationlogindemo.dto.FormDto;
import com.example.registrationlogindemo.entity.Form;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;


import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Objects;

@Service
public class MailSenderService {

        @Autowired
        private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String email;


public void sendMail(String email, String subject, Form formDto) throws MessagingException{

    String body = "Номер подразделения :" + formDto.getUser().getSubdivision() + "\n"
            + "Vin : " + formDto.getVin() + "\n"
            + "Желаемая дата и время : " + formDto.getDate() + " "+ formDto.getTime() + "\n"
            + "1 : " + formDto.isCondition1() + "\n"
            + "2 : " + formDto.isCondition2() + "\n"
            + "3 : " + formDto.isCondition3() + "\n"
            + "4 : " + formDto.isCondition4() + "\n"
            + "5 : " + formDto.isCondition5() + "\n"
            + "6 : " + formDto.isCondition6() + "\n"
            + "Дополнительные данные : " + formDto.getDescription() + "\n"
            + "Город : " + formDto.getUser().getCity() + "\n"
            + "Пользователь: " + formDto.getUser().getFullName() + "\n"
            + "Почта пользователья: " + formDto.getUser().getEmail();

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

        mimeMessageHelper.setFrom(email);
        mimeMessageHelper.setText(body);
        mimeMessageHelper.setSubject(subject);

        mimeMessageHelper.setTo(email);
        mailSender.send(mimeMessage);
        }

}
