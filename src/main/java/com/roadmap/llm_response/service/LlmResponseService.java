package com.roadmap.llm_response.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.roadmap.llm_response.model.RoadmapRequest;
import com.roadmap.llm_response.utils.PromptLoader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Service
public class LlmResponseService {

    private final WebClient webClient;
    private final PromptLoader promptLoader;

    @Value("${gemini.api.url}")
    private String geminiApiUrl;

    @Value("${gemini.api.key}")
    private String geminiApiKey;

    public LlmResponseService(WebClient.Builder webclientBuilder, PromptLoader promptLoader) {
        this.webClient = webclientBuilder.build();
        this.promptLoader = promptLoader;
    }

    public String generateResponse(RoadmapRequest roadmapRequest) {
        // Building the prompt
        String prompt = buildPrompt(roadmapRequest);

        // crafting a request
        Map<String, Object> requestBody = Map.of(
                "contents", new Object[]{
                        Map.of(
                                "parts", new Object[]{
                                        Map.of("text", prompt)
                                }
                        )
                }
        );

        String response = webClient.post()
                .uri(geminiApiUrl+ geminiApiKey)
                .header("Content-Type", "application/json")
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(String.class)
                .block();
        System.out.println(response);

        return extractResponseContent(response);

    }
    private String extractResponseContent(String response) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(response);
            String rawText = rootNode.path("candidates")
                    .get(0)
                    .path("content")
                    .path("parts")
                    .get(0)
                    .path("text")
                    .asText();

            // Remove markdown backticks and `json` tag
            String cleaned = rawText
                    .replaceAll("(?s)```json\\s*", "")
                    .replaceAll("(?s)```", "")
                    .trim();

            return cleaned;
        } catch (Exception e) {
            return "Error processing request, " + e.getMessage();
        }
    }

    private String buildPrompt(RoadmapRequest roadmapRequest) {

        try {
            String promptTemplate = promptLoader.loadPrompt("roadmap_prompt.txt");
            String userPrompt = "How to become a " + roadmapRequest.getGoal() +
                    " in " + roadmapRequest.getWeeks() + " weeks?";
            String safeUserPrompt = userPrompt.replaceAll("[^a-zA-Z0-9\\s]", " ");

            return promptTemplate.replace("{user_prompt}", safeUserPrompt);
        }catch (Exception e){
            throw new RuntimeException("Error building prompt: " + e.getMessage(), e);
        }
    }

}
