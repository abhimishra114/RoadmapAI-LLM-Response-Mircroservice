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
                "  \"message\": \"Embark on your Python journey with enthusiasm and consistency!\",\n" +
                "  \"data\": {\n" +
                "    \"goal\": \"Python Basics\",\n" +
                "    \"duration_weeks\": 3,\n" +
                "    \"weeks\": [\n" +
                "      {\n" +
                "        \"week\": 1,\n" +
                "        \"week_title\": \"Understanding the Fundamentals of Python\",\n" +
                "        \"topics\": [\n" +
                "          \"Introduction to Python & Setup\",\n" +
                "          \"Variables, Data Types, and Operators\",\n" +
                "          \"Input/Output and Basic String Manipulation\"\n" +
                "        ],\n" +
                "        \"resources\": [\n" +
                "          {\n" +
                "            \"title\": \"Python for Everybody (Getting Started)\",\n" +
                "            \"url\": \"https://www.py4e.com/lessons/intro\",\n" +
                "            \"type\": \"video series\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"title\": \"Python Docs: Built-in Types\",\n" +
                "            \"url\": \"https://docs.python.org/3/library/stdtypes.html\",\n" +
                "            \"type\": \"article\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"title\": \"W3Schools Python Variables\",\n" +
                "            \"url\": \"https://www.w3schools.com/python/python_variables.asp\",\n" +
                "            \"type\": \"article\"\n" +
                "          }\n" +
                "        ]\n" +
                "      },\n" +
                "      {\n" +
                "        \"week\": 2,\n" +
                "        \"week_title\": \"Control Flow and Data Structures\",\n" +
                "        \"topics\": [\n" +
                "          \"Conditional Statements (if/elif/else)\",\n" +
                "          \"Loops (for/while)\",\n" +
                "          \"Lists and Tuples\"\n" +
                "        ],\n" +
                "        \"resources\": [\n" +
                "          {\n" +
                "            \"title\": \"Real Python: Conditional Statements\",\n" +
                "            \"url\": \"https://realpython.com/python-conditional-statements/\",\n" +
                "            \"type\": \"article\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"title\": \"Programiz Python Loops\",\n" +
                "            \"url\": \"https://www.programiz.com/python-programming/loops\",\n" +
                "            \"type\": \"article\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"title\": \"Corey Schafer: Python Lists and Tuples Tutorial\",\n" +
                "            \"url\": \"https://www.youtube.com/watch?v=W8KRzm-HUcc\",\n" +
                "            \"type\": \"video\"\n" +
                "          }\n" +
                "        ]\n" +
                "      },\n" +
                "      {\n" +
                "        \"week\": 3,\n" +
                "        \"week_title\": \"Functions, Dictionaries, and Modules\",\n" +
                "        \"topics\": [\n" +
                "          \"Functions and Scope\",\n" +
                "          \"Dictionaries and Sets\",\n" +
                "          \"Introduction to Modules and Packages\"\n" +
                "        ],\n" +
                "        \"resources\": [\n" +
                "          {\n" +
                "            \"title\": \"Python for Everybody (Functions)\",\n" +
                "            \"url\": \"https://www.py4e.com/lessons/functions\",\n" +
                "            \"type\": \"video series\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"title\": \"Real Python: Dictionaries\",\n" +
                "            \"url\": \"https://realpython.com/python-dicts/\",\n" +
                "            \"type\": \"article\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"title\": \"Automate the Boring Stuff with Python: Organizing Programs with Functions\",\n" +
                "            \"url\": \"https://automatetheboringstuff.com/2e/chapter3/\",\n" +
                "            \"type\": \"article\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"title\": \"Python Docs: Modules\",\n" +
                "            \"url\": \"https://docs.python.org/3/tutorial/modules.html\",\n" +
                "            \"type\": \"article\"\n" +
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
