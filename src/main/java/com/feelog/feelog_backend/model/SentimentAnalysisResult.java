package com.feelog.feelog_backend.model;

import java.util.List;

public class SentimentAnalysisResult {
    private Document document;
    private List<Sentence> sentences;

    // Getters and Setters for document and sentences
    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public List<Sentence> getSentences() {
        return sentences;
    }

    public void setSentences(List<Sentence> sentences) {
        this.sentences = sentences;
    }

    // Document 내부 클래스
    public static class Document {
        private String sentiment;
        private Confidence confidence;

        // Getters and Setters for sentiment and confidence
        public String getSentiment() {
            return sentiment;
        }

        public void setSentiment(String sentiment) {
            this.sentiment = sentiment;
        }

        public Confidence getConfidence() {
            return confidence;
        }

        public void setConfidence(Confidence confidence) {
            this.confidence = confidence;
        }
    }

    // Sentence 내부 클래스
    public static class Sentence {
        private String content;
        private String sentiment;
        private Confidence confidence;

        // Getters and Setters for content, sentiment, and confidence
        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getSentiment() {
            return sentiment;
        }

        public void setSentiment(String sentiment) {
            this.sentiment = sentiment;
        }

        public Confidence getConfidence() {
            return confidence;
        }

        public void setConfidence(Confidence confidence) {
            this.confidence = confidence;
        }
    }

    // Confidence 내부 클래스
    public static class Confidence {
        private Float neutral;
        private Float positive;
        private Float negative;

        // Getters and Setters for neutral, positive, and negative
        public Float getNeutral() {
            return neutral;
        }

        public void setNeutral(Float neutral) {
            this.neutral = neutral;
        }

        public Float getPositive() {
            return positive;
        }

        public void setPositive(Float positive) {
            this.positive = positive;
        }

        public Float getNegative() {
            return negative;
        }

        public void setNegative(Float negative) {
            this.negative = negative;
        }
    }
}
