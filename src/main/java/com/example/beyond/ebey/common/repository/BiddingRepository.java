package com.example.beyond.ebey.common.repository;

import com.example.beyond.ebey.bidding.domain.Bidding;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BiddingRepository extends JpaRepository<Bidding, Long> {
}