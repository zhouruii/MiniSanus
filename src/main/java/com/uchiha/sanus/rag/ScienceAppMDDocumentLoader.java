package com.uchiha.sanus.rag;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.document.Document;
import org.springframework.ai.reader.markdown.MarkdownDocumentReader;
import org.springframework.ai.reader.markdown.config.MarkdownDocumentReaderConfig;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class ScienceAppMDDocumentLoader {

    private static final Logger log = LoggerFactory.getLogger(ScienceAppMDDocumentLoader.class);

    private final ResourcePatternResolver resourcePatternResolver;

    ScienceAppMDDocumentLoader(ResourcePatternResolver resourcePatternResolver) {
        this.resourcePatternResolver = resourcePatternResolver;
    }

    public List<Document> loadMarkdowns() {
        List<Document> allDocuments = new ArrayList<>();
        try {
            // 多个 Markdown 文件的路径
            Resource[] resources = resourcePatternResolver.getResources("classpath:documents/*.md");
            for (Resource resource : resources) {
                String fileName = resource.getFilename();
                // 提取文档倒数第 3 和第 2 个字作为标签
                assert fileName != null;
                String status = fileName.substring(fileName.length() - 8, fileName.length() - 5);
                MarkdownDocumentReaderConfig config = MarkdownDocumentReaderConfig.builder()
                        .withHorizontalRuleCreateDocument(true)
                        .withIncludeCodeBlock(false)
                        .withIncludeBlockquote(false)
                        .withAdditionalMetadata("filename", fileName)
                        .withAdditionalMetadata("status", status)
                        .build();
                MarkdownDocumentReader reader = new MarkdownDocumentReader(resource, config);
                allDocuments.addAll(reader.get());
            }
        } catch (IOException e) {
            log.error("Markdown 文档加载失败", e);
        }
        return allDocuments;
    }
}
