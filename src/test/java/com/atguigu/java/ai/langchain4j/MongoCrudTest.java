package com.atguigu.java.ai.langchain4j;

import com.atguigu.java.ai.langchain4j.bean.ChatMessages;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

@SpringBootTest
public class MongoCrudTest {
    @Autowired
    private MongoTemplate mongoTemplate;

    // 插入文档
//    @Test
//    public void testInsert() {
//        mongoTemplate.insert(new ChatMessages(1L, "测试插入文档"));
//    }

    @Test
    public void testFind() {
        ChatMessages chatMessages = new ChatMessages();
        chatMessages.setContent("测试插入文档2，没有给定id");
        mongoTemplate.insert(chatMessages);
    }

    //查询测试
    @Test
    public void testFindById() {
        ChatMessages chatMessages = mongoTemplate.findById("682557c685ffdd1f0704be51", ChatMessages.class);
        System.out.println(chatMessages);
    }

    //修改测试
    @Test
    public void testUpdate() {
        Criteria criteria = Criteria.where("_id").is("682557c685ffdd1f0704be51");
        Query query = new Query(criteria);
        Update update = new Update();
        update.set("content", "测试修改文档llll");

        mongoTemplate.upsert(query, update, ChatMessages.class);
    }


    // 删除测试
    @Test
    public void testDelete() {
        Criteria criteria = Criteria.where("_id").is("682557c685ffdd1f0704be51");
        Query query = new Query(criteria);
        mongoTemplate.remove(query, ChatMessages.class);
    }

}
