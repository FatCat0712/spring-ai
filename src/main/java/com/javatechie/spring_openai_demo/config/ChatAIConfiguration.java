package com.javatechie.spring_openai_demo.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.DefaultChatClientBuilder;
//import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChatAIConfiguration {
      @Bean
      public ChatClient chatClient(ChatClient.Builder chatClientBuilder) {
          return chatClientBuilder
                  .build();
      }

}
