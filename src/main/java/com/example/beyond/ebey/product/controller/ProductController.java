package com.example.beyond.ebey.product.controller;

import com.example.beyond.ebey.product.domain.Product;
import com.example.beyond.ebey.product.dto.ProductSaveReqDto;
import com.example.beyond.ebey.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
public class ProductController {
    @Autowired
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/product/save")
    public ResponseEntity<?> createProduct(@RequestBody ProductSaveReqDto dto) {
        try {
            if (dto.getMemberId() == null) {
                return new ResponseEntity<>("Member ID is required", HttpStatus.BAD_REQUEST);
            }

            Product createdProduct = productService.createProduct(dto);
            return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}