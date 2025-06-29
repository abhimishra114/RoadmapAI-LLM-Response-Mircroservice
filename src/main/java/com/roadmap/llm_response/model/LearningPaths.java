package com.roadmap.llm_response.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LearningPaths {
    @Id
    private long id;
    private long userId;
    private String title;
    private String goalDescription;
    private int durationWeeks;
    private boolean generatedByAI;
    private String rawResponse; // Stores full LLM JSON
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
