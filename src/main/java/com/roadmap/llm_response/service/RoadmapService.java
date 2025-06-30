package com.roadmap.llm_response.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.roadmap.llm_response.dto.*;
import com.roadmap.llm_response.model.LearningPaths;
import com.roadmap.llm_response.model.Resources;
import com.roadmap.llm_response.model.Topics;
import com.roadmap.llm_response.model.Weeks;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class RoadmapService {
    private final LearningPathService learningPathService;
    private final WeeksService weeksService;
    private final TopicsService topicsService;
    private final ResourcesService resourcesService;

    public RoadmapService(LearningPathService learningPathService, WeeksService weeksService, TopicsService topicsService, ResourcesService resourcesService) {
        this.learningPathService = learningPathService;
        this.weeksService = weeksService;
        this.topicsService = topicsService;
        this.resourcesService = resourcesService;
    }


    @Transactional
    public RoadmapDTO saveRoadmap(long userId, RoadmapResponse response) throws JsonProcessingException {
        try {
            String rawJson = new ObjectMapper().writeValueAsString(response);
            LearningPaths path = learningPathService.createLearningPathFromResponse(userId, response, rawJson);
            LearningPaths savedPath = learningPathService.saveLearningPath(path);

            List<WeekDTO> weekDTOs = response.getData().getWeeks();
            List<Weeks> savedWeeks = new ArrayList<>();

            // Save weeks and corresponding topics/resources
            for (WeekDTO weekDTO : weekDTOs) {
                Weeks week = new Weeks();
                week.setLearningPathId(savedPath.getId());
                week.setWeekNumber(weekDTO.getWeek());
                week.setTitle(weekDTO.getWeek_title());
                Weeks savedWeek = weeksService.saveWeek(week); // Save individually

                // Create & save topics
                List<Topics> topics = topicsService.createTopicsFromResponse(savedWeek.getId(), weekDTO.getTopics());
                topicsService.saveTopics(topics);

                // Create & save resources
                List<Resources> resources = resourcesService.createResourcesFromResponse(savedWeek.getId(), weekDTO.getResources());
                resourcesService.saveResources(resources);

                savedWeeks.add(savedWeek);
            }

            RoadmapDTO roadmapDTO = new RoadmapDTO();
            roadmapDTO.setLearningPathId(savedPath.getId());
            roadmapDTO.setData(response.getData());
            roadmapDTO.setMessage(response.getMessage());

            return roadmapDTO;
        } catch (Exception e) {
            log.error("Error saving roadmap: {}", e.getMessage(), e);
            throw new RuntimeException("Error saving roadmap: " + e.getMessage(), e);
        }
    }

    public RoadmapDTO getFullRoadmapByLearningPathId(long learningPathId){
        LearningPaths learningPath = learningPathService.getLearningPathById(learningPathId);

        List<Weeks> weeksList = weeksService.getWeeksByLearningPathId(learningPathId);
        List<WeekDTO> weekDTOList = new ArrayList<>();
        for (Weeks week: weeksList){
            WeekDTO weekDTO = new WeekDTO();
            weekDTO.setWeek(week.getWeekNumber());
            weekDTO.setWeek_title(week.getTitle());
            List<Topics> topicsList = topicsService.getTopicsByWeekId(week.getId());
            List<String> topicsTitles = new ArrayList<>();
            for (Topics topic : topicsList) {
                topicsTitles.add(topic.getTitle());
            }
            weekDTO.setTopics(topicsTitles);
            List<Resources> resourcesList = resourcesService.getResourcesByWeekId(week.getId());
            List<ResourceDTO> resourceDTOList = new ArrayList<>();
            for (Resources resource : resourcesList) {
                ResourceDTO resourceDTO = new ResourceDTO();
                resourceDTO.setTitle(resource.getTitle());
                resourceDTO.setUrl(resource.getUrl());
                resourceDTO.setType(String.valueOf(resource.getType()));
                resourceDTOList.add(resourceDTO);
            }
            weekDTO.setResources(resourceDTOList);

            weekDTOList.add(weekDTO);

        }
        return new RoadmapDTO(
                learningPathId,
                new LLMResponseData(learningPath.getTitle(),
                        learningPath.getDurationWeeks(),
                        weekDTOList),
                learningPath.getGoalDescription()
        );

    }
}
