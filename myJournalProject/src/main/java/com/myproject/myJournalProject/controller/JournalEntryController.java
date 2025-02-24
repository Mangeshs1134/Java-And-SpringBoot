package com.myproject.myJournalProject.controller;

import com.myproject.myJournalProject.entity.JournalEntry;
import com.myproject.myJournalProject.entity.User;
import com.myproject.myJournalProject.services.JournalEntryService;
import com.myproject.myJournalProject.services.UserService;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    @GetMapping
    public ResponseEntity<?> getAllJournalEntriesByUser(){
        System.out.println("POST REQ");
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    String username = auth.getName();
    User user = userService.findByUserName(username);
    List<JournalEntry> list = user.getJournalEntries();
    if (list!=null && !list.isEmpty()) {
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    return new ResponseEntity<>( HttpStatus.NO_CONTENT);



//  Get Through Condition
    } @GetMapping("/id/{myId}")
    public ResponseEntity<JournalEntry> getJournalEntryById(@PathVariable ObjectId myId){
         Optional<JournalEntry> journalEntry = journalEntryService.findById(myId);
         return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
    }



//    Post Request
    @PostMapping
    public ResponseEntity<?> createEntry(@RequestBody JournalEntry myEntry){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        System.out.println("POST REQ");
        try {
            journalEntryService.saveEntry(myEntry, username);
            return new ResponseEntity<>(myEntry, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }



// //    Put Request
    @PutMapping("/{username}/{myId}")
    public ResponseEntity<?> putEntry(@PathVariable ObjectId myId, @PathVariable String username, @RequestBody JournalEntry myEntry){
        myEntry.setDate(LocalDateTime.now());
        JournalEntry old = journalEntryService.findById(myId).orElse(null);
        if (old!=null){
            old.setTitle(myEntry.getTitle()!=null && !myEntry.getTitle().equals("") ? myEntry.getTitle() : old.getTitle());
            old.setContent(myEntry.getContent()!=null && !myEntry.getContent().equals("") ? myEntry.getContent() : old.getContent());
            journalEntryService.saveEntry(old, username);
            return new ResponseEntity<>(old, HttpStatus.CREATED);
        }  
            System.out.println("Here we reached");
            return new ResponseEntity<>(HttpStatus.OK); 
    }



//    Delete Request
    @DeleteMapping("/id/{username}/{myId}")
    public ResponseEntity<?> deleteEntry(@PathVariable ObjectId myId, @PathVariable String username){
        System.out.println("Entry");
        journalEntryService.deleteEntry(myId, username);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
}
