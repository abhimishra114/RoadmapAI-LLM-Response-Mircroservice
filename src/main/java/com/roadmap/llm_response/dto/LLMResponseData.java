package com.roadmap.llm_response.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LLMResponseData { // contains goal duration and weeks
    private String goal;
    private int duration_weeks;
    private List<WeekDTO> weeks;
}
