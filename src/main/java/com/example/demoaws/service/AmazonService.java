package com.example.demoaws.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class AmazonService {

    private static final String BUCKET = "[NOME DO BUCKET]"; //replace

    private AmazonS3 amazonS3;

    @Autowired
    public AmazonService(AmazonS3 amazonS3) {
        this.amazonS3 = amazonS3;
    }

    public String upload(MultipartFile file) {
        try {
            PutObjectRequest putObjectRequest = new PutObjectRequest(BUCKET,
                    file.getOriginalFilename(),
                    file.getInputStream(),
                    null);
            putObjectRequest.withCannedAcl(CannedAccessControlList.PublicRead);
            amazonS3.putObject(putObjectRequest);

            return "http://s3.amazonaws.com/" + BUCKET + "/" + file.getOriginalFilename();
        } catch (IOException e) {
            throw new RuntimeException("Não foi possível fazer o upload");
        }
    }
}