package com.myproject.myJournalProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement //enables atomicity-- isolates --rollback 
public class MyJournalProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyJournalProjectApplication.class, args);
	}


	public PlatformTransactionManager M(MongoDatabaseFactory dbFactory){
		return new MongoTransactionManager(dbFactory);
	}

}
// Platform T Manager <- implements MongoTransactional Manager