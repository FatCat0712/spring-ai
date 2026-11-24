package com.javatechie.spring_openai_demo.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;

@Service
public class OpenAIChatService {
    private final ChatClient chatClient;

    public OpenAIChatService(ChatClient.Builder chatClient) {
        this.chatClient = chatClient.build();
    }

    public String chatWithOpenAI(String message) {
        ChatOptions chatOptions = ChatOptions.builder()
                .model("gpt-4o-mini")
                .temperature(0.3)
                .maxTokens(400)
                .frequencyPenalty(0.7)
                .presencePenalty(0.7)
                .stopSequences(List.of("}"))
                .topK(50)
                .topP(0.5)
                .build();
        return chatClient
                .prompt(message)
                .options(chatOptions.mutate())
                .call()
                .content();
    }

    public Flux<String> askToAIStream(String message) {
        return chatClient
                .prompt(message)
                .stream()
                .content();
    }


}
