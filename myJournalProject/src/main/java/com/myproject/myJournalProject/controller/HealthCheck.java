package com.myproject.myJournalProject.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheck {
//    auto converted to JSON
    @GetMapping("/health-check")
    public String healthCheck(){
        return "Check";
    }
}
