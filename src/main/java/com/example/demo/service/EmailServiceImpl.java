package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl implements EmailService{
@Autowired
private JavaMailSender javaMailSender;

    @Override
    public void sendEmail(String to, String body, String topic) {
        System.out.println("Sending email");
        SimpleMailMessage simpleMailMessage=new SimpleMailMessage();
        simpleMailMessage.setFrom("goga.iremashvili2000@gmail.com");
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(topic);
        simpleMailMessage.setText(body);
        javaMailSender.send(simpleMailMessage);
        System.out.println("sent email...");
    }

    @Override
    public void sendHtmlEmail(String to, String body, String topic) {
        try{

            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            mimeMessage.setSubject(topic);
            MimeMessageHelper helper;
            helper=new MimeMessageHelper(mimeMessage,true);
            helper.setFrom("goga.iremashvili2000@gmail.com");
            helper.setTo(to);
            helper.setText(body,true);
            javaMailSender.send(mimeMessage);
            System.out.println("sending message");

        }catch (MessagingException ex){
            ex.getMessage();
        }
    }
}
