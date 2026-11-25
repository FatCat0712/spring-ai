package com.javatechie.spring_openai_demo.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.DefaultChatClientBuilder;
//import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.client.advisor.api.Advisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.ai.chat.memory.repository.jdbc.JdbcChatMemoryRepository;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ChatAIConfiguration {

    @Bean
    public ChatMemory chatMemory(JdbcChatMemoryRepository jdbcChatMemoryRepository) {
        return MessageWindowChatMemory.builder()
                .maxMessages(5)
                .chatMemoryRepository(jdbcChatMemoryRepository)
                .build();
    }

      @Bean
      public ChatClient chatClient(ChatClient.Builder chatClientBuilder, ChatMemory chatMemory) {
          Advisor loggerAdvisor = new SimpleLoggerAdvisor();

          Advisor messageAdvisor = MessageChatMemoryAdvisor
                   .builder(chatMemory)
                   .build();

          return chatClientBuilder
                  .defaultAdvisors(List.of(loggerAdvisor, messageAdvisor))
                  .build();
      }

}
