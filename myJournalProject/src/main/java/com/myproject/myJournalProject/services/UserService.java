package com.myproject.myJournalProject.services;

import com.myproject.myJournalProject.entity.User;
import com.myproject.myJournalProject.repository.UserRepository;

import java.util.List;
import java.util.Optional;


import org.bson.types.ObjectId;
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


//  POST - ADD USER
    public void createUser(User user){
        userRepository.save(user);
    }

//  Find User
    public Optional<User> findUser(ObjectId userId){
        return userRepository.findById(userId);
}
    
//  DELETE USER
    public boolean deleteUser(ObjectId userId){
        userRepository.deleteById(userId);
        return true;
    }

    // Get user
    public User findByUserName(String username){
        return userRepository.findByUsername(username);
    }

    
}
