package com.petid.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@EnableJpaAuditing
@SpringBootApplication
@ComponentScan({"com.petid.infra", "com.petid.domain"})
@EntityScan("com.petid.infra")
@EnableJpaRepositories("com.petid.infra")
public class PetIdApplication {
    public static void main(String[] args) {
        SpringApplication.run(PetIdApplication.class, args);
    }
}