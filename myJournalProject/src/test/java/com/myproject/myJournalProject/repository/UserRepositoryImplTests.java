package com.myproject.myJournalProject.repository;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.myproject.myJournalProject.entity.User;

@SpringBootTest
public class UserRepositoryImplTests {

    @Autowired
    private UserRepositoryImpl userRepositoryImpl;

    @Test
    public void test(){
        List<User> response = userRepositoryImpl.getUserForSA();
        System.out.println(response);
    }
    
}
