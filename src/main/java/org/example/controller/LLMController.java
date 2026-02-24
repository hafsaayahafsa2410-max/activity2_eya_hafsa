package org.example.controller;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.example.messaging.LlmProducer;
import org.example.model.LlmRequestMessage;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/llm")
public class LLMController {

    private final LlmProducer llmProducer;

    public LLMController(LlmProducer llmProducer) {
        this.llmProducer = llmProducer;
    }

    @PostMapping("/generate")
    public ResponseEntity<String> generate(@RequestBody String prompt) {

        llmProducer.sendMessage(prompt);

        return ResponseEntity.ok("Request sent to queue. Processing asynchronously.");
    }
}