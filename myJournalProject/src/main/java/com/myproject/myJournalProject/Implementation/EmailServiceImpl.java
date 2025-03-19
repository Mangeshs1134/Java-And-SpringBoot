package com.myproject.myJournalProject.Implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import org.springframework.mail.javamail.JavaMailSender;

import com.myproject.myJournalProject.entity.EmailEntity;
import com.myproject.myJournalProject.services.EmailService;


@Service
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender javaMailSender;

    public EmailServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    // for simple text mail
    @Override
    public String simpleMail(EmailEntity emailEntity){
        try {
            
            SimpleMailMessage mail = new SimpleMailMessage();
            mail.setFrom("mangeshs1134@gmail.com");
            mail.setTo(emailEntity.getTo());
            mail.setText(emailEntity.getMsgBody());
            mail.setSubject(emailEntity.getSubject());
            
            javaMailSender.send(mail);
            return "mail sent";
            
        } catch (Exception e) {
//             throw new RuntimeException("Exception occurred while sending text mail", e);
            return "mail not sent";
//            return e;
        }
    }
    @Override
    public String attachmentMail(EmailEntity emailEntity){
        return "mail sent";
    }
}
