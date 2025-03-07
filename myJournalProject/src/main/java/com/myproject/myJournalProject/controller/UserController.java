package com.myproject.myJournalProject.controller;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myproject.myJournalProject.api_response.WeatherResponse;
import com.myproject.myJournalProject.entity.User;
import com.myproject.myJournalProject.services.UserService;
import com.myproject.myJournalProject.services.WeatherService;

import org.springframework.security.core.Authentication;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;
//    auto converted to JSON

    @Autowired
    private WeatherService weatherService;


    // Get users
    @GetMapping("/all-users")
    public List<User> getAllUser() {
        return userService.getAll();
    }
    // Get users
    @GetMapping("/test")
    public String   test() {
        return "TEST";
    }

    // Get one USER
    @GetMapping
    public ResponseEntity<?> getUser(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth!=null) {
            try {
                String gretting;
                String city = "Delhi";
                System.out.println("line 1 passed");
                WeatherResponse response = weatherService.getWeather(city);
                System.out.println("line 2 passed");
                if (response!=null) {
                    gretting = " from " + city + " - - Today weather feels like " + response.getCurrent().getFeelslike();
                    
                    System.out.println("line 3 passed");
                }
                else{
                    gretting = " Failed to fetch weather";

                }
                return new ResponseEntity<>("Hi " + auth.getName() + gretting + " THANK YOU     ******----*******" , HttpStatus.OK);

            } catch (Exception e) {
                return new ResponseEntity<>("Catch Called, Try Failed for User Get.", HttpStatus.NOT_FOUND);
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
