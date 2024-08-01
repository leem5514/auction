package com.example.beyond.ebey.product.domain;

import com.example.beyond.ebey.common.domain.BaseTimeEntity;
import com.example.beyond.ebey.member.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private String name; // 제품명

    private String description;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private ProductStatus status = ProductStatus.BIDDING;

    private String imagePath;

    private LocalDateTime deadline; // 경매 마감 시간

    private Integer startPrice; // 시작가

    private Integer nowPrice; // 현재가

    private Long lastBiddingMemberId; // 최종 입찰자 id



}
