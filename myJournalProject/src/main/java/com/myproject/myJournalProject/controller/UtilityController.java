package com.myproject.myJournalProject.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

    @RestController
    @RequestMapping("/util/")
    public class UtilityController {

    @GetMapping
    public String   test() {
        return "util test";
    }


    
    
    }
