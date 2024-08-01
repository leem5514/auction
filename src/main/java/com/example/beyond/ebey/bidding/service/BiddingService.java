package com.example.beyond.ebey.bidding.service;

import com.example.beyond.ebey.bidding.domain.Bidding;
import com.example.beyond.ebey.bidding.dto.BiddingDto;
import com.example.beyond.ebey.common.configs.RedisSubscriber;
import com.example.beyond.ebey.common.repository.BiddingRepository;
import com.example.beyond.ebey.common.repository.SseEmitterRepository;
import com.example.beyond.ebey.member.domain.Member;
import com.example.beyond.ebey.member.repository.MemberRepository;
import com.example.beyond.ebey.product.domain.Product;
import com.example.beyond.ebey.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;

@Log4j2
@Service
public class BiddingService {

    public final static String PRODUCT_PRICE_CHANNEL_NAME = "productPrice_";
    private final Long DEFAULT_TIMEOUT = 10L * 60 * 1000; // 10분

    private final ProductRepository productRepository;
    private final MemberRepository memberRepository;
    private final BiddingRepository biddingRepository;
    private final SseEmitterRepository sseEmitterRepository;
    private final RedisSubscriber redisSubscriber;

    public BiddingService (ProductRepository productRepository, MemberRepository memberRepository, BiddingRepository biddingRepository, SseEmitterRepository sseEmitterRepository, RedisSubscriber redisSubscriber) {
        this.productRepository = productRepository;
        this.memberRepository = memberRepository;
        this.biddingRepository = biddingRepository;
        this.sseEmitterRepository = sseEmitterRepository;
        this.redisSubscriber = redisSubscriber;
    }

    @Transactional
    public void bidding(BiddingDto biddingDto) {

        Long memberId = biddingDto.getMemberId();

        Member member = memberRepository.findById(memberId).orElseThrow(()->new EntityNotFoundException("회원 정보가 없습니다."));

        Integer price = biddingDto.getPrice();
        Long productId = biddingDto.getProductId();

        Product updateProduct = productRepository.findById(productId).orElseThrow(()->new EntityNotFoundException("상품 정보가 없습니다."));

        // 변경되면 1, 변경 안되면 0 반환
        int updateCheck = productRepository.updateProductPrice(productId, price, memberId);

        if (updateCheck == 1) {
            Bidding bidding = BiddingDto.toEntity(biddingDto);
            bidding.member = member; // 필드 직접 설정
            bidding.product = updateProduct; // 필드 직접 설정
            biddingRepository.save(bidding);
        } else {
            throw new IllegalArgumentException("가격이 틀렸습니다.");
        }

    }

    public SseEmitter priceSubscribe(Long productId) {
        String channelName = PRODUCT_PRICE_CHANNEL_NAME + productId;
        redisSubscriber.createChannel(channelName);

        SseEmitter sseEmitter = new SseEmitter(DEFAULT_TIMEOUT);
        sseEmitterRepository.save(channelName, sseEmitter);

        try {
            // sse 연결 후 유효 기간이 끝나지 않을 때까지 데이터를 보내지 않으면 503 에러 발생.
            // 따라서 더미 데이터를 보내준다
            sseEmitter.send(SseEmitter.event().data("init"));
        } catch (IOException e) {
            sseEmitterRepository.delete(channelName,sseEmitter);
        }
        return sseEmitter;
    }

}