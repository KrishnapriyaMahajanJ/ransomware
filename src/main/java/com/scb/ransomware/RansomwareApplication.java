package com.scb.ransomware;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackages = "com.scb.ransomware.repository")
public class RansomwareApplication {

    public static void main(String[] args) {
        SpringApplication.run(RansomwareApplication.class, args);
    }
}
