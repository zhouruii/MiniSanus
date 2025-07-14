package com.uchiha.sanus.controller;

import com.uchiha.sanus.service.SimpleScienceApp;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/get_started")
public class GetStartedController {

    @Resource
    private SimpleScienceApp simpleScienceApp;

    @GetMapping("/demo")
    public String doQuickChat(String modelName, String message, String chatId) {
        return simpleScienceApp.doChat(modelName, message, chatId);
    }

    @GetMapping("/rag")
    public String chatWithRAG(String modelName, String message, String chatId) {
        return simpleScienceApp.chatWithRAG(modelName, message, chatId);
    }

}
