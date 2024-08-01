package com.example.beyond.ebey.bidding.controller;

import com.example.beyond.ebey.bidding.dto.BiddingDto;
import com.example.beyond.ebey.bidding.service.BiddingService;
import com.example.beyond.ebey.common.configs.RedisPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import javax.persistence.EntityNotFoundException;
import javax.xml.transform.Result;

import static com.example.beyond.ebey.bidding.service.BiddingService.PRODUCT_PRICE_CHANNEL_NAME;

@RestController
@RequiredArgsConstructor
public class BiddingController {

    private final BiddingService biddingService;
    private final RedisPublisher redisPublisher;

    @PostMapping("/bidding")
    public ResponseEntity<?> biddingProduct(@RequestBody BiddingDto dto) {
        biddingService.bidding(dto);
        redisPublisher.publish(PRODUCT_PRICE_CHANNEL_NAME + dto.getProductId(), dto.getPrice());
        return ResponseEntity.ok("경매방을 생성되었습니다.");

    }

    @GetMapping("/bidding/subscribe/{productId}")
    public SseEmitter priceSubscribe(@PathVariable Long productId) {
        return biddingService.priceSubscribe(productId);
    }

}