package com.petid;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
@EntityScan(basePackages = "com.petid.infra")
public class PetIdApplication {
    public static void main(String[] args) {
        SpringApplication.run(PetIdApplication.class, args);
    }
}