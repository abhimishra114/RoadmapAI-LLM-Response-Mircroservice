package com.roadmap.llm_response.repository;

import com.roadmap.llm_response.model.Weeks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WeeksRepo extends JpaRepository<Weeks, Long> {

    List<Weeks> findByLearningPathId(long learningPathId);
}
