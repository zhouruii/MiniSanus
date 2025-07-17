package com.uchiha.sanus.controller;

import com.uchiha.sanus.agent.MiniSanus;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
@RequestMapping("/agent")
public class AgentController {

    @Resource
    private MiniSanus miniSanus;

    @GetMapping("/mini/demo")
    public String interactWithMini(String message) {
        return miniSanus.run(message);
    }

    @GetMapping("/mini/sse")
    public SseEmitter interactWithStreamedMini(String message) {
        return miniSanus.runStream(message);
    }

}
