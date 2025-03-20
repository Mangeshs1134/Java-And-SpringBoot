package com.myproject.myJournalProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableTransactionManagement //enables atomicity-- isolates --rollback
@EnableScheduling //scheduling through cron expression
public class MyJournalProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyJournalProjectApplication.class, args);
	}

	@Bean
	public PlatformTransactionManager M(MongoDatabaseFactory dbFactory){
		return new MongoTransactionManager(dbFactory);
	}
	@Bean
	public RestTemplate restTemplate(){
		return new RestTemplate(); 
	}

}
// Platform T Manager <- implements MongoTransactional Manager