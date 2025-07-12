package com.uchiha.sanus.service;

import com.uchiha.sanus.config.ChatModelFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.InMemoryChatMemory;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.stereotype.Component;

import static org.springframework.ai.chat.client.advisor.AbstractChatMemoryAdvisor.CHAT_MEMORY_CONVERSATION_ID_KEY;
import static org.springframework.ai.chat.client.advisor.AbstractChatMemoryAdvisor.CHAT_MEMORY_RETRIEVE_SIZE_KEY;

@Component
@Slf4j
public class SimpleScienceApp {

    private final ChatModelFactory chatModelFactory;

    private final ChatMemoryRepository chatMemoryRepository;

    private static final String SYSTEM_PROMPT = """
            # 角色设定
            你是一位专业的科研协作AI助手，核心使命是通过以下方式提升研究效率：
            1. **精准支持** - 根据用户当前研究阶段提供针对性帮助
            2. **思维强化** - 帮助梳理逻辑而非替代思考
            3. **持续优化** - 每次交互自动记录研究上下文

            # 核心能力
            ▸ 论文写作：从提纲到润色的全流程支持 \s
            ▸ 文献处理：快速提取关键信息并建立关联 \s
            ▸ 数据呈现：指导制作清晰专业的图表 \s
            ▸ 方法论证：帮助检验研究设计的合理性 \s

            # 交互原则
            1. **启动时**主动询问： \s
               "当前需要协助的研究环节是？" \s
               （如文献综述/实验设计/结果分析/论文撰写/投稿选刊） \s

            2. **过程中**保持： \s
               ✓ 对专业术语自动标注解释 \s
               ✓ 提供可验证的参考文献（DOI/PMID） \s
               ✓ 区分"事实陈述"与"建议方案" \s

            3. **输出时**确保： \s
               ✦ 复杂概念配有示意图/类比说明 \s
               ✦ 技术建议附带实施步骤 \s
               ✦ 始终维护学术诚信底线 \s

            # 工作模式
            默认采用"问题定义→方案建议→执行反馈"的协作闭环，当检测到： \s
            逻辑矛盾 → 用思维导图梳理关系 \s
            表述模糊 → 提供STAR法则模板 \s
            压力词汇 → 推荐番茄工作法干预\s""";


    public SimpleScienceApp(ChatModelFactory chatModelFactory, ChatMemoryRepository chatMemoryRepository) {
        // 初始化基于内存的对话记忆
        this.chatModelFactory = chatModelFactory;

        // 核心区别：记忆是复用的
        this.chatMemoryRepository = chatMemoryRepository;
    }

    public String doChat(String modelName, String message, String chatId) {
        ChatModel model = chatModelFactory.getModel(modelName);

        // 核心区别：记忆是复用的
        ChatMemory chatMemory = chatMemoryRepository.getOrCreate(chatId);

        ChatClient chatClient = ChatClient.builder(model)
                .defaultSystem(SYSTEM_PROMPT)
                .defaultAdvisors(new MessageChatMemoryAdvisor(chatMemory))
                .build();
        ChatResponse response = chatClient
                .prompt()
                .user(message)
                .advisors(spec -> spec.param(CHAT_MEMORY_CONVERSATION_ID_KEY, chatId)
                        .param(CHAT_MEMORY_RETRIEVE_SIZE_KEY, 10))
                .call()
                .chatResponse();
        assert response != null;

        return response.getResult().getOutput().getText();
    }


}

