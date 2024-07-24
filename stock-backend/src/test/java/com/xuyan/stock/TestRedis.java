package com.xuyan.stock;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
public class TestRedis {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Test
    public void testRedis() {
        redisTemplate.opsForValue().set("name", "xuyan");
        String myName = redisTemplate.opsForValue().get("name");
        System.out.println(myName);
    }
}
