package com.myproject.myJournalProject.services;

import com.myproject.myJournalProject.entity.JournalEntry;
import com.myproject.myJournalProject.repository.JournalEntryRepository;
import com.myproject.myJournalProject.repository.UserRepository;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;
    @Autowired
    private UserRepository userRepository;

// Post
    public void saveEntry(JournalEntry journalEntry, String username){
        journalEntry.setDate(LocalDateTime.now());
        journalEntryRepository.save(journalEntry);
    }


// Get
    public List<JournalEntry> getAll(){
        return journalEntryRepository.findAll();
    }


//    Get One
    public Optional<JournalEntry> findById(ObjectId id){
        return journalEntryRepository.findById(id);
    }


//    Delete
    public boolean deleteEntry(ObjectId id){
        journalEntryRepository.deleteById(id);
        return true;
    }



}



//  controller --> service --> repo -->