package com.myproject.myJournalProject.controller;

import com.myproject.myJournalProject.entity.JournalEntry;
import com.myproject.myJournalProject.entity.User;
import com.myproject.myJournalProject.services.JournalEntryService;
import com.myproject.myJournalProject.services.UserService;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    @Autowired
    private JournalEntryService journalEntryService;
    @Autowired
    private UserService userService;


// Get All
    @GetMapping("/{username}")
    public ResponseEntity<?> getAllJournalEntriesByUser(@PathVariable String username ){
    User user = userService.findByUserName(username);
    List<JournalEntry> list = user.getJournalEntries();
    return new ResponseEntity<>(list, HttpStatus.OK);
    
    
    
    //  Get Through Condition
} @GetMapping("/id/{myId}")
User user = userService.findByUserName(username);
    public ResponseEntity<JournalEntry> getJournalEntryById(@PathVariable ObjectId myId){
         Optional<JournalEntry> journalEntry = journalEntryService.findById(myId);
         return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
    }



//    Post Request
    @PostMapping("/{username}")
    public ResponseEntity<?> createEntry(@RequestBody JournalEntry myEntry, @PathVariable String username){
        try {
            journalEntryService.saveEntry(myEntry, username);
            return new ResponseEntity<>(myEntry, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }



//    Put Request
    @PutMapping("/id/{myId}")
    public ResponseEntity<?> putEntry(@PathVariable ObjectId myId, @RequestBody JournalEntry myEntry){
        myEntry.setDate(LocalDateTime.now());
        JournalEntry old = journalEntryService.findById(myId).orElse(null);
        if (old!=null){
            old.setTitle(myEntry.getTitle()!=null && !myEntry.getTitle().equals("") ? myEntry.getTitle() : old.getTitle());
            old.setContent(myEntry.getContent()!=null && !myEntry.getContent().equals("") ? myEntry.getContent() : old.getContent());
            journalEntryService.saveEntry(old);
            return new ResponseEntity<>(old, HttpStatus.CREATED);
        }  
            System.out.println("Here we reached");
            return new ResponseEntity<>(HttpStatus.OK); 
    }



//    Delete Request
    @DeleteMapping("/id/{myId}")
    public ResponseEntity<?> deleteEntry(@PathVariable ObjectId myId){
        journalEntryService.deleteEntry(myId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
}
