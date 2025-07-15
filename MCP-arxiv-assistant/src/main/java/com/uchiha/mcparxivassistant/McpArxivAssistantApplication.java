package com.uchiha.mcparxivassistant;

import com.uchiha.mcparxivassistant.tools.ArxivSearchTool;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class McpArxivAssistantApplication {

    public static void main(String[] args) {
        SpringApplication.run(McpArxivAssistantApplication.class, args);
    }

    @Bean
    public ToolCallbackProvider ArxivSearchTools(ArxivSearchTool arxivSearchTool) {
        return MethodToolCallbackProvider.builder()
                .toolObjects(arxivSearchTool)
                .build();
    }
}
