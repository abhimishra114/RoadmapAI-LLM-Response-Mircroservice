package com.roadmap.llm_response.service;

import com.roadmap.llm_response.dto.RoadmapResponse;
import com.roadmap.llm_response.dto.WeekDTO;
import com.roadmap.llm_response.model.Topics;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TopicsService {

    public List<Topics> createTopicsFromResponse(long weekId, RoadmapResponse response){
        List<Topics> topicsList = new ArrayList<>();
        List<WeekDTO> weeks = response.getData().getWeeks();
        for (WeekDTO week : weeks) {
            List<String> topicTitles = week.getTopics();
            for (String title : topicTitles) {
                Topics topic = new Topics();
                topic.setWeekId(weekId);
                topic.setTitle(title);
                topic.setDescription(""); // Assuming description is not provided in the response

                topicsList.add(topic);
            }
        }
        return topicsList;
    }
}
