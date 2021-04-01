package com.example.demo.service;

public interface EmailService {
    void sendEmail(String to,String body,String topic);
    void sendHtmlEmail(String to,String body,String topic);
}
