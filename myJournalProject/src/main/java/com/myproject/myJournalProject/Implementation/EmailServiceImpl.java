package com.myproject.myJournalProject.Implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import com.myproject.myJournalProject.entity.EmailEntity;
import com.myproject.myJournalProject.services.EmailService;

import lombok.Value;

public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender javaMailSender;
    private String sender = "mangeshs1134@gmail.com";

    // for simple text mail
    public String simpleMail(EmailEntity emailEntity){
        try {

            SimpleMailMessage mail = new SimpleMailMessage();
            mail.setFrom(sender);
            mail.setTo(emailEntity.getTo());
            mail.setText(emailEntity.getMsgBody());
            mail.setSubject(emailEntity.getSubject());

            javaMailSender.send(mail);
            return "mail sent";
            
        } catch (Exception e) {
            // throw new RuntimeException("Exception occurred while sending text mail", e);
            return "mail not sent";
        }
    }
    public String attachmentMail(EmailEntity emailEntity){
        return "mail sent";
    }
}
