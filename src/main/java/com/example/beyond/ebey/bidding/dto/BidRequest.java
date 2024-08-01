package com.example.beyond.ebey.bidding.dto;

import lombok.Data;

@Data
// 구매자기준
public class BidRequest {

    private String userName;

    private long bidAmount;
}
