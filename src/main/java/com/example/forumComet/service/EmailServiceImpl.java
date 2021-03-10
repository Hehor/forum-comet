package com.example.forumComet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl{
    @Autowired
    private JavaMailSender javaMailSender;
    public void sendMessage(String to,String subject, String text){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("forforumreg34@gmail.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        javaMailSender.send(message);
    }
}
