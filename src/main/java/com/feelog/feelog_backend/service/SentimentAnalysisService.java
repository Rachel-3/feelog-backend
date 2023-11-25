package com.feelog.feelog_backend.service;

import com.feelog.feelog_backend.model.SentimentAnalysisResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;
import org.json.JSONObject;

@Service
public class SentimentAnalysisService {

    @Value("${clova.api.key-id}")
    private String apiKeyId;

    @Value("${clova.api.secret}")
    private String apiSecret;

    @Value("${clova.api.url}")
    private String apiUrl;

    public SentimentAnalysisResult analyzeSentiment(String text) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("X-NCP-APIGW-API-KEY-ID", apiKeyId);
        headers.set("X-NCP-APIGW-API-KEY", apiSecret);

        JSONObject requestBody = new JSONObject();
        requestBody.put("content", text);

        HttpEntity<String> request = new HttpEntity<>(requestBody.toString(), headers);
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<SentimentAnalysisResult> response = restTemplate.postForEntity(apiUrl, request, SentimentAnalysisResult.class);
        return response.getBody();
    }
}
