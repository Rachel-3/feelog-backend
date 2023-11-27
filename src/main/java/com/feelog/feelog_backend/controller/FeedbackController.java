package com.feelog.feelog_backend.controller;

import com.feelog.feelog_backend.model.Feedback;
import com.feelog.feelog_backend.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/diaries/{diaryId}/feedback")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @GetMapping
    public ResponseEntity<FeedbackDto> getFeedback(@PathVariable Integer diaryId, @RequestParam String sentiment) {
        Feedback feedback = feedbackService.getFeedbackForDiary(diaryId, sentiment);
        if (feedback == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(new FeedbackDto(feedback.getFeedbackId(), feedback.getSentiment(), feedback.getFeedbackText()));
    }

    // ... 기타 코드 ...

    // Feedback 데이터를 전달하기 위한 DTO 클래스
    public static class FeedbackDto {
        private Long feedbackId;
        private String sentiment;
        private String feedbackText;

        public FeedbackDto(Long feedbackId, String sentiment, String feedbackText) {
            this.feedbackId = feedbackId;
            this.sentiment = sentiment;
            this.feedbackText = feedbackText;
        }

        // getter 및 setter
        // ...
        public Long getFeedbackId() {
            return feedbackId;
        }

        public void setFeedbackId(Long feedbackId) {
            this.feedbackId = feedbackId;
        }

        public String getSentiment() {
            return sentiment;
        }

        public void setSentiment(String sentiment) {
            this.sentiment = sentiment;
        }

        public String getFeedbackText() {
            return feedbackText;
        }

        public void setFeedbackText(String feedbackText) {
            this.feedbackText = feedbackText;
        }
    }
}

