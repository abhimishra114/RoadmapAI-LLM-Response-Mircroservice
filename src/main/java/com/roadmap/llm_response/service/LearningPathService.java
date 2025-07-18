package com.roadmap.llm_response.service;

import com.roadmap.llm_response.dto.RoadmapResponse;
import com.roadmap.llm_response.model.LearningPaths;
import com.roadmap.llm_response.repository.LearningPathRepo;
import org.springframework.stereotype.Service;

@Service
public class LearningPathService {

    private final LearningPathRepo learningPathRepo;

    public LearningPathService(LearningPathRepo learningPathRepo) {
        this.learningPathRepo = learningPathRepo;
    }

    // This service is responsible for creating a LearningPaths object from a RoadmapResponse.
    public LearningPaths createLearningPathFromResponse(long userId, RoadmapResponse response, String rawResponse) {

        if (response == null || response.getData() == null) {
            throw new IllegalArgumentException("Invalid roadmap response data");
        }
        if (response.getData().getGoal() == null || response.getData().getDuration_weeks() <= 0) {
            throw new IllegalArgumentException("Goal or duration weeks are not properly defined in the response");
        }
        if (userId <= 0) {
            throw new IllegalArgumentException("Invalid user ID");
        }

        LearningPaths learningPath = new LearningPaths();
        learningPath.setUserId(userId);
        learningPath.setTitle(response.getData().getGoal());
        learningPath.setGoalDescription(response.getMessage());
        learningPath.setDurationWeeks(response.getData().getDuration_weeks());
        learningPath.setGeneratedByAI(true);
        learningPath.setRawResponse(rawResponse);

        return learningPath;
    }

    // This method will save the LearningPaths object to the database.
    public LearningPaths saveLearningPath(LearningPaths learningPaths){

        LearningPaths savedLearningPath = learningPathRepo.save(learningPaths);
        return savedLearningPath;
    }


    public LearningPaths getLearningPathById(long learningPathId) {
        return learningPathRepo.findById(learningPathId).orElseThrow(() ->
            new IllegalArgumentException("Learning path with ID " + learningPathId + " not found"));
    }
}
