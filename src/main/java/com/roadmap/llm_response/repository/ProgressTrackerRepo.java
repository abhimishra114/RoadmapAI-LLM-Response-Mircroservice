package com.roadmap.llm_response.repository;

import com.roadmap.llm_response.model.ProgressTrackers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProgressTrackerRepo extends JpaRepository<ProgressTrackers, Long> {

    Optional<ProgressTrackers> findByUserIdAndTopicId(Long userId, Long topicId);

    List<ProgressTrackers> findByUserIdAndTopicIdIn(Long userId, List<Long> topicIds);

    long countByUserIdAndTopicIdInAndIsCompletedTrue(Long userId, List<Long> topicIds);

}
