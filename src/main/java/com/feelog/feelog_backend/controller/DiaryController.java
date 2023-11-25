package com.feelog.feelog_backend.controller;

import com.feelog.feelog_backend.model.Diary;
import com.feelog.feelog_backend.service.DiaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/diaries")
public class DiaryController {

    @Autowired
    private DiaryService diaryService;

    // 다이어리 작성
    @PostMapping
    public ResponseEntity<Diary> createDiary(@RequestBody Diary diary) {
        // User ID를 사용하여 User 객체를 가져오고, Diary 객체에 설정
        Diary savedDiary = diaryService.saveDiary(diary, diary.getUser().getId());
        return ResponseEntity.ok(savedDiary);
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

    // 기타 메소드들...
}
