package com.myproject.myJournalProject.schedular;

import com.myproject.myJournalProject.Implementation.EmailServiceImpl;
import com.myproject.myJournalProject.entity.JournalEntry;
import com.myproject.myJournalProject.entity.User;
import com.myproject.myJournalProject.repository.UserRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserSchedular {

    @Autowired
    public EmailServiceImpl emailServiceImpl;
    @Autowired
    public UserRepositoryImpl userRepositoryImpl;

    public void fetchUsersAndSendSAMail(){
        List<User>  users = userRepositoryImpl.getUserForSA();
        for (User user : users){
            List <JournalEntry> journalEntries = user.getJournalEntries();
            List<String> filtered = journalEntries.stream().filter(x-> x.getDate().isAfter(LocalDateTime.now().minus(7, ChronoUnit.DAYS))).map(x->x.getContent()).collect(Collectors.toList());
        }
    }

}
