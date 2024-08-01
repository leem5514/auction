package com.example.beyond.ebey.bidding.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
//방 기준
public class BidResponse { // topic/key/count
    private final long auctionId;
    private final String userName;
    private final long highestBidAmount;
}
