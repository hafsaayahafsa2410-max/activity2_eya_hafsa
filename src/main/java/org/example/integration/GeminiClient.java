package org.example.integration;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;


@Component
public class GeminiClient implements LLMClient {

    private final WebClient webClient;
    private final ObjectMapper objectMapper;

    @Autowired
    public GeminiClient(WebClient geminiWebClient) {
        this.webClient = geminiWebClient;
        this.objectMapper = new ObjectMapper();
        System.out.println("✨ Gemini client is ready to chat with AI!");
    }

    @Override
    public String generateText(String prompt) {
        System.out.println(" Thinking about: " + shortenText(prompt, 50));

        try {
            ObjectNode requestBody = createGeminiRequest(prompt);

            String response = webClient.post()
                    .bodyValue(requestBody)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();

            return extractTextFromResponse(response);

        } catch (WebClientResponseException e) {
            return handleHttpError(e);

        } catch (Exception e) {
            System.err.println(" Something unexpected happened: " + e.getMessage());
            e.printStackTrace();
            return "Sorry, I couldn't get a response from the AI right now. Please try again later.";
        }
    }


    private ObjectNode createGeminiRequest(String prompt) {
        ObjectNode requestBody = objectMapper.createObjectNode();

        ArrayNode contents = requestBody.putArray("contents");
        ObjectNode content = contents.addObject();
        ArrayNode parts = content.putArray("parts");
        ObjectNode part = parts.addObject();
        part.put("text", prompt);

        // Increase this value to get complete sentences
        ObjectNode generationConfig = objectMapper.createObjectNode();
        generationConfig.put("maxOutputTokens", 900);
        generationConfig.put("temperature", 0.7);
        requestBody.set("generationConfig", generationConfig);

        return requestBody;
    }

    /**
     * Pulls out just the text from Gemini's complex response
     */
    private String extractTextFromResponse(String jsonResponse) {
        try {
            JsonNode root = objectMapper.readTree(jsonResponse);

            JsonNode candidates = root.path("candidates");

            if (candidates.isArray() && candidates.size() > 0) {
                JsonNode content = candidates.get(0).path("content");
                JsonNode parts = content.path("parts");

                if (parts.isArray() && parts.size() > 0) {
                    String text = parts.get(0).path("text").asText();
                    System.out.println(" Got a nice response from Gemini!");
                    return text.trim();
                }
            }

            JsonNode error = root.path("error");
            if (!error.isMissingNode()) {
                String errorMessage = error.path("message").asText();
                System.err.println(" Gemini said: " + errorMessage);
                return "Gemini couldn't help: " + errorMessage;
            }

            return "Hmm, Gemini gave me a response I didn't understand.";

        } catch (Exception e) {
            System.err.println(" Got confused trying to read Gemini's response");
            return "Sorry, I couldn't understand the AI's response.";
        }
    }


    private String handleHttpError(WebClientResponseException e) {
        int statusCode = e.getStatusCode().value();
        String errorBody = e.getResponseBodyAsString();

        System.err.println(" HTTP Error " + statusCode + ": " + errorBody);

        switch (statusCode) {
            case 400:
                return "The message I sent to Gemini was badly formatted. Please check the request structure.";
            case 401:
            case 403:
                return "My API key isn't working. Please check if it's correct and still valid.";
            case 404:
                return "I can't find the Gemini API. Please check the URL in application.properties.";
            case 429:
                return "We're chatting too fast! Gemini's free tier allows 60 requests per minute. Let's wait a bit.";
            case 500:
            case 502:
            case 503:
                return "Gemini's servers seem to be having trouble. Please try again in a moment.";
            default:
                return "Something went wrong talking to Gemini. Error code: " + statusCode;
        }
    }


    private String shortenText(String text, int maxLength) {
        if (text.length() <= maxLength) return text;
        return text.substring(0, maxLength) + "...";
    }
}
