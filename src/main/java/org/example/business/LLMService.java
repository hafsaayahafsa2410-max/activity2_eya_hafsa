package org.example.business;

import org.example.integration.LLMClient;
import org.springframework.stereotype.Service;

@Service
public class LLMService {

    private final LLMClient llmClient;

    public LLMService(LLMClient llmClient) {
        this.llmClient = llmClient;
    }

    public String generateFromPrompt(String prompt) {
        return llmClient.generateText(prompt);
    }
}
