package com.javatechie.spring_openai_demo.controller;

import com.javatechie.spring_openai_demo.service.MultiModelChatService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//@RestController
@RequestMapping("/multi-model/api/chat")
public class MultiModelChatController {
    private final MultiModelChatService multiModelChatService;

    public MultiModelChatController(MultiModelChatService multiModelChatService) {
        this.multiModelChatService = multiModelChatService;
    }

    @GetMapping("/openai")
    public String chatWithOpenAI(@RequestParam("message") String message) {
        return multiModelChatService.chatWithOpenAI(message);
    }

    @GetMapping("/ollama")
    public String chatWithOllama(@RequestParam("message") String message) {
        return multiModelChatService.chatWithOllama(message);
    }
}
