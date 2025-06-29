package com.roadmap.llm_response.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoadmapResponse {
    private String status; // Status of the response (e.g., success, error)
    private LLMResponseData data; // Data containing goal, duration, and weeks
    private String message; // Optional message for additional context or error details
}
