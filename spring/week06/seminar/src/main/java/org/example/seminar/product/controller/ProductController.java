package org.example.seminar.product.controller;

import lombok.RequiredArgsConstructor;
import org.example.seminar.product.entity.Product;
import org.example.seminar.product.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    // 가장 비싼 상품 Top10 조회
    @GetMapping("/jpa")
    public List<Product> findTop10ByOrderByPriceDesc() {
        return productService.findTop10ByOrderByPriceDesc();
    }

}
