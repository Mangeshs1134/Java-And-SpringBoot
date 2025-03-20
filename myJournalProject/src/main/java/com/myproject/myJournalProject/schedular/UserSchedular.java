package com.myproject.myJournalProject.schedular;

import com.myproject.myJournalProject.Implementation.EmailServiceImpl;
import com.myproject.myJournalProject.cache.AppCache;
import com.myproject.myJournalProject.entity.EmailEntity;
import com.myproject.myJournalProject.entity.JournalEntry;
import com.myproject.myJournalProject.entity.User;
import com.myproject.myJournalProject.enums.Sentiment;
import com.myproject.myJournalProject.repository.UserRepositoryImpl;
//import com.myproject.myJournalProject.services.SentimentAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class UserSchedular {

    @Autowired
    public EmailServiceImpl emailServiceImpl;
    @Autowired
    public UserRepositoryImpl userRepositoryImpl;
//    @Autowired
//    private SentimentAnalysisService sentimentAnalysisService;
    @Autowired
    private AppCache appCache;

    @Scheduled(cron = "0 0 9 * * SUN")
    public void fetchUsersAndSendSAMail(){
        List<User>  users = userRepositoryImpl.getUserForSA();
        for (User user : users){
            List <JournalEntry> journalEntries = user.getJournalEntries();
            List<Sentiment>  sentiments = journalEntries.stream().filter(x-> x.getDate().isAfter(LocalDateTime.now().minus(7, ChronoUnit.DAYS))).map(x->x.getSentiment()).collect(Collectors.toList());

            Map<Sentiment, Integer> sentimentCounts = new HashMap<>();
            for (Sentiment sentiment : sentiments){
                if (sentiment != null){
                    sentimentCounts.put(sentiment, sentimentCounts.getOrDefault(sentiment, 0) + 1);
                }
                Sentiment mostFrequesntSentiment = null;
                int maxCount = 0;
                for (Map.Entry<Sentiment, Integer> entry : sentimentCounts.entrySet()){
                    if (entry.getValue() > maxCount){
                        maxCount = entry.getValue();
                        mostFrequesntSentiment = entry.getKey();
                    }
                }
            if (mostFrequesntSentiment != null){
                EmailEntity emailEntity = new EmailEntity();
                emailEntity.setTo(user.getEmail());
                emailEntity.setSubject("Sentiment for last 7 days");
                emailEntity.setMsgBody(mostFrequesntSentiment.toString());
                emailServiceImpl.simpleMail(emailEntity);
            }
            }

//            String entry = String.join("", filteredEntries);
//            String sentiment = sentimentAnalysisService.getSentiment(entry);
//            EmailEntity emailEntity = new EmailEntity();
//            emailEntity.setTo(user.getEmail());
//            emailEntity.setSubject("Sentiment for last 7 days");
//            emailEntity.setMsgBody(sentiment);
//            emailServiceImpl.simpleMail(emailEntity);
        }
    }
    @Scheduled(cron = "0 0/1 * ? * *") //scheduled and will run after every 10 minutes
    public void clearAppCache(){
        System.out.println("Scheduling is working");
        appCache.init();
    }

}
