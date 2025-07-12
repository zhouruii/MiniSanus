package com.uchiha.sanus.service;

import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.InMemoryChatMemory;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class ChatMemoryRepository {

    private final Map<String, ChatMemory> memoryMap = new ConcurrentHashMap<>();

    public ChatMemory getOrCreate(String chatId) {
        return memoryMap.computeIfAbsent(chatId, id -> new InMemoryChatMemory());
    }
}

