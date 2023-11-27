package com.feelog.feelog_backend.repository;

import com.feelog.feelog_backend.model.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    List<Feedback> findBySentiment(String sentiment);
}
