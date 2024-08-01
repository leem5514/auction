package com.example.beyond.ebey.product.dto;

import com.example.beyond.ebey.member.domain.Member;
import com.example.beyond.ebey.product.domain.Product;
import com.example.beyond.ebey.product.domain.ProductStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductSaveReqDto {
    private String name;
    private String description;
    private ProductStatus status;
    private MultipartFile imageFile;
    private LocalDateTime deadline;
    private Integer startPrice;
    private Long memberId;

    public static Product toEntity(ProductSaveReqDto dto, String imagePath, Member member) {
        return Product.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .status(ProductStatus.BIDDING)
                .imagePath(imagePath)
                .deadline(dto.getDeadline())
                .startPrice(dto.getStartPrice())
                .nowPrice(dto.getStartPrice())
                .member(member)
                .build();
    }
}
