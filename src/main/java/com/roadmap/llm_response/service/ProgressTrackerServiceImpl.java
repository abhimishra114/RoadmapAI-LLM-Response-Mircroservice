package com.roadmap.llm_response.service;

import com.roadmap.llm_response.model.ProgressTrackers;
import com.roadmap.llm_response.model.Topics;
import com.roadmap.llm_response.model.Weeks;
import com.roadmap.llm_response.repository.ProgressTrackerRepo;
import com.roadmap.llm_response.repository.TopicsRepo;
import com.roadmap.llm_response.repository.WeeksRepo;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ProgressTrackerServiceImpl implements ProgressTrackerService{

    private final ProgressTrackerRepo progressTrackerRepo;
    private final WeeksRepo weeksRepo;
    private final TopicsRepo topicsRepo;

    public ProgressTrackerServiceImpl(ProgressTrackerRepo progressTrackerRepo, WeeksRepo weeksRepo, TopicsRepo topicsRepo) {
        this.progressTrackerRepo = progressTrackerRepo;
        this.weeksRepo = weeksRepo;
        this.topicsRepo = topicsRepo;
    }

    @Override
    public void markTopicCompleted(Long userId, Long topicId) {
        ProgressTrackers tracker = progressTrackerRepo.findByUserIdAndTopicId(userId,topicId)
                .orElse(new ProgressTrackers());
        tracker.setUserId(userId);
        tracker.setTopicId(topicId);
        tracker.setCompleted(true);
        tracker.setCompletedAt(LocalDateTime.now());

        progressTrackerRepo.save(tracker);
    }

    @Override
    public void unmarkTopicCompleted(Long userId, Long topicId) {
        progressTrackerRepo.findByUserIdAndTopicId(userId, topicId)
                .ifPresent(tracker -> {
                    tracker.setCompleted(false);
                    tracker.setCompletedAt(null);
                    progressTrackerRepo.save(tracker);
                });
    }

    @Override
    public boolean isTopicCompleted(Long userId, Long topicId) {
        return progressTrackerRepo.findByUserIdAndTopicId(userId, topicId)
                .map(ProgressTrackers::isCompleted)
                .orElse(false);
    }

    @Override
    public Map<Long, Boolean> getCompletionStatusForUser(Long userId, List<Long> topicIds) {
        List<ProgressTrackers> trackers = progressTrackerRepo.findByUserIdAndTopicIdIn(userId, topicIds);
        return trackers.stream()
                .collect(Collectors.toMap(
                        ProgressTrackers::getTopicId,
                        ProgressTrackers::isCompleted
                ));

    }

    @Override
    public double getProgressPercentageForLearningPath(Long userId, Long learningPathId) {
        List<Weeks> weeks = weeksRepo.findByLearningPathId(learningPathId);
        List<Long> topicIds = weeks.stream()
                .flatMap(week -> topicsRepo.findByWeekId(week.getId()).stream())
                .map(Topics::getId)
                .toList();
        long totalTopics = topicIds.size();
        if (totalTopics == 0) {
            return 0.0; // No topics to track progress
        }
        long completedCount = progressTrackerRepo.countByUserIdAndTopicIdInAndIsCompletedTrue(userId, topicIds);
        return (completedCount * 100.0) / totalTopics;
    }
}
