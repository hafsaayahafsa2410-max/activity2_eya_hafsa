package org.example.messaging;

import org.example.integration.GeminiClient;
import org.example.model.LlmRequestMessage;
import org.example.business.LLMService;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
@Component
public class LlmConsumer {

    private final GeminiClient geminiClient;

    public LlmConsumer(GeminiClient geminiClient) {
        this.geminiClient = geminiClient;
    }

    @JmsListener(destination = "llm.queue")
    public void receiveMessage(String message) {

        System.out.println("Message received from queue: " + message);

        String geminiResponse = geminiClient.generateText(message);

        System.out.println("Gemini response: " + geminiResponse);

        System.out.println("Processing done.");
    }
}