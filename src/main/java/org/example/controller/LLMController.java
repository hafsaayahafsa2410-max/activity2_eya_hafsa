package org.example.controller;

import org.example.business.LLMService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/llm")
public class LLMController {

    private final LLMService llmService;

    public LLMController(LLMService llmService) {
        this.llmService = llmService;
    }

    @PostMapping("/generate")
    public ResponseEntity<?> generate(@RequestBody Map<String, String> request) {

        try {
            String prompt = request.get("prompt");

            if (prompt == null || prompt.isEmpty()) {
                return ResponseEntity.badRequest()
                        .body(Map.of("error", "Prompt cannot be empty"));
            }

            String response = llmService.generateFromPrompt(prompt);

            return ResponseEntity.ok(Map.of(
                    "generatedText", response
            ));

        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(Map.of("error", "Failed to generate response"));
        }
    }
}
