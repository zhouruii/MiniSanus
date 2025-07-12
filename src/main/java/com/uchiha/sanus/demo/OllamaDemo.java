package com.uchiha.sanus.demo;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;

public class OllamaDemo {

    private final ChatModel chatModel;

    public OllamaDemo(ChatModel chatModel) {
        this.chatModel = chatModel;
    }

    public String chat(String prompt) {
        ChatResponse response = chatModel.call(new Prompt(prompt));
        return response.getResult().getOutput().getText();
    }

}
