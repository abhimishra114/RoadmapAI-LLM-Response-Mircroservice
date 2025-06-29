package com.roadmap.llm_response.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
public class Resources {
    @Id
    private long id;
    private long topicId;
    private String title;
    private String url;
    @Enumerated(EnumType.STRING)
    private Type type;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public enum Type {
        video, article, repo, book, OTHER;

        @JsonCreator
        public static Type fromString(String value){
            return Type.valueOf(value.toLowerCase());
        }
    }

}
