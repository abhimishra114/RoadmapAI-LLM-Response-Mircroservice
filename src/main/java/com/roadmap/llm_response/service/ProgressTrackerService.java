package com.roadmap.llm_response.service;

import java.util.List;
import java.util.Map;

public interface ProgressTrackerService {
    void markTopicCompleted(Long userId, Long topicId);
    void unmarkTopicCompleted(Long userId, Long topicId);
    boolean isTopicCompleted(Long userId, Long topicId);
    Map<Long,Boolean> getCompletionStatusForUser(Long userId, List<Long> topicIds);
    double getProgressPercentageForLearningPath(Long userId, Long learningPathId);
}
