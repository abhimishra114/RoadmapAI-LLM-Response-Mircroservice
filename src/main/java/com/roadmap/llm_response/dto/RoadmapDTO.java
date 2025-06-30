package com.roadmap.llm_response.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoadmapDTO {
    private long learningPathId;
    private LLMResponseData data;
    private String message;
}
