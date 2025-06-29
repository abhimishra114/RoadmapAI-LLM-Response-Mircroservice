package com.roadmap.llm_response.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResourceDTO {
    private String title; // Title of the resource
    private String link; // URL of the resource
    private String type; // Type of resource (e.g., video, article, book)
}
