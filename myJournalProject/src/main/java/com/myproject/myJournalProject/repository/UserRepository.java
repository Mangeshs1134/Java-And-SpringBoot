package com.myproject.myJournalProject.repository;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.myproject.myJournalProject.entity.User;


public interface UserRepository extends MongoRepository<User, ObjectId> {
    

    User findByUsername(String username);
}
