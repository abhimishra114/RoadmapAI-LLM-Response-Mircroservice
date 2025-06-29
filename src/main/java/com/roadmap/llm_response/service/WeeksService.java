package com.roadmap.llm_response.service;

import com.roadmap.llm_response.dto.RoadmapResponse;
import com.roadmap.llm_response.dto.WeekDTO;
import com.roadmap.llm_response.model.Weeks;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WeeksService {

    public List<Weeks> createWeeksFromResponse(long learningPathId, RoadmapResponse response){

        List<WeekDTO> dtoList = response.getData().getWeeks();
        List<Weeks> weeksList = dtoList.stream().map(dto -> {
            Weeks week = new Weeks();
            week.setLearningPathId(learningPathId);
            week.setWeekNumber(dto.getWeek());
            week.setTitle(dto.getWeek_title());
            return week;
        }).toList();
        return weeksList;
    }
}
