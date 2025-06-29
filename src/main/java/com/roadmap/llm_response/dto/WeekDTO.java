package com.roadmap.llm_response.dto;

import com.roadmap.llm_response.model.Topics;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WeekDTO { // represents single week
    private int week;
    private String week_title; // Title of the week
    private List<String> topics; // List of topic titles
    private List<ResourceDTO> resources; // List of resources for the week
}
