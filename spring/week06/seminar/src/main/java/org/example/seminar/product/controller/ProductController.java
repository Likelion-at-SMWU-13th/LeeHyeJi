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

    // 상품 생성
    @PostMapping("/create")
    public Product createProduct(@RequestBody Product product) {
        return productService.saveProduct(product);
    }

    // 가장 비싼 상품 Top10 조회
    @GetMapping("/jpa")
    public List<Product> findTop10ByOrderByPriceDesc() {
        return productService.findTop10ByOrderByPriceDesc();
    }

    // 가격이 2,000원 이하이고, 재고가 많은 상품 Top5 조회
    @GetMapping("/jpql")
    public List<Product> findTop5ByPriceAndStock(@RequestParam int price) {
        return productService.findTop5ByPriceAndStock(price);
    }
}
