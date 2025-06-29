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
public class Weeks {
    @Id
    private long id;
    private long learningPathId;
    private int weekNumber;
    private String title;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
