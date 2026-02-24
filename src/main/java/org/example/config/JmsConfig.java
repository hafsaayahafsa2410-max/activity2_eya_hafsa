package org.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import jakarta.jms.Queue;
import org.apache.activemq.command.ActiveMQQueue;

@Configuration
public class JmsConfig {

    public static final String LLM_QUEUE = "llm.queue";

    @Bean
    public Queue queue() {
        return new ActiveMQQueue(LLM_QUEUE);
    }
}