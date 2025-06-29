package com.roadmap.llm_response.repository;

import com.roadmap.llm_response.model.LearningPaths;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LearningPathRepo extends JpaRepository<LearningPaths, Long> {


}
