package com.uchiha.sanus.controller;

import com.uchiha.sanus.model.GetStartedRequest;
import com.uchiha.sanus.service.SimpleScienceApp;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/get_started")
public class GetStartedController {

    @Resource
    private SimpleScienceApp simpleScienceApp;

    @GetMapping()
    public String doQuickChat(String modelName, String message, String chatId) {

        return simpleScienceApp.doChat(modelName, message, chatId);
    }

}
