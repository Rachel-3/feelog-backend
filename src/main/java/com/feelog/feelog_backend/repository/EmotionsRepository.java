package com.feelog.feelog_backend.repository;

import com.feelog.feelog_backend.model.Diary;
import com.feelog.feelog_backend.model.Emotions;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmotionsRepository extends JpaRepository<Emotions, Integer> {
    Optional<Emotions> findByDiary(Diary diary);
}
