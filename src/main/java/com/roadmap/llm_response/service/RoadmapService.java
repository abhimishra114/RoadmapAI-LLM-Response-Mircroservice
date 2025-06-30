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
import java.util.Map;

@Slf4j
@Service
public class RoadmapService {
    private final LearningPathService learningPathService;
    private final WeeksService weeksService;
    private final TopicsService topicsService;
    private final ResourcesService resourcesService;
    private final ProgressTrackerService progressTrackerService;

    public RoadmapService(LearningPathService learningPathService, WeeksService weeksService, TopicsService topicsService, ResourcesService resourcesService, ProgressTrackerService progressTrackerService) {
        this.learningPathService = learningPathService;
        this.weeksService = weeksService;
        this.topicsService = topicsService;
        this.resourcesService = resourcesService;
        this.progressTrackerService = progressTrackerService;
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

    public RoadmapDTO getFullRoadmapByLearningPathId(long userId, long learningPathId){
        LearningPaths learningPath = learningPathService.getLearningPathById(learningPathId);

        List<Weeks> weeksList = weeksService.getWeeksByLearningPathId(learningPathId);
        List<WeekDTO> weekDTOList = new ArrayList<>();
        for (Weeks week: weeksList){
            WeekDTO weekDTO = new WeekDTO();
            weekDTO.setWeek(week.getWeekNumber());
            weekDTO.setWeek_title(week.getTitle());

            // Get topics for the week
            List<Topics> topicsList = topicsService.getTopicsByWeekId(week.getId());
            List<Long> topicIds = topicsList.stream().map(Topics::getId).toList();

            // Get progress map for the user
            Map<Long, Boolean> completionMap = progressTrackerService.getCompletionStatusForUser(userId, topicIds);

            // Build TopicDTO list with completion info
            List<TopicDTO> topicDTOList = new ArrayList<>();
            for (Topics topic : topicsList){
                TopicDTO topicDTO = new TopicDTO();
                topicDTO.setId(topic.getId());
                topicDTO.setTitle(topic.getTitle());
                topicDTO.setCompleted(completionMap.getOrDefault(topic.getId(),false));
                topicDTOList.add(topicDTO);
            }

            weekDTO.setTopics(topicDTOList);

            // Build Resource list for the week
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
        // progress percentage for the learning path
        double progressPercentage = progressTrackerService.getProgressPercentageForLearningPath(userId, learningPathId);
        double roundedProgressPercentage = Math.round(progressPercentage * Math.pow(10, 2)) / Math.pow(10, 2);

        return new RoadmapDTO(
                learningPathId,
                roundedProgressPercentage,
                new LLMResponseData(learningPath.getTitle(),
                        learningPath.getDurationWeeks(),
                        weekDTOList),
                learningPath.getGoalDescription()
        );

    }
}
