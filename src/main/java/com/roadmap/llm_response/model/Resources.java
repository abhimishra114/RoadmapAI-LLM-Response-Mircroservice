package com.roadmap.llm_response.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Resources {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long weekId;
    private String title;
    private String url;
    @Enumerated(EnumType.STRING)
    private Type type;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public enum Type {
        video, article, repo, book, other;

        @JsonCreator
        public static Type fromString(String value){
            try {
                return Type.valueOf(value.trim().toLowerCase());
            }catch (IllegalArgumentException e) {
                return other; // Default to OTHER if the value doesn't match any enum
            }
        }
    }

}
