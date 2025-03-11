package com.myproject.myJournalProject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.myproject.myJournalProject.entity.EmailEntity;

// @Service
public interface EmailService {

    // method to send a simple mail
    
    String simpleMail(EmailEntity emailEntity);
    

    // method to send a attachment mail

    String attachmentMail(EmailEntity emailEntity);







    
    // private final JavaMailSender javaMailSender;
    // @Autowired
    // public EmailService(JavaMailSender javaMailSender){
    //     this.javaMailSender = javaMailSender;

    // }

    // public String sendEmail(String to, String subject, String body){
    //     try {
    //         SimpleMailMessage mail = new SimpleMailMessage();
    //         mail.setSubject(subject);
    //         mail.setText(body);
    //         mail.setTo(to);
    //         javaMailSender.send(mail);
    //         return "mail sent";
            
    //     } catch (Exception e) {
    //         throw new RuntimeException("Failed to create email service bean", e);
    //     }

    // }
    
}
