package com.roadmap.llm_response.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.roadmap.llm_response.dto.RoadmapResponse;
import com.roadmap.llm_response.model.LearningPaths;
import com.roadmap.llm_response.model.Weeks;
import com.roadmap.llm_response.service.LearningPathService;
import com.roadmap.llm_response.service.WeeksService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/test")
public class Test {

    @GetMapping("/roadmap")
    public RoadmapResponse response() throws JsonProcessingException {
        String jsonString = "{\n" +
                "  \"status\": \"success\",\n" +
                "  \"message\": \"Embark on your Python journey with enthusiasm and watch your skills grow!\",\n" +
                "  \"data\": {\n" +
                "    \"goal\": \"Basic Python\",\n" +
                "    \"duration_weeks\": 3,\n" +
                "    \"weeks\": [\n" +
                "      {\n" +
                "        \"week\": 1,\n" +
                "        \"week_title\": \"Understanding Python Fundamentals and Setting Up Your Environment\",\n" +
                "        \"topics\": [\n" +
                "          \"Introduction to Python & Installation\",\n" +
                "          \"Variables, Data Types & Basic Operations\",\n" +
                "          \"Input/Output & String Formatting\"\n" +
                "        ],\n" +
                "        \"resources\": [\n" +
                "          {\n" +
                "            \"title\": \"Python for Beginners - Full Course\",\n" +
                "            \"link\": \"https://www.youtube.com/watch?v=rfscVS0vtbw\",\n" +
                "            \"type\": \"video\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"title\": \"Learn Python - Free Interactive Tutorial\",\n" +
                "            \"link\": \"https://www.learnpython.org/\",\n" +
                "            \"type\": \"interactive\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"title\": \"Official Python Documentation (Getting Started)\",\n" +
                "            \"link\": \"https://docs.python.org/3/tutorial/index.html\",\n" +
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
                "          \"Lists, Tuples, Dictionaries, and Sets\"\n" +
                "        ],\n" +
                "        \"resources\": [\n" +
                "          {\n" +
                "            \"title\": \"Python If-Else Statement Tutorial\",\n" +
                "            \"link\": \"https://www.geeksforgeeks.org/python-if-else/\",\n" +
                "            \"type\": \"article\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"title\": \"Python For Loops Explained\",\n" +
                "            \"link\": \"https://www.programiz.com/python-programming/for-loop\",\n" +
                "            \"type\": \"article\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"title\": \"Python Data Structures Tutorial\",\n" +
                "            \"link\": \"https://realpython.com/python-data-structures/\",\n" +
                "            \"type\": \"article\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"title\": \"HackerRank - Python Practice\",\n" +
                "            \"link\": \"https://www.hackerrank.com/domains/python\",\n" +
                "            \"type\": \"practice\"\n" +
                "          }\n" +
                "        ]\n" +
                "      },\n" +
                "      {\n" +
                "        \"week\": 3,\n" +
                "        \"week_title\": \"Functions, Modules, and Basic File Handling\",\n" +
                "        \"topics\": [\n" +
                "          \"Functions: Definition, Arguments, and Return Values\",\n" +
                "          \"Modules and Packages\",\n" +
                "          \"File Input/Output\"\n" +
                "        ],\n" +
                "        \"resources\": [\n" +
                "          {\n" +
                "            \"title\": \"Python Functions Tutorial\",\n" +
                "            \"link\": \"https://www.freecodecamp.org/news/python-functions-explained/\",\n" +
                "            \"type\": \"article\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"title\": \"Python Modules and Packages\",\n" +
                "            \"link\": \"https://www.programiz.com/python-programming/modules\",\n" +
                "            \"type\": \"article\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"title\": \"Working with Files in Python\",\n" +
                "            \"link\": \"https://www.geeksforgeeks.org/reading-writing-text-files-python/\",\n" +
                "            \"type\": \"article\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"title\": \"GitHub - Awesome Python\",\n" +
                "            \"link\": \"https://github.com/vinta/awesome-python\",\n" +
                "            \"type\": \"repo\"\n" +
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

    @GetMapping("/learning-path/{learningPathId}")
    public List<Weeks> weeks(@PathVariable long learningPathId) throws JsonProcessingException {

        RoadmapResponse response = response();
        WeeksService service = new WeeksService();
        return service.createWeeksFromResponse(learningPathId, response);
    }
}
