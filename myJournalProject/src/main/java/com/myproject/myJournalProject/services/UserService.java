package com.myproject.myJournalProject.services;

import com.myproject.myJournalProject.entity.JournalEntry;
import com.myproject.myJournalProject.entity.User;
import com.myproject.myJournalProject.repository.UserRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    
// Get
    public List<User> getAll(){
        return userRepository.findAll();
    }

    
}
