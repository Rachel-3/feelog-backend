package com.feelog.feelog_backend.service;


import com.feelog.feelog_backend.model.Diary;
import com.feelog.feelog_backend.model.Feedback;
import com.feelog.feelog_backend.model.DiaryFeedback;
import com.feelog.feelog_backend.repository.DiaryRepository;
import com.feelog.feelog_backend.repository.FeedbackRepository;
import com.feelog.feelog_backend.repository.DiaryFeedbackRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Autowired
    private DiaryFeedbackRepository diaryFeedbackRepository;

    @Autowired
    private DiaryRepository diaryRepository;

    @Transactional
    // 감정에 따른 피드백 저장
    public Feedback getFeedbackForDiary(Integer diaryId, String highestEmotion) {
        // 이미 매핑된 피드백이 있는지 확인
        DiaryFeedback diaryFeedback = diaryFeedbackRepository.findByDiaryId(diaryId).orElse(null);
        if (diaryFeedback != null) {
            // 이미 매핑된 피드백이 있으면 반환
            return diaryFeedback.getFeedback();
        } else {
            // 해당 감정과 일치하는 모든 피드백을 데이터베이스에서 가져옴
            List<Feedback> matchingFeedbacks = feedbackRepository.findBySentiment(highestEmotion);
            if (matchingFeedbacks.isEmpty()) {
                // 일치하는 피드백이 없으면 null 반환
                return null;
            }
            // 랜덤 피드백 선택
            Feedback randomFeedback = matchingFeedbacks.get(new Random().nextInt(matchingFeedbacks.size()));

            // 새 DiaryFeedback 객체 생성 및 저장
            DiaryFeedback newDiaryFeedback = new DiaryFeedback();
            Diary diary = diaryRepository.getReferenceById(diaryId);
            newDiaryFeedback.setDiary(diary);
            newDiaryFeedback.setFeedback(randomFeedback);
            diaryFeedbackRepository.save(newDiaryFeedback);

            return randomFeedback;
        }
    }
}
