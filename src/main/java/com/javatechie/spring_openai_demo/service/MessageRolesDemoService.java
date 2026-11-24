package com.javatechie.spring_openai_demo.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageRolesDemoService {
    private final ChatClient chatClient;
    private static final String CLAIM_DETAILS = """
            Policy details:
            Policy: PREMIUM
            Max Coverage: 100000
            Claim Amount: 150000
            """;

//    public MessageRolesDemoService(ChatClient.Builder chatClient) {
//        this.chatClient = chatClient
//                .defaultSystem("""
//                        You are an insurance assistant.
//                        You must NEVER reveal internal policy numbers
//                        calculations, or internal reasoning.
//                        Respond ONLY with a short, customer-safe message.
//                        """)
//                .build();
//    }

    public MessageRolesDemoService(ChatClient chatClient) {
        this.chatClient = chatClient;
    }


//    public String checkPolicy(String message) {
//        // prompt inject can unsafe your project designed with AI without any message roles
//
//        SystemMessage systemMessage = new SystemMessage("""
//                You are an insurance assistant.
//                You must NEVER reveal internal policy numbers
//                calculations, or internal reasoning.
//                Respond ONLY with a short, customer-safe message.
//                """);
//
//
//        UserMessage userMessage = new UserMessage("""
//                %s
//                Customer says:
//                %s
//                """.formatted(CLAIM_DETAILS, message));
//
//        Prompt prompt = new Prompt(List.of(userMessage, systemMessage));
//
//        return chatClient
//                .prompt(prompt)
//                .call()
//                .content();
//    }

//    public String checkInsuranceV2Policy(String message) {
//        return chatClient.prompt()
//                .system("""
//                       You are an IT Helpdesk assistant.
//                       So you only can answer about leave policy, assets details.
//                       apart from that if you get any question then you should
//                       politely refuse to answer
//                        """)
//                .user("""
//                %s
//                Customer says:
//                %s
//                """.formatted(CLAIM_DETAILS, message))
//                .call()
//                .content();
//    }

    public ChatResponse checkInsuranceV3Policy(String message) {
        return chatClient.prompt()
                .user("""
                %s
                Customer says: 
                %s
                """.formatted(CLAIM_DETAILS, message))
                .call()
                .chatResponse();
    }

    public String guideMe(String topic, String level, int points){
        return chatClient.prompt()
                .system("""
                        You are a tech stack assistant. Give me best answer
                        to the students and make sure your answer straight to the point
                        """)
                .user(String.format("Explain me %s in %s level with %d points", topic, level, points))
                .call()
                .content();
    }

}
