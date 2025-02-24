package com.myproject.myJournalProject.controller;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myproject.myJournalProject.entity.User;
import com.myproject.myJournalProject.services.UserService;
import org.springframework.security.core.Authentication;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;
//    auto converted to JSON


    // Get users
    @GetMapping
    public List<User> getAll() {
        return userService.getAll();
    }

    // Get one USER
    @GetMapping("/id/{userId}")
    public ResponseEntity<?> getUser(@PathVariable ObjectId userId){
        System.out.println(userService.findUser(userId));
        if (userService.findUser(userId)!=null) {
            try {
                return new ResponseEntity<>(userService.findUser(userId), HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }else{
            return new ResponseEntity<>("User not Found",HttpStatus.NOT_FOUND);

        }
    }


    
    //  DELETE USER
    @DeleteMapping("/id/{userId}")
    public boolean deleteUser(@RequestBody ObjectId userId){
        try {
            userService.deleteUser(userId);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // Put

    @PutMapping("/update")
    public ResponseEntity<?> updateUser(@RequestBody User user){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User userInDb = userService.findByUserName(username);
        if (userInDb!=null) {
            userInDb.setPassword(user.getPassword());
            userInDb.setUsername(user.getUsername());
            userService.createUser(userInDb);
        }
        return new ResponseEntity<>(userInDb, HttpStatus.CREATED);
    }
    
    
}
