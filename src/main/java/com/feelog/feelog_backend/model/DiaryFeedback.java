package com.feelog.feelog_backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "diary_feedback")
public class DiaryFeedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long diaryFeedbackId;

    @ManyToOne
    @JoinColumn(name = "diary_id")
    private Diary diary;

    @ManyToOne
    @JoinColumn(name = "feedback_id")
    private Feedback feedback;

    // ... 생성자, getter, setter

    public DiaryFeedback() {
    }

    public Long getDiaryFeedbackId() {
        return diaryFeedbackId;
    }

    public void setDiaryFeedbackId(Long diaryFeedbackId) {
        this.diaryFeedbackId = diaryFeedbackId;
    }

    public Diary getDiary() {
        return diary;
    }

    public void setDiary(Diary diary) {
        this.diary = diary;
    }

    public Feedback getFeedback() {
        return feedback;
    }

    public void setFeedback(Feedback feedback) {
        this.feedback = feedback;
    }
}
