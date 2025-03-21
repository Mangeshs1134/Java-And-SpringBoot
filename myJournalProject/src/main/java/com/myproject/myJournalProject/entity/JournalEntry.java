package com.myproject.myJournalProject.entity;

import com.myproject.myJournalProject.enums.Sentiment;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NonNull ;

import java.time.LocalDateTime;

// POJO --> plain old java object
@Document(collection = "journal_entries")
@Data

public class JournalEntry {

    @Id
    private ObjectId id;

    @NonNull
    private String title;

    private String content;
    
    private LocalDateTime date;

    private Sentiment sentiment;

    

}
