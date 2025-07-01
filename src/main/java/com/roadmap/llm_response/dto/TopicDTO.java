package com.roadmap.llm_response.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@JsonIgnoreProperties({"id"})
@Data
public class TopicDTO {
    private long id;
    private String title;
    @JsonProperty("isCompleted")
    private boolean isCompleted;
}
