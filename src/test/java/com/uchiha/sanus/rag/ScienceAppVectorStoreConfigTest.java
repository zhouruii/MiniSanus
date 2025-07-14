package com.uchiha.sanus.rag;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ScienceAppVectorStoreConfigTest {

    @Resource
    private ScienceAppMDDocumentLoader scienceAppMDDocumentLoader;

    @Resource
    private VectorStore scienceAppPGVectorVectorStore;

    @Resource
    private AIKeywordEnricher aiKeywordEnricher;

    @Test
    void test_pg_store() {
        List<Document> documents = scienceAppMDDocumentLoader.loadMarkdowns();
        assertNotNull(documents);
        List<Document> enrichedDocuments = aiKeywordEnricher.enrichDocuments(documents);
        scienceAppPGVectorVectorStore.add(enrichedDocuments);
    }
}