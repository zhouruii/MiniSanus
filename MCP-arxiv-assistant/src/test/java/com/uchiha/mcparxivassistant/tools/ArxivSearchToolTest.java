package com.uchiha.mcparxivassistant.tools;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class ArxivSearchToolTest {

    @Resource
    private ArxivSearchTool arxivSearchTool;

    @Test
    void search() {
        String keyword = "machine learning";
        List<String> result = arxivSearchTool.search(keyword);
        System.out.println(result);
    }

}