package com.roadmap.llm_response.controller;

import com.roadmap.llm_response.dto.ProgressRequestDTO;
import com.roadmap.llm_response.service.ProgressTrackerServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/progress")
public class ProgressTrackerController {

    private final ProgressTrackerServiceImpl progressTrackerService;

    public ProgressTrackerController(ProgressTrackerServiceImpl progressTrackerService) {
        this.progressTrackerService = progressTrackerService;
    }

    // mark topic as completed
    @PostMapping("/markCompleted")
    @ResponseStatus(HttpStatus.OK)
    public void markTopicCompleted(@RequestBody ProgressRequestDTO req) {
        progressTrackerService.markTopicCompleted(req.getUserId(), req.getTopicId());
    }

    // unmark topic as completed
    @PostMapping("/unmarkCompleted")
    @ResponseStatus(HttpStatus.OK)
    public void unmarkTopicCompleted(@RequestBody ProgressRequestDTO req) {
        progressTrackerService.unmarkTopicCompleted(req.getUserId(), req.getTopicId());
    }
}
