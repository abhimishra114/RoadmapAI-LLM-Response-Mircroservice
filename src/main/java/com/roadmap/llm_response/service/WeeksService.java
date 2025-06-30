package com.roadmap.llm_response.service;

import com.roadmap.llm_response.dto.RoadmapResponse;
import com.roadmap.llm_response.dto.WeekDTO;
import com.roadmap.llm_response.model.Weeks;
import com.roadmap.llm_response.repository.WeeksRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WeeksService {

    private final WeeksRepo weeksRepo;
    public WeeksService(WeeksRepo weeksRepo) {
        this.weeksRepo = weeksRepo;
    }
    public List<Weeks> createWeeksFromResponse(long learningPathId, List<WeekDTO> weeks){

//        List<WeekDTO> weeks = response.getData().getWeeks();
        List<Weeks> weeksList = new ArrayList<>();
        for (WeekDTO dto : weeks) {
            Weeks week = new Weeks();
            week.setLearningPathId(learningPathId);
            week.setWeekNumber(dto.getWeek());
            week.setTitle(dto.getWeek_title());
            weeksList.add(week);
        }
        return weeksList;
    }

    // This method will save the list of Weeks objects to the database.
    public List<Weeks> saveWeeks(List<Weeks> weeksList) {
        List<Weeks> savedWeeks = weeksRepo.saveAll(weeksList);
        return weeksList;
    }

    public List<Weeks> getWeeksByLearningPathId(long learningPathId) {
        return weeksRepo.findByLearningPathId(learningPathId);
    }

    public Weeks saveWeek(Weeks week) {
        return weeksRepo.save(week);
    }
}
