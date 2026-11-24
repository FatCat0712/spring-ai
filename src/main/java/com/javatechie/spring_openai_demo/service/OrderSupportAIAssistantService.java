package com.javatechie.spring_openai_demo.service;

import com.javatechie.spring_openai_demo.advisor.AuditTokenUsageAdvisor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SafeGuardAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderSupportAIAssistantService {
    private final ChatClient chatClient;

    @Value("classpath:prompts/order_system_template.st")
    private Resource orderSystemPrompt;

    @Value("classpath:prompts/order_user_template.st")
    private Resource orderUserPrompt;

    @Value("classpath:prompts/order_system_policy.st")
    private Resource orderSystemPolicyPrompt;

    public OrderSupportAIAssistantService(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    public String assistWithOrderSupport(String customerName, String orderId, String customerMessage) {
        return chatClient
                .prompt()
                .system(orderSystemPrompt)
                .user(promptUserSpec -> promptUserSpec.text(orderUserPrompt)
                        .param("customerName", customerName)
                        .param("orderID", orderId)
                        .param("customerMessage", customerMessage)
                )
                .call()
                .content();
    }

    public String talkToAISupport(String customerName, String orderId, String customerMessage) {
        return chatClient
                .prompt()
                .advisors(List.of(
                        new SimpleLoggerAdvisor(),
                        new AuditTokenUsageAdvisor(),
                        new SafeGuardAdvisor(
                                List.of("password", "otp", "cvv"),
                                "For security reason, we never ask such sensitive information please talk to our customer support directly",
                                1)
                ))
                .system(orderSystemPolicyPrompt)
                .user(promptUserSpec -> promptUserSpec.text(orderUserPrompt)
                        .param("customerName", customerName)
                        .param("orderID", orderId)
                        .param("customerMessage", customerMessage)
                )
                .call()
                .content();
    }
}
