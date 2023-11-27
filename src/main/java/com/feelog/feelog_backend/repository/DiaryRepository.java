package com.feelog.feelog_backend.repository;

import com.feelog.feelog_backend.model.Diary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DiaryRepository extends JpaRepository<Diary, Integer> {
    // 필요한 JPA 메서드 추가

    // 특정 사용자의 일기 목록을 가져오는 메소드
    List<Diary> findByUserId(Integer userId);
}
