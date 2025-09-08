package org.example.seminar.product.service;

import lombok.RequiredArgsConstructor;
import org.example.seminar.product.entity.Product;
import org.example.seminar.product.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    // 상품 생성
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    // 가장 비싼 상품 Top10 조회
    public List<Product> findTop10ByOrderByPriceDesc() {
        return productRepository.findTop10ByOrderByPriceDesc();
    }

    // 가격이 2,000원 이하이고, 재고가 많은 상품 Top5 조회
    public List<Product> findTop5ByPriceAndStock(int price) {
        return productRepository.findTop5ByPriceAndStock(price);
    }
}
