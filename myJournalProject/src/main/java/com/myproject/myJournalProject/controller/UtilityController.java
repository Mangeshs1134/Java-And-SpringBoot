package com.myproject.myJournalProject.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/test/")
public class UtilityController {

    @Autowired

    // @Autowired
    // private UserRepositoryImpl userRepositoryImpl;
    // @Autowired
    // private EmailService emailService;

    // @GetMapping("/sa")
    // public ResponseEntity<?> getMethodName() {
    //     String response =   emailService.sendEmail("mangesh195524@gmail.com", "subject", "body of email");
        
    //     return new ResponseEntity<>( response  , HttpStatus.FOUND);
    // }
    
    
    
}
