package com.roadmap.llm_response.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.roadmap.llm_response.dto.RoadmapResponse;
import com.roadmap.llm_response.model.LearningPaths;
import com.roadmap.llm_response.service.LearningPathService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/learning-path")
public class LearningPathController {
    private final LearningPathService learningPathService;

    public LearningPathController(LearningPathService learningPathService) {
        this.learningPathService = learningPathService;
    }

    @PostMapping("/save-learning-path/{userId}")
    public ResponseEntity<LearningPaths> saveLearningPathToDatabase(@PathVariable long userId,
                                                                    @RequestBody RoadmapResponse response) {

        try {
            String rawJson = new ObjectMapper().writeValueAsString(response);
            LearningPaths learningPath = learningPathService.createLearningPathFromResponse(userId, response, rawJson);
            LearningPaths savedLearningPath = learningPathService.saveLearningPath(learningPath);

            return ResponseEntity.ok(savedLearningPath);
        } catch (JsonProcessingException e) {
            return ResponseEntity.status(500).body(null);
        }
    }


}
