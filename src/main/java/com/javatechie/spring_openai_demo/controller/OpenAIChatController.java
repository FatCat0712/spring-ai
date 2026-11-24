package com.javatechie.spring_openai_demo.controller;

import com.javatechie.spring_openai_demo.service.MessageRolesDemoService;
import com.javatechie.spring_openai_demo.service.OpenAIChatService;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api")
public class OpenAIChatController {
    private final OpenAIChatService openAIChatService;
    private final MessageRolesDemoService messageRolesDemoService;

    public OpenAIChatController(OpenAIChatService openAIChatService, MessageRolesDemoService messageRolesDemoService) {
        this.openAIChatService = openAIChatService;
        this.messageRolesDemoService = messageRolesDemoService;
    }

    @GetMapping("/chat")
    public String chat(@RequestParam String message) {
        return openAIChatService.chatWithOpenAI(message);
    }

    @GetMapping(value = "/chat/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> chatStream(@RequestParam String message) {
        return openAIChatService.askToAIStream(message);
    }

    @GetMapping("/check/policy")
    public ChatResponse checkPolicy(@RequestParam String message) {
        return messageRolesDemoService.checkInsuranceV3Policy(message);
    }

    @GetMapping("/guide")
    public String guideUser(@RequestParam String topic, @RequestParam String level, @RequestParam Integer points) {
        return messageRolesDemoService.guideMe(topic, level, points);
    }
}
