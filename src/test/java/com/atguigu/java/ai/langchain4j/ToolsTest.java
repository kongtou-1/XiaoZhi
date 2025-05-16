package com.atguigu.java.ai.langchain4j;

import com.atguigu.java.ai.langchain4j.assistant.SeparateChatAssistant;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ToolsTest {//大语言模型的tool
    @Autowired
    private SeparateChatAssistant separateChatAssistant;

//    @Test
//    public void testTools() {
//        String answer = separateChatAssistant.chat(1, "1+2=?, 22356765445678的平方更多少");
//        System.out.println(answer);
//    }
}
