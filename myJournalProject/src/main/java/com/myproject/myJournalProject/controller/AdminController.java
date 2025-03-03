package com.myproject.myJournalProject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myproject.myJournalProject.entity.User;
import com.myproject.myJournalProject.services.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @GetMapping("/all-users")
    public ResponseEntity<?> getAllUsers() {
        System.out.println("All USERS called ------------------------------------->>>>>>>>");
        List<User> users = userService.getAll();
        if (!users.isEmpty()) {
            return new ResponseEntity<>(users, HttpStatus.FOUND);
        }
        return new ResponseEntity<>("No any users registered.", HttpStatus.NOT_FOUND);
    }

    
    @PostMapping("/create-admin-user")
    public ResponseEntity<?> postMethodName(@RequestBody User user) {
        try {
            userService.createAdmin(user);
            return new ResponseEntity<>("user created", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
    }



       
    
    
    
    
}
