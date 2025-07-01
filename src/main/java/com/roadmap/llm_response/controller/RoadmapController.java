package com.roadmap.llm_response.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.roadmap.llm_response.dto.RoadmapDTO;
import com.roadmap.llm_response.dto.RoadmapResponse;
import com.roadmap.llm_response.service.RoadmapService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/roadmap")
public class RoadmapController {

    private final RoadmapService roadmapService;
    public RoadmapController(RoadmapService roadmapService) {
        this.roadmapService = roadmapService;
    }

    // this will take user input and return a predefined roadmap response in JSON format.
    // TO-DO: Integrate with a real LLM service to generate dynamic responses based on user input.
    @GetMapping("/ai")
    public RoadmapResponse response() throws JsonProcessingException {
        String jsonString = "{\n" +
                "  \"status\": \"success\",\n" +
                "  \"message\": \"This roadmap assumes you already have prior knowledge in the following areas: **Java fundamentals**, **Object-Oriented Programming (OOP)**, and **basic SQL/MySQL operations**. \uD83E\uDDE0 If you're new to these, consider starting with a beginner roadmap or extending your timeline for a smoother learning experience.\",\n" +
                "  \"data\": {\n" +
                "    \"goal\": \"Become a Spring Boot Developer\",\n" +
                "    \"duration_weeks\": 4,\n" +
                "    \"weeks\": [\n" +
                "      {\n" +
                "        \"week\": 1,\n" +
                "        \"week_title\": \"Core Spring and Spring Boot Fundamentals\",\n" +
                "        \"topics\": [\n" +
                "          {\n" +
                "            \"title\": \"Introduction to Spring Framework & IoC Container\",\n" +
                "            \"isCompleted\": false,\n" +
                "            \"id\": 101\n" +
                "          },\n" +
                "          {\n" +
                "            \"title\": \"Dependency Injection (DI) and Beans\",\n" +
                "            \"isCompleted\": false,\n" +
                "            \"id\": 102\n" +
                "          },\n" +
                "          {\n" +
                "            \"title\": \"Getting Started with Spring Boot: Setup & Auto-configuration\",\n" +
                "            \"isCompleted\": false,\n" +
                "            \"id\": 103\n" +
                "          }\n" +
                "        ],\n" +
                "        \"resources\": [\n" +
                "          {\n" +
                "            \"title\": \"Spring Framework Documentation - Core\",\n" +
                "            \"url\": \"https://docs.spring.io/spring-framework/reference/core/overview.html\",\n" +
                "            \"type\": \"article\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"title\": \"Spring Boot Official Guide\",\n" +
                "            \"url\": \"https://spring.io/guides/gs/spring-boot/\",\n" +
                "            \"type\": \"article\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"title\": \"Inversion of Control and Dependency Injection in Spring\",\n" +
                "            \"url\": \"https://www.youtube.com/watch?v=F0Cg-qRkFUs\",\n" +
                "            \"type\": \"video\"\n" +
                "          }\n" +
                "        ]\n" +
                "      },\n" +
                "      {\n" +
                "        \"week\": 2,\n" +
                "        \"week_title\": \"Building RESTful APIs with Spring Boot\",\n" +
                "        \"topics\": [\n" +
                "          {\n" +
                "            \"title\": \"Understanding REST Principles and HTTP Methods\",\n" +
                "            \"isCompleted\": false,\n" +
                "            \"id\": 201\n" +
                "          },\n" +
                "          {\n" +
                "            \"title\": \"Spring Web (Spring MVC) for REST APIs\",\n" +
                "            \"isCompleted\": false,\n" +
                "            \"id\": 202\n" +
                "          },\n" +
                "          {\n" +
                "            \"title\": \"Request Mapping, Path Variables, Request Params\",\n" +
                "            \"isCompleted\": false,\n" +
                "            \"id\": 203\n" +
                "          }\n" +
                "        ],\n" +
                "        \"resources\": [\n" +
                "          {\n" +
                "            \"title\": \"Spring @RestController Annotation\",\n" +
                "            \"url\": \"https://www.baeldung.com/spring-restcontroller-annotation\",\n" +
                "            \"type\": \"article\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"title\": \"RESTful Web Services by IBM\",\n" +
                "            \"url\": \"https://www.ibm.com/topics/rest-apis\",\n" +
                "            \"type\": \"article\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"title\": \"Building a RESTful Web Service with Spring Boot\",\n" +
                "            \"url\": \"https://spring.io/guides/gs/rest-service/\",\n" +
                "            \"type\": \"article\"\n" +
                "          }\n" +
                "        ]\n" +
                "      },\n" +
                "      {\n" +
                "        \"week\": 3,\n" +
                "        \"week_title\": \"Data Persistence with Spring Data JPA and MySQL\",\n" +
                "        \"topics\": [\n" +
                "          {\n" +
                "            \"title\": \"Introduction to Spring Data JPA and ORM concepts\",\n" +
                "            \"isCompleted\": false,\n" +
                "            \"id\": 301\n" +
                "          },\n" +
                "          {\n" +
                "            \"title\": \"Entities, Repositories, and CrudRepository\",\n" +
                "            \"isCompleted\": false,\n" +
                "            \"id\": 302\n" +
                "          },\n" +
                "          {\n" +
                "            \"title\": \"Connecting Spring Boot to MySQL Database\",\n" +
                "            \"isCompleted\": false,\n" +
                "            \"id\": 303\n" +
                "          }\n" +
                "        ],\n" +
                "        \"resources\": [\n" +
                "          {\n" +
                "            \"title\": \"Spring Data JPA Reference Documentation\",\n" +
                "            \"url\": \"https://docs.spring.io/spring-data/jpa/docs/current/reference/html/\",\n" +
                "            \"type\": \"article\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"title\": \"Spring Boot and MySQL CRUD Example\",\n" +
                "            \"url\": \"https://www.javatpoint.com/spring-boot-crud-example\",\n" +
                "            \"type\": \"article\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"title\": \"Spring Boot JPA Tutorial\",\n" +
                "            \"url\": \"https://www.youtube.com/watch?v=l_QyEP2u7b8\",\n" +
                "            \"type\": \"video\"\n" +
                "          }\n" +
                "        ]\n" +
                "      },\n" +
                "      {\n" +
                "        \"week\": 4,\n" +
                "        \"week_title\": \"Advanced Topics and Project Development\",\n" +
                "        \"topics\": [\n" +
                "          {\n" +
                "            \"title\": \"Error Handling and Exception Management in Spring Boot\",\n" +
                "            \"isCompleted\": false,\n" +
                "            \"id\": 401\n" +
                "          },\n" +
                "          {\n" +
                "            \"title\": \"Basic Security with Spring Security (optional, if time permits)\",\n" +
                "            \"isCompleted\": false,\n" +
                "            \"id\": 402\n" +
                "          },\n" +
                "          {\n" +
                "            \"title\": \"Building a Mini Project: RESTful API with CRUD operations\",\n" +
                "            \"isCompleted\": false,\n" +
                "            \"id\": 403\n" +
                "          }\n" +
                "        ],\n" +
                "        \"resources\": [\n" +
                "          {\n" +
                "            \"title\": \"Global Exception Handling in Spring Boot\",\n" +
                "            \"url\": \"https://www.baeldung.com/spring-boot-custom-error-pages\",\n" +
                "            \"type\": \"article\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"title\": \"Spring Security Getting Started\",\n" +
                "            \"url\": \"https://spring.io/guides/gs/securing-web/\",\n" +
                "            \"type\": \"article\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"title\": \"Spring Boot REST API Tutorial for Beginners\",\n" +
                "            \"url\": \"https://www.javainuse.com/spring/boot-rest\",\n" +
                "            \"type\": \"article\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"title\": \"Example Spring Boot REST API GitHub Repo\",\n" +
                "            \"url\": \"https://github.com/spring-projects/spring-boot-examples\",\n" +
                "            \"type\": \"github\"\n" +
                "          }\n" +
                "        ]\n" +
                "      }\n" +
                "    ]\n" +
                "  }\n" +
                "}";
        // parsing the jsonString into RoadmapResponse object
        ObjectMapper objectMapper = new ObjectMapper();
        RoadmapResponse response = objectMapper.readValue(jsonString, RoadmapResponse.class);

        return response;
    }

    @PostMapping("/save/{userId}")
    public ResponseEntity<RoadmapDTO> saveRoadmap(
            @PathVariable long userId,
            @RequestBody RoadmapResponse response){
        try {
            RoadmapDTO roadmapDTO = roadmapService.saveRoadmap(userId, response);
            return ResponseEntity.ok(roadmapDTO);
        } catch (JsonProcessingException e) {
            return ResponseEntity.badRequest().body(null);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }


    @GetMapping
    public RoadmapDTO getFullRoadmapByLearningPathId(
            @RequestParam long userId,
            @RequestParam long learningPathId) {
        return roadmapService.getFullRoadmapByLearningPathId(userId,learningPathId);
    }
}
