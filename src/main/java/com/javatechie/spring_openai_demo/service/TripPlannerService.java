package com.javatechie.spring_openai_demo.service;

import com.javatechie.spring_openai_demo.dto.TripPlan;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.ai.converter.BeanOutputConverter;
import org.springframework.ai.converter.ListOutputConverter;
import org.springframework.ai.converter.MapOutputConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

@Service
public class TripPlannerService {
    private final ChatClient chatClient;

    @Value(("classpath:prompts/trip_guide_template.st"))
    private Resource tripGuideTemplate;

    public TripPlannerService(ChatClient.Builder chatClient) {
        this.chatClient = chatClient.build();
    }

    public TripPlan getTripPlans(String message) {
        return chatClient
                .prompt()
                .system(tripGuideTemplate)
                .user(message)
                .call()
                .entity(new BeanOutputConverter<>(TripPlan.class));
    }

    public List<String> getTripSports(String message) {
        return chatClient
                .prompt()
                .user(message)
                .call()
                .entity(new ListOutputConverter());
    }

    public Map<String, Object> getTripGuide(String message) {
        return chatClient
                .prompt()
                .user(message)
                .call()
                .entity(new MapOutputConverter());
    }

    public List<TripPlan> completeTripPlans(String message) {
        return chatClient
                .prompt()
                .user(message)
                .call()
                .entity(new ParameterizedTypeReference<List<TripPlan>>() {});
    }

}