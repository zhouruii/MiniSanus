package com.uchiha.sanus.controller;

import com.uchiha.sanus.agent.MiniSanus;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/agent")
public class AgentController {

    @Resource
    private MiniSanus miniSanus;

    @GetMapping("/mini")
    public String interactWithMini(String message) {
        return miniSanus.run(message);
    }

}
