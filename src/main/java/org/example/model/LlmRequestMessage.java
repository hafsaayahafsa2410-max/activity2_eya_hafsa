package org.example.model;

import java.io.Serializable;

public class LlmRequestMessage implements Serializable {

    private String prompt;

    public LlmRequestMessage() {}

    public LlmRequestMessage(String prompt) {
        this.prompt = prompt;
    }

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }
}