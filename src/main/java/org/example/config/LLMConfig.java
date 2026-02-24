package org.example.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;


@Configuration
public class LLMConfig {

    @Value("${llm.api.key}")
    private String apiKey;

    @Value("${llm.api.url}")
    private String apiUrl;


    @Bean
    public WebClient geminiWebClient() {
        System.out.println("=========");
        System.out.println("Setting up connection to Gemini AI");
        System.out.println("=========");

        if (apiKey == null || apiKey.trim().isEmpty()) {
            System.err.println("WARNING: No API key found!");
        } else {
            String maskedKey = apiKey.length() > 8 ?
                    apiKey.substring(0, 8) + "..." :
                    "[key too short]";
            System.out.println(" API key loaded: " + maskedKey);
        }

        System.out.println(" Base API URL from properties: " + apiUrl);


        String fullUrl = apiUrl + "?key=" + apiKey;

        String maskedFullUrl = apiUrl + "?key=" +
                (apiKey != null && apiKey.length() > 8 ?
                        apiKey.substring(0, 8) + "..." : "[hidden]");
        System.out.println("🔗 Full URL being used: " + maskedFullUrl);

        System.out.println("==========");

        return WebClient.builder()
                .baseUrl(fullUrl)
                .defaultHeader("Content-Type", "application/json")
                .build();
    }
}