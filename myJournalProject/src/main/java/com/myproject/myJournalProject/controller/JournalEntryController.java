package com.myproject.myJournalProject.controller;

import com.myproject.myJournalProject.entity.JournalEntry;
import com.myproject.myJournalProject.services.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {
    private Map<ObjectId, JournalEntry> journalEntries = new HashMap<>();

    @Autowired
    private JournalEntryService journalEntryService;

// Get All
    @GetMapping
    public List<JournalEntry> getAll(){
        return journalEntryService.getAll();

//  Get Through Condition
    } @GetMapping("/id/{myId}")
    public JournalEntry getJournalEntryById(@PathVariable ObjectId myId){
        return journalEntryService.findById(myId).orElse(null);
    }

//    Post Request
    @PostMapping
    public boolean createEntry(@RequestBody JournalEntry myEntry){
        myEntry.setDate(LocalDateTime.now());
        journalEntryService.saveEntry(myEntry);
        return true;
    }

//    Put Request
    @PutMapping("/id/{myId}")
    public boolean putEntry(@PathVariable ObjectId myId, @RequestBody JournalEntry myEntry){
        myEntry.setDate(LocalDateTime.now());
        JournalEntry old = journalEntryService.findById(myId).orElse(null);
        if (old!=null){
            old.setTitle(myEntry.getTitle()!=null && !myEntry.getTitle().equals("") ? myEntry.getTitle() : old.getTitle());
            old.setContent(myEntry.getContent()!=null && !myEntry.getContent().equals("") ? myEntry.getContent() : old.getContent());
        }
        journalEntryService.saveEntry(old);
        return true;
    }
//    Delete Request
    @DeleteMapping("/id/{myId}")
    public boolean deleteEntry(@PathVariable ObjectId myId){
        journalEntryService.deleteEntry(myId);
        return true;
    }
}
