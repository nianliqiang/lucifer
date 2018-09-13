package com.lucifer.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

/**
 * Created by jonynian on 2018/8/28.
 */
public class MailUtil {

    @Autowired
    public JavaMailSender sender;

    public  void sendMail(String from , String to ,String subject ,String text)  {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        sender.send(message);
    }
}
