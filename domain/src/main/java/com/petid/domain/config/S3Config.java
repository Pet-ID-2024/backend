package com.petid.domain.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import software.amazon.awssdk.auth.credentials.EnvironmentVariableCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;

@Configuration
public class S3Config {

    @Value("${spring.cloud.aws.region.static}")
    private String region;

    @Bean
    public S3Client s3Client() {
        return S3Client.builder()
                .credentialsProvider(EnvironmentVariableCredentialsProvider.create())
                .region(Region.AP_NORTHEAST_2)
                .build();
    }
    
    @Bean
    public S3Presigner presigner() {
        return S3Presigner.builder()
                .credentialsProvider(EnvironmentVariableCredentialsProvider.create())
                //.region(Region.of(region))
                .region(Region.AP_NORTHEAST_2)
                .build();
    }
   
}
 

