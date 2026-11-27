package com.javatechie.spring_openai_demo.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rag")
public class RAGController {
    private final ChatClient chatClient;
    private final VectorStore vectorStore;

    @Value("classpath:prompts/system_data_prompt.st")
    private Resource template;

    public RAGController(ChatClient chatClient, VectorStore vectorStore) {
        this.chatClient = chatClient;
        this.vectorStore = vectorStore;
    }

    @GetMapping("/connect")
    public String connectToRagAI(@RequestParam String prompt, @RequestHeader String username) {
        // R - Retrieval query
        SearchRequest searchRequest = SearchRequest.builder()
                .query(prompt)
                .topK(3)
                .similarityThreshold(0.5)
                .build();

        // A - Augmentation
        List<Document> similarSearchDocuments = vectorStore.similaritySearch(searchRequest);

        // extract the text content from the retrieved documents
        List<String> similarResults = similarSearchDocuments.stream()
                .map(Document::getText)
                .toList();

        return chatClient
                .prompt()
                .system(promptSystemSpec -> promptSystemSpec
                        .text(template)
                        .param("documents", similarResults)
                )
                .advisors(advisorSpec ->
                        advisorSpec.param(ChatMemory.CONVERSATION_ID, username)
                )
                .user(prompt)
                .call()
                .content();
    }
}
