package com.feelog.feelog_backend.controller;

import com.feelog.feelog_backend.model.SentimentAnalysisResult;
import com.feelog.feelog_backend.service.SentimentAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/analyze-sentiment")
public class SentimentAnalysisController {

    @Autowired
    private SentimentAnalysisService sentimentAnalysisService;

    @PostMapping
    public ResponseEntity<SentimentAnalysisResult> analyzeSentiment(@RequestBody String text) {
        SentimentAnalysisResult result = sentimentAnalysisService.analyzeSentiment(text);
        return ResponseEntity.ok(result);
    }
}
