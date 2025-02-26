package com.myproject.myJournalProject.services;

import com.myproject.myJournalProject.entity.JournalEntry;
import com.myproject.myJournalProject.entity.User;
import com.myproject.myJournalProject.repository.JournalEntryRepository;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;
    @Autowired
    private UserService userService;

// Post
    public void saveEntry(JournalEntry journalEntry, String username){
        User user = userService.findByUserName(username);
        journalEntry.setDate(LocalDateTime.now());
        JournalEntry newEntry = journalEntryRepository.save(journalEntry);
        user.getJournalEntries().add(newEntry);
        userService.updateUser(user);
        
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
    @Transactional
    public boolean deleteEntry(ObjectId id, String username){
        User user = userService.findByUserName(username);
        System.out.println(user + "     " + username);
        user.getJournalEntries().removeIf(x-> x.getId().equals(id));
        userService.createUser(user);
        journalEntryRepository.deleteById(id);
        return true;
    }



}



//  controller --> service --> repo -->