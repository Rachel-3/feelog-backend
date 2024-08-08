package com.feelog.feelog_backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "feedback")
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long feedbackId;

    private String sentiment;

    @Column(name = "feedback_text") // 데이터베이스의 컬럼 이름과 일치하도록 지정
    private String feedbackText; // 필드 이름 변경

    public Feedback() {}

    // getter 및 setter
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

    public String getFeedbackText() { // 메소드 이름 변경
        return feedbackText;
    }

    public void setFeedbackText(String feedbackText) { // 메소드 이름 변경
        this.feedbackText = feedbackText;
    }
}
