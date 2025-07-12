package com.uchiha.sanus.config;

import com.alibaba.cloud.ai.dashscope.api.DashScopeApi;
import com.alibaba.cloud.ai.dashscope.chat.DashScopeChatModel;
import com.alibaba.cloud.ai.dashscope.chat.DashScopeChatOptions;
import jakarta.annotation.Resource;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChatModelConfig {

    @Resource
    private ChatModel ollamaChatModel;

    @Value("${spring.ai.dashscope.api-key}")
    private String ALI_KEY;

    @Bean("qwen-turbo")
    public ChatModel qwenTurboChatModel() {
        // 创建 QWEN_TURBO 模型
        return new DashScopeChatModel(new DashScopeApi(ALI_KEY),
                DashScopeChatOptions
                        .builder()
                        .withModel(DashScopeApi.ChatModel.QWEN_TURBO.getModel())
                        .build());
    }

    @Bean("qwen-plus")
    public ChatModel qwenPlusChatModel() {
        // 创建 QWEN_PLUS 模型
        return new DashScopeChatModel(new DashScopeApi(ALI_KEY),
                DashScopeChatOptions
                        .builder()
                        .withModel(DashScopeApi.ChatModel.QWEN_PLUS.getModel())
                        .build());
    }


    @Bean("local")
    public ChatModel localChatModel() {
        // 创建 DashScope 模型
        return ollamaChatModel;
    }

}

