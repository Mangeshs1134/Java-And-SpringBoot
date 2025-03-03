package com.myproject.myJournalProject.services;

import com.myproject.myJournalProject.entity.User;
import com.myproject.myJournalProject.repository.UserRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;


import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserService {

    public static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder() ; 
    
    @Autowired
    private UserRepository userRepository;

    private static final Logger logger = LoggerFactory.getLogger(JournalEntryService.class);

    
// Get
    public List<User> getAll(){
        return userRepository.findAll();
    }


//  POST - ADD USER
    public void createNewUser(User user){
        userRepository.save(user);
    }
    
    //  CREATE NEW USER
    public void createUser(User user){
        // user.setPassword(passwordEncoder.encode(user.getPassword()));
        if (!user.getPassword().startsWith("$2a$")) {  // Check if already hashed
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        user.setRoles(Arrays.asList("USER"));
        userRepository.save(user);
    }
    
    //  CREATE NEW Admin
    public void createAdmin(User user){
        // user.setPassword(passwordEncoder.encode(user.getPassword()));
        if (!user.getPassword().startsWith("$2a$")) {  // Check if already hashed
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        user.setRoles(Arrays.asList("USER", "ADMIN"));
        userRepository.save(user);
    }
    public void updateUser(User user){
        // user.setPassword(passwordEncoder.encode(user.getPassword()));
        // user.setRoles(Arrays.asList("USER"));
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
