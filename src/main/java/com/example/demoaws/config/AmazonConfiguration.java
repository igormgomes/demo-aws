package com.example.demoaws.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmazonConfiguration {

    private static final String ACCESS_KEY = "[chave de acesso]"; //replace
    private static final String SECRET_KEY = "[chave de segredo]"; //replace
    private static final String REGION = "us-east-1"; //replace

    @Bean
    public BasicAWSCredentials basicAWSCredentials() {
        return new BasicAWSCredentials(ACCESS_KEY, SECRET_KEY);
    }

    @Bean
    public AmazonS3 amazonS3() {
        return AmazonS3ClientBuilder.standard()
                .withRegion(REGION)
                .withCredentials(new AWSStaticCredentialsProvider(basicAWSCredentials()))
                .build();
    }
}