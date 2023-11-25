package com.feelog.feelog_backend.repository;

import com.feelog.feelog_backend.model.Diary;
import com.feelog.feelog_backend.model.Emotions;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmotionsRepository extends JpaRepository<Emotions, Integer> {
    // 필요한 메서드 정의 (현재는 기본적인 CRUD 작업만 포함)
    Optional<Emotions> findByDiary(Diary diary);
}
