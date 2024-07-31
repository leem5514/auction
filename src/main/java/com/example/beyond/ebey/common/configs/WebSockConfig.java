package com.example.beyond.ebey.common.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;


// registerstomp : script -> 외부 m
// pub/sub <- 내부 (인 메모리)
// redis pub / sub 구조와 함께 registerStomp 을 사용해서 pub -> redis -> n subscriber
// (n)client -> loadbalancer -> server(n) -> redis

//MESSAGE
//destination: /topic/auction/820
//content-type: application/json -> {"auctionId": pId, "userName": "member_Name", "highestBidAmount": price}
// subscriber - M전달-> 위 json 데이터 / 이렇게 구현하면 session 따로 필요 x (내부 인메모리 관리)
@Configuration
@EnableWebSocketMessageBroker
public class WebSockConfig implements WebSocketMessageBrokerConfigurer {
    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/sub");
        config.setApplicationDestinationPrefixes("/pub");
    }
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws-stomp") // end p
                .setAllowedOriginPatterns("*")
                .withSockJS();
    }
}
