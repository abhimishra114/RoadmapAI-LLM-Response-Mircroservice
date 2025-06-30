package com.roadmap.llm_response.service;

import com.roadmap.llm_response.dto.RoadmapResponse;
import com.roadmap.llm_response.dto.WeekDTO;
import com.roadmap.llm_response.model.Topics;
import com.roadmap.llm_response.repository.TopicsRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TopicsService {

    private final TopicsRepo topicsRepo;
    public TopicsService(TopicsRepo topicsRepo) {
        this.topicsRepo = topicsRepo;
    }

    public List<Topics> createTopicsFromResponse(long weekId, List<WeekDTO> weeks){
        List<Topics> topicsList = new ArrayList<>();
//        List<WeekDTO> weeks = response.getData().getWeeks();
        for (WeekDTO week : weeks) {
            List<String> topicTitles = week.getTopics();
            for (String title : topicTitles) {
                Topics topic = new Topics();
                topic.setWeekId(weekId);
                topic.setTitle(title);
                topic.setIsCompleted(false);
                topic.setDescription(""); // Assuming description is not provided in the response

                topicsList.add(topic);
            }
        }
        return topicsList;
    }

    public List<Topics> createTopicsFromResponse(Long weekId, List<String> topicTitles) {
        List<Topics> topics = new ArrayList<>();
        for (String title : topicTitles) {
            Topics topic = new Topics();
            topic.setTitle(title);
            topic.setWeekId(weekId);
            topics.add(topic);
        }
        return topics;
    }


    public List<Topics> saveTopics(List<Topics> topicsList) {
        List<Topics> savedTopics = topicsRepo.saveAll(topicsList);
        return savedTopics;
    }

    public List<Topics> getTopicsByWeekId(long weekId) {
        return topicsRepo.findByWeekId(weekId);
    }
}
