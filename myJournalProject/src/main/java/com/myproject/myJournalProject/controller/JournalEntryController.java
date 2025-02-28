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
import java.util.stream.Collectors;

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
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    String username = auth.getName();
    User user = userService.findByUserName(username);
    List<JournalEntry> list = user.getJournalEntries();
    if (list!=null && !list.isEmpty()) {
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    return new ResponseEntity<>( HttpStatus.NO_CONTENT);
    
}

//  Get Through Condition
@GetMapping("/journalId/{myId}")
public ResponseEntity<?> getJournalEntryById(@PathVariable ObjectId myId){
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    String username = auth.getName();
    User user = userService.findByUserName(username);
    List <JournalEntry> collect = user.getJournalEntries().stream().filter(x-> x.getId().equals(myId)).collect(Collectors.toList());
    if (!collect.isEmpty()) {
        // ObjectId collectId = collect.get(0).getId(); will be same as myId
        Optional<JournalEntry> journalEntry = journalEntryService.findById(myId);
        return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
    }
    return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
}



//    Post Request
    @PostMapping
    public ResponseEntity<?> createEntry(@RequestBody JournalEntry myEntry){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        try {
            journalEntryService.saveEntry(myEntry, username);
            return new ResponseEntity<>(myEntry, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }



// //    Put Request
    @PutMapping("/journalId/{myId}")
    public ResponseEntity<?> putEntry(@PathVariable ObjectId myId, @RequestBody JournalEntry myEntry){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User user = userService.findByUserName(username);

        
        List <JournalEntry> collect = user.getJournalEntries().stream().filter(x-> x.getId().equals(myId)).collect(Collectors.toList());

        if (!collect.isEmpty()) {
            myEntry.setDate(LocalDateTime.now());
            JournalEntry old = journalEntryService.findById(myId).orElse(null);
            
            if (old!=null){
                old.setTitle(myEntry.getTitle()!=null && !myEntry.getTitle().equals("") ? myEntry.getTitle() : old.getTitle());
                old.setContent(myEntry.getContent()!=null && !myEntry.getContent().equals("") ? myEntry.getContent() : old.getContent());
                journalEntryService.saveEntry(old, username);
                return new ResponseEntity<>(collect, HttpStatus.CREATED);
            }  
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);  
    }



//    Delete Request
    @DeleteMapping("/journalId/{myId}")
    public ResponseEntity<?> deleteEntry(@PathVariable ObjectId myId){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        System.out.println("username"+ username);
        User user = userService.findByUserName(username);
        System.out.println("user" + user);
        List <JournalEntry> collect = user.getJournalEntries().stream().filter(x-> x.getId().equals(myId)).collect(Collectors.toList());
        System.out.println("collect" + collect);
        // List <JournalEntry> collect = user.getJournalEntries().stream().filter(x-> x.getId().equals(myId)).collect(Collectors.toList());
        if (!collect.isEmpty()) {
            journalEntryService.deleteEntry(myId, username);
            return new ResponseEntity<>("Deleted",HttpStatus.ACCEPTED);
        }
        // return new ResponseEntity<>("UnSuccess for delete",HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(collect,HttpStatus.NOT_FOUND);
    }
    
}
