package com.atguigu.java.ai.langchain4j;

import com.atguigu.java.ai.langchain4j.assistant.Assistant;
import com.atguigu.java.ai.langchain4j.assistant.MemoryChatAssistant;
import com.atguigu.java.ai.langchain4j.assistant.SeparateChatAssistant;
import dev.langchain4j.community.model.dashscope.QwenChatModel;

import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.response.ChatResponse;
import dev.langchain4j.service.AiServices;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.beans.Transient;
import java.util.Arrays;

@SpringBootTest
public class ChatMemoryTest {
    @Autowired
    private Assistant assistant;

    @Test
    public void testChat() {//没有聊天记忆
        String answer = assistant.chat("你好，我是环环");
        System.out.println(answer);

        String answer2 = assistant.chat("你好，我是谁");
        System.out.println(answer2);

    }

    //简单实现有记忆聊天，需要传很多上下文参数
    @Autowired
    private QwenChatModel qwenChatModel;

    @Test
    public void testChat2() {
        UserMessage userMessage = UserMessage.userMessage("你好，我是环环");
        ChatResponse chatResponse = qwenChatModel.chat(userMessage);
        AiMessage aiMessage = chatResponse.aiMessage();
        System.out.println(aiMessage.text());

        UserMessage userMessage2 = UserMessage.userMessage("你好，我是谁");
        ChatResponse chatResponse2 = qwenChatModel.chat(Arrays.asList(userMessage, aiMessage, userMessage2));
        AiMessage aiMessage2 = chatResponse2.aiMessage();
        System.out.println(aiMessage2.text());
    }

    @Test
    public void testChat3() {
        MessageWindowChatMemory chatMemory = MessageWindowChatMemory.withMaxMessages(10);
        //创建AIService
        Assistant assistant = AiServices
                .builder(Assistant.class)
                .chatLanguageModel(qwenChatModel)
                .chatMemory(chatMemory)
                .build();

        //调用service的接口
        String answer1 = assistant.chat("我是环环");
        System.out.println(answer1);
        String answer2 = assistant.chat("我是谁");
        System.out.println(answer2);
    }

    @Autowired
    private MemoryChatAssistant memoryChatAssistant;
    @Test
    public void testChat4() {//配置了有聊天记忆的
        String answer1 = memoryChatAssistant.chat("我是环环");
        System.out.println(answer1);
        String answer2 = memoryChatAssistant.chat("我是谁");
        System.out.println(answer2);
    }

    @Autowired
    private SeparateChatAssistant separateChatAssistant;
    @Test
    public void testChat5() {//配置了有聊天记忆的
        String answer1 = separateChatAssistant.chat(1, "我是环环");
        System.out.println(answer1);
        String answer2 = separateChatAssistant.chat(1, "我是谁");
        System.out.println(answer2);

        String answer3 = separateChatAssistant.chat(2, "我是谁");
        System.out.println(answer3);
    }
}
