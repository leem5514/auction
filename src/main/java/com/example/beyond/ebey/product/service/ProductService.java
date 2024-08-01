package com.example.beyond.ebey.product.service;

import com.example.beyond.ebey.member.domain.Member;
import com.example.beyond.ebey.member.domain.Role;
import com.example.beyond.ebey.member.repository.MemberRepository;
import com.example.beyond.ebey.product.domain.Product;
import com.example.beyond.ebey.product.dto.ProductSaveReqDto;
import com.example.beyond.ebey.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class ProductService {
    @Autowired
    private final ProductRepository productRepository;
    @Autowired
    private final MemberRepository memberRepository;
    private final String uploadDir = "C:/springboot_img";

    public ProductService(ProductRepository productRepository, MemberRepository memberRepository) {
        this.productRepository = productRepository;
        this.memberRepository = memberRepository;
    }

    @Transactional
    public Product createProduct(ProductSaveReqDto dto) throws IOException {
        Long memberId = dto.getMemberId(); // DTO에서 memberId 가져오기

        // Member 조회
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid member ID"));

        // Role 검증
        if (member.getRole() != Role.SELLER) {
            throw new IllegalArgumentException("Only sellers can create products.");
        }

        String imagePath = saveImage(dto.getImageFile());

        Product product = ProductSaveReqDto.toEntity(dto,imagePath, member);
        return productRepository.save(product);
    }

    private String saveImage(MultipartFile imageFile) throws IOException {
        if (imageFile == null || imageFile.isEmpty()) {
            throw new IllegalArgumentException("Image file is required.");
        }

        Files.createDirectories(Paths.get(uploadDir));

        String filename = System.currentTimeMillis() + "_" + imageFile.getOriginalFilename();
        Path filePath = Paths.get(uploadDir + filename);
        Files.write(filePath, imageFile.getBytes());

        return filePath.toString();
    }
}