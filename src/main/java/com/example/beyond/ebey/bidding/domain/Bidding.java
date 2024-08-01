package com.example.beyond.ebey.bidding.domain;

import com.example.beyond.ebey.common.domain.BaseTimeEntity;
import com.example.beyond.ebey.member.domain.Member;
import com.example.beyond.ebey.product.domain.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

// 입찰 도메인 -> 가격 증가율에 따른 가격 변화 , member, 상품 필요
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Bidding extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    public Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    public Product product;

    private int price;
}