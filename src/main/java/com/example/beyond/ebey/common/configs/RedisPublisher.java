package com.example.beyond.ebey.common.configs;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@RequiredArgsConstructor
public class RedisPublisher {  // 가격과 채널명으로 들어가서 입찰

    private final RedisTemplate redisTemplate;

    public void publish(String channelName, int price) {
        redisTemplate.convertAndSend(channelName, price);
    }

}