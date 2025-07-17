package com.uchiha.sanus.controller;

import com.uchiha.sanus.service.SimpleScienceApp;
import jakarta.annotation.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import reactor.core.publisher.Flux;

import java.io.IOException;

@RestController
@RequestMapping("/chat")
public class ChatController {

    @Resource
    private SimpleScienceApp simpleScienceApp;

    @GetMapping("/demo")
    public String doQuickChat(String modelName, String message, String chatId) {
        return simpleScienceApp.doChat(modelName, message, chatId);
    }

    @GetMapping(value = "/sse", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> chatWithSSE(String modelName, String message, String chatId) {
        return simpleScienceApp.chatWithSSE(modelName, message, chatId);
    }

    @GetMapping("/sse/emitter")
    public SseEmitter chatWithSSEEmitter(String modelName, String message, String chatId) {
        // 创建一个超时时间较长的 SseEmitter
        SseEmitter emitter = new SseEmitter(180000L); // 3分钟超时
        // 获取 Flux 数据流并直接订阅
        simpleScienceApp.chatWithSSE(modelName, message, chatId)
                .subscribe(
                        // 处理每条消息
                        chunk -> {
                            try {
                                emitter.send(chunk);
                            } catch (IOException e) {
                                emitter.completeWithError(e);
                            }
                        },
                        // 处理错误
                        emitter::completeWithError,
                        // 处理完成
                        emitter::complete
                );
        // 返回emitter
        return emitter;
    }


    @GetMapping("/rag")
    public String chatWithRAG(String modelName, String message, String chatId) {
        return simpleScienceApp.chatWithRAG(modelName, message, chatId);
    }

    @GetMapping("/tool")
    public String chatWithTool(String modelName, String message, String chatId) {
        return simpleScienceApp.chatWithTools(modelName, message, chatId);
    }

    @GetMapping("/mcp")
    public String chatWithMCP(String modelName, String message, String chatId) {
        return simpleScienceApp.chatWithMCP(modelName, message, chatId);
    }

}
