package com.myproject.myJournalProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myproject.myJournalProject.entity.User;
import com.myproject.myJournalProject.services.UserService;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/public")
public class PublicController {
    
    @Autowired
    private UserService userService;
    @GetMapping("/health-check")
    public String getMethodName() {
        return "ok";
    }
    

        // Post --> add user
    @PostMapping("/create-user")
    public ResponseEntity<?> createUser(@RequestBody User user) {
        System.out.println("post check");
        try {
            userService.createUser(user);
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Already Registered User",HttpStatus.BAD_REQUEST);
        }
    }
 
}
