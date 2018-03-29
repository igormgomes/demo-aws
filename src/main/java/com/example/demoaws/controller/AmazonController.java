package com.example.demoaws.controller;

import com.example.demoaws.service.AmazonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.logging.Logger;

@Controller
public class AmazonController {

    private Logger logger = Logger.getLogger(this.getClass().getName());

    private AmazonService amazonService;

    @Autowired
    public AmazonController(AmazonService amazonService) {
        this.amazonService = amazonService;
    }

    @PostMapping("/")
    public ResponseEntity upload (@RequestParam("file") MultipartFile file) {
        String upload = this.amazonService.upload(file);
        logger.info(upload);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/")
    public String listUploadedFiles(Model model){
        return "uploadForm";
    }
}
