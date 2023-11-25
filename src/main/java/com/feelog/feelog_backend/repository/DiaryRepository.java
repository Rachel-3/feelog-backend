package com.feelog.feelog_backend.repository;

import com.feelog.feelog_backend.model.Diary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiaryRepository extends JpaRepository<Diary, Integer> {
    // 필요한 JPA 메서드 추가
}
