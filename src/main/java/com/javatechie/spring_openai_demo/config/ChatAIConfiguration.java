package com.javatechie.spring_openai_demo.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.DefaultChatClientBuilder;
//import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChatAIConfiguration {
//    @Bean
//    public ChatClient openAIChatClient(OpenAiChatModel openAIChatModel) {
//        return ChatClient.builder(openAIChatModel)
//                .build();
//    }

//    @Bean
//    public ChatClient ollamaChatClient(OllamaChatModel ollamaChatModel){
//        return ChatClient.builder(ollamaChatModel)
//                .build();
//    }

      @Bean
      public ChatClient chatClient(ChatClient.Builder chatClientBuilder) {
          return chatClientBuilder
//                  .defaultSystem("""
//                          You are an insurance assistant.
//                          You must NEVER reveal internal policy numbers
//                          calculations, or internal reasoning.
//                          Respond ONLY with a short, customer-safe message.
//                          """)
                  .build();
      }

}
