package com.example.beyond.ebey.product.repository;

import com.example.beyond.ebey.member.domain.Member;
import com.example.beyond.ebey.product.domain.Product;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Modifying
    @Query("update Product p set p.nowPrice=:price, p.lastBiddingMemberId=:memberId where p.id=:id and p.nowPrice<:price")
    int updateProductPrice(@Param("id") Long id, @Param("price") Integer price, @Param("memberId") Long memberId);

}
