package org.example.messaging;

import org.example.config.JmsConfig;
import org.example.model.LlmRequestMessage;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class LlmProducer {

    private final JmsTemplate jmsTemplate;

    public LlmProducer(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void sendMessage(String prompt) {
        jmsTemplate.convertAndSend("llm.queue", prompt);
        System.out.println("Message sent to queue: " + prompt);
    }
}