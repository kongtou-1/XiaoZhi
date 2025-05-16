package com.atguigu.java.ai.langchain4j;

import com.atguigu.java.ai.langchain4j.assistant.MemoryChatAssistant;
import com.atguigu.java.ai.langchain4j.assistant.SeparateChatAssistant;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PromptTest {
    @Autowired
    private SeparateChatAssistant separateChatAssistant;

    @Test
    public void testPrompt() {
        String answer1 = separateChatAssistant.chat(5, "你是谁，我是环环");
        System.out.println(answer1);
        String answer2 = separateChatAssistant.chat(5, "今天几号");
        System.out.println(answer2);
    }

    @Autowired
    private MemoryChatAssistant memoryChatAssistant;
    @Test
    public void testUserMessage() {
        String answer = memoryChatAssistant.chat("我是环环");
        System.out.println(answer);

        String answer2 = memoryChatAssistant.chat("我是谁");
        System.out.println(answer2);
    }

    @Test
    public void testUserMessage2() {
        String answer = separateChatAssistant.chat2(6, "我是环环");
        System.out.println(answer);

        String answer2 = separateChatAssistant.chat2(6, "我是谁");
        System.out.println(answer2);
    }

    @Test
    public void testUserMessage3() {
        String answer = separateChatAssistant.chat3(7, "hello啊，你是谁，我谁，我多大了", "环环", "18");
        System.out.println(answer);
    }
}
