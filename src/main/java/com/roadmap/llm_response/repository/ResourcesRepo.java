package com.roadmap.llm_response.repository;

import com.roadmap.llm_response.model.Resources;
import com.roadmap.llm_response.model.Weeks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResourcesRepo extends JpaRepository<Resources, Long> {

    List<Resources> findByWeekId(long weekId);
}
