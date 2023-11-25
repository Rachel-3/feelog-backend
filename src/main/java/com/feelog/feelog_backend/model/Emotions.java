package com.feelog.feelog_backend.model;

import jakarta.persistence.*;


@Entity
@Table(name = "emotions")
public class Emotions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "diary_id", referencedColumnName = "id")
    private Diary diary;

    private String overallSentiment;
    private Float neutralScore;
    private Float positiveScore;
    private Float negativeScore;

    // getters, setters...

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Diary getDiary() {
        return diary;
    }

    public void setDiary(Diary diary) {
        this.diary = diary;
    }

    public String getOverallSentiment() {
        return overallSentiment;
    }

    public void setOverallSentiment(String overallSentiment) {
        this.overallSentiment = overallSentiment;
    }

    public Float getNeutralScore() {
        return neutralScore;
    }

    public void setNeutralScore(Float neutralScore) {
        this.neutralScore = neutralScore;
    }

    public Float getPositiveScore() {
        return positiveScore;
    }

    public void setPositiveScore(Float positiveScore) {
        this.positiveScore = positiveScore;
    }

    public Float getNegativeScore() {
        return negativeScore;
    }

    public void setNegativeScore(Float negativeScore) {
        this.negativeScore = negativeScore;
    }
}
