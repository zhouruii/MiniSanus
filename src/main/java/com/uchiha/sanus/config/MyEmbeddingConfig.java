package com.uchiha.sanus.config;

import jakarta.annotation.Resource;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class MyEmbeddingConfig {

    @Resource
    private EmbeddingModel dashscopeEmbeddingModel;

    @Bean
    @Primary
    public EmbeddingModel myDefaultEmbedding() {
        return dashscopeEmbeddingModel;
    }
}

