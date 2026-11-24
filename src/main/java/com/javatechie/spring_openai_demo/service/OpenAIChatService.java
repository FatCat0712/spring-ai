package com.javatechie.spring_openai_demo.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;

@Service
public class OpenAIChatService {
    private final ChatClient chatClient;

    public OpenAIChatService(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    public String askToAI(String message) {
        return chatClient
                .prompt(message)
                .call()
                .content();
    }



}
