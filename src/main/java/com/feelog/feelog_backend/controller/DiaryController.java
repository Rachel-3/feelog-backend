package com.feelog.feelog_backend.controller;

import com.feelog.feelog_backend.model.Diary;
import com.feelog.feelog_backend.model.Emotions;
import com.feelog.feelog_backend.service.DiaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RestController
@RequestMapping("/diaries")
public class DiaryController {

    @Autowired
    private DiaryService diaryService;

    private static final Logger logger = LoggerFactory.getLogger(DiaryController.class);

    // 다이어리 작성
    @PostMapping
    public ResponseEntity<?> createDiary(@RequestBody Diary diary) {
        try {
            if (diary.getUser() == null || diary.getUser().getId() == null) {
                return ResponseEntity.badRequest().body("User 정보가 없습니다.");
            }
            Diary savedDiary = diaryService.saveDiary(diary, diary.getUser().getId());
            return ResponseEntity.ok(savedDiary);
        } catch (Exception e) {
            // 로깅 추가
            logger.error("Diary 생성 중 오류 발생", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Diary 생성 중 오류가 발생했습니다.");
        }
    }

    // 다이어리 삭제
    @DeleteMapping("/{diaryId}")
    public ResponseEntity<Void> deleteDiary(@PathVariable Integer diaryId) {
        diaryService.deleteDiary(diaryId);
        return ResponseEntity.ok().build();
    }

    // 다이어리 수정
    @PutMapping("/{diaryId}")
    public ResponseEntity<Diary> updateDiary(@PathVariable Integer diaryId, @RequestBody Diary diaryDetails) {
        Diary updatedDiary = diaryService.updateDiary(diaryId, diaryDetails);
        return ResponseEntity.ok(updatedDiary);
    }

    /// 모든 다이어리 목록을 반환하는 메소드
    @GetMapping
    public ResponseEntity<List<Diary>> getAllDiaries() {
        List<Diary> diaries = diaryService.getAllDiaries();
        return ResponseEntity.ok(diaries);
    }

    // 특정 사용자의 다이어리 목록을 반환하는 메소드
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Diary>> getDiariesByUser(@PathVariable Integer userId) {
        List<Diary> diaries = diaryService.getDiariesByUser(userId);
        return ResponseEntity.ok(diaries);
    }

    /// 감정 데이터 조회 엔드포인트
    @GetMapping("/{diaryId}/emotions")
    public ResponseEntity<?> getDiaryEmotions(@PathVariable Integer diaryId) {
        try {
            Emotions emotions = diaryService.getDiaryEmotions(diaryId);
            return ResponseEntity.ok(emotions);
        } catch (Exception e) {
            logger.error("감정 데이터 조회 중 오류 발생", e);
            // 타입을 ResponseEntity<?>로 변경하여 오류 메시지를 반환
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("감정 데이터 조회 중 오류가 발생했습니다.");
        }
    }



    // 기타 메소드들...
}
