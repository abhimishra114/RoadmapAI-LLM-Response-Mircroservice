package com.roadmap.llm_response.repository;

import com.roadmap.llm_response.model.Topics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TopicsRepo extends JpaRepository<Topics, Long> {

    List<Topics> findByWeekId(long weekId);
}
