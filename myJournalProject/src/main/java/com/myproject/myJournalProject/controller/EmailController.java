package com.myproject.myJournalProject.controller;


import com.myproject.myJournalProject.Implementation.EmailServiceImpl;
import com.myproject.myJournalProject.entity.EmailEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {

    @Autowired
    private EmailServiceImpl emailServiceImpl;

    @PostMapping("/senMail")
    public String senMail(@RequestBody EmailEntity emailEntity){
        System.out.println("reached mere");
        String status = emailServiceImpl.simpleMail(emailEntity);
        return status;
    }

}
