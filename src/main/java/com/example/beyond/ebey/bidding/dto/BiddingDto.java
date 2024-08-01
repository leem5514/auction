package com.example.beyond.ebey.bidding.dto;

import com.example.beyond.ebey.bidding.domain.Bidding;
import lombok.Data;

@Data
public class BiddingDto {

    private Long productId;
    private Long memberId;
    private Integer price;

    public static Bidding toEntity(BiddingDto dto) {
        return Bidding.builder().price(dto.getPrice()).build();
    }

}