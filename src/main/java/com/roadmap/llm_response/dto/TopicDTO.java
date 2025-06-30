package com.roadmap.llm_response.dto;

import lombok.Data;

@Data
public class TopicDTO {
    private long id;
    private String title;
    private boolean isCompleted;
}
