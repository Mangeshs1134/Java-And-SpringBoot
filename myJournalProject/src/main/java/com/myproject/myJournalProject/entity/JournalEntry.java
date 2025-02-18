package com.myproject.myJournalProject.entity;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Date;

// POJO --> plain old java object
@Document(collection = "journal_entries")
public class JournalEntry {

    @Id
    private ObjectId id;
    private String title;
    private String content;
    private LocalDateTime date;

// Getter
    public LocalDateTime getDate() {
        return date;
    }
// Setter
    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    // getters declaration
    public ObjectId getId() {
        return id;
    }
    // getters declaration
    public String getTitle() {
        return title;
    }
    // getters declaration
    public String getContent() {
        return content;
    }
    // setters declaration
    public void setId(ObjectId id) {
        this.id = id;
    }
    // setters declaration
    public void setTitle(String title) {
        this.title = title;
    }
    // setters declaration
    public void setContent(String content) {
        this.content = content;
    }

}
