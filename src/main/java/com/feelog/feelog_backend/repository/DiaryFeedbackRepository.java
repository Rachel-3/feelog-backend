package com.feelog.feelog_backend.repository;

import com.feelog.feelog_backend.model.DiaryFeedback;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface DiaryFeedbackRepository extends JpaRepository<DiaryFeedback, Integer> {
    Optional<DiaryFeedback> findByDiaryId(Integer diaryId);
}
