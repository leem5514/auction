//package com.example.beyond.ebey.common.configs;
//
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
//import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
//import org.springframework.data.redis.serializer.StringRedisSerializer;
//
//@Configuration
//public class RedisC {
//    // application.yml 의 spring.redis.host 의 정보를 가져옴.
//    @Value("${spring.redis.host}")
//    public String host;
//
//    @Value("${spring.redis.port}")
//    public int port;
//
//    @Bean
//    @Qualifier("2")
//    public RedisConnectionFactory redisConnectionFactory() {
//        RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();
//        configuration.setHostName(host);
//        configuration.setPort(port);
//        configuration.setDatabase(1);
//        return new LettuceConnectionFactory(configuration);
//    }
//
//    @Bean
//    @Qualifier("2")
//    public RedisTemplate<String, Object> redisTemplate(@Qualifier("2") RedisConnectionFactory redisConnectionFactory) {
//        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
//        redisTemplate.setKeySerializer(new StringRedisSerializer());
//        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
//        redisTemplate.setConnectionFactory(redisConnectionFactory);
//        return redisTemplate;
//    }
//}