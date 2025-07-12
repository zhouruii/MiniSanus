package com.uchiha.sanus.demo;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SpringAIDemoTest {

    @Resource
    private ChatModel dashScopeChatModel;

    @Test
    void test_demo() {
        SpringAIDemo springAIDemo = new SpringAIDemo(dashScopeChatModel);
        String result = springAIDemo.chat("是否了解西安电子科技大学？");
        System.out.println(result);
    }
}