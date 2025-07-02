package com.roadmap.llm_response.model;

import lombok.Data;

@Data
public class RoadmapRequest {
    private String goal;
    private int weeks;
}
