package com.uchiha.sanus.config;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ChatModelFactory {

    private final Map<String, ChatModel> modelMap;

    public ChatModelFactory(Map<String, ChatModel> modelMap) {
        this.modelMap = modelMap;
    }

    public ChatModel getModel(String name) {
        ChatModel model = modelMap.get(name);
        if (model == null) {
            throw new IllegalArgumentException("不支持的模型: " + name);
        }
        return model;
    }
}

