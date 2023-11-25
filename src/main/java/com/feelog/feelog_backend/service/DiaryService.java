package com.feelog.feelog_backend.service;

import com.feelog.feelog_backend.model.Diary;
import com.feelog.feelog_backend.model.User;
import com.feelog.feelog_backend.repository.DiaryRepository;
import com.feelog.feelog_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiaryService {

    @Autowired
    private DiaryRepository diaryRepository;

    @Autowired
    private UserRepository userRepository;

    // 다이어리 작성
    public Diary saveDiary(Diary diary, Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다: " + userId));
        diary.setUser(user);
        return diaryRepository.save(diary);
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

        return diaryRepository.save(diary);
    }

    // 기타 서비스 메소드들...
}
