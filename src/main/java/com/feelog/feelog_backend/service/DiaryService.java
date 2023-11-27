package com.feelog.feelog_backend.service;

import com.feelog.feelog_backend.model.Diary;
import com.feelog.feelog_backend.model.Emotions;
import com.feelog.feelog_backend.model.SentimentAnalysisResult;
import com.feelog.feelog_backend.model.User;
import com.feelog.feelog_backend.repository.DiaryRepository;
import com.feelog.feelog_backend.repository.EmotionsRepository;
import com.feelog.feelog_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiaryService {

    @Autowired
    private DiaryRepository diaryRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SentimentAnalysisService sentimentAnalysisService;

    @Autowired
    private EmotionsRepository emotionsRepository;

    // 다이어리 작성
    public Diary saveDiary(Diary diary, Integer userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다: " + userId));
        diary.setUser(user);

        Diary savedDiary = diaryRepository.save(diary);

        // 감정 분석 수행
        SentimentAnalysisResult sentimentResult = sentimentAnalysisService.analyzeSentiment(diary.getContent());

        // 감정 분석 결과를 기반으로 Emotions 객체 생성 및 저장
        Emotions emotions = new Emotions();
        emotions.setDiary(savedDiary); // savedDiary는 저장된 Diary 객체
        emotions.setOverallSentiment(sentimentResult.getDocument().getSentiment());
        emotions.setNeutralScore(sentimentResult.getDocument().getConfidence().getNeutral());
        emotions.setPositiveScore(sentimentResult.getDocument().getConfidence().getPositive());
        emotions.setNegativeScore(sentimentResult.getDocument().getConfidence().getNegative());

        emotionsRepository.save(emotions);

        return savedDiary;
    }

    // 다이어리 삭제
    public void deleteDiary(Integer diaryId) {
        diaryRepository.deleteById(diaryId);
    }


    // 다이어리 수정
    public Diary updateDiary(Integer diaryId, Diary diaryDetails) {
        Diary diary = diaryRepository.findById(diaryId)
                .orElseThrow(() -> new RuntimeException("일기를 찾을 수 없습니다: " + diaryId));

        diary.setTitle(diaryDetails.getTitle());
        diary.setContent(diaryDetails.getContent());
        // 필요한 경우 다른 필드도 수정

        // 감정 분석 수행
        SentimentAnalysisResult sentimentResult = sentimentAnalysisService.analyzeSentiment(diary.getContent());

        // 기존 Emotions 객체를 찾거나 새로 생성
        Emotions emotions = emotionsRepository.findByDiary(diary)
                .orElse(new Emotions());
        emotions.setDiary(diary);
        emotions.setOverallSentiment(sentimentResult.getDocument().getSentiment());
        emotions.setNeutralScore(sentimentResult.getDocument().getConfidence().getNeutral());
        emotions.setPositiveScore(sentimentResult.getDocument().getConfidence().getPositive());
        emotions.setNegativeScore(sentimentResult.getDocument().getConfidence().getNegative());

        // Emotions 객체 업데이트
        emotionsRepository.save(emotions);

        return diaryRepository.save(diary);
    }


    // 모든 다이어리를 반환하는 메소드
    public List<Diary> getAllDiaries() {
        return diaryRepository.findAll();
    }

    // 특정 사용자의 다이어리를 반환하는 메소드
    public List<Diary> getDiariesByUser(Integer userId) {
        return diaryRepository.findByUserId(userId);
    }
    // 기타 서비스 메소드들...
}
