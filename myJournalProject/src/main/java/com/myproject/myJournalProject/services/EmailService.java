package com.myproject.myJournalProject.services;

import org.springframework.stereotype.Service;

import com.myproject.myJournalProject.entity.EmailEntity;

@Service
public interface EmailService {

    // method to send a simple mail
    
    String simpleMail(EmailEntity emailEntity);
    

    // method to send an attachment mail

    String attachmentMail(EmailEntity emailEntity);


    
}
