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

    // 가장 비싼 상품 Top10 조회
    public List<Product> findTop10ByOrderByPriceDesc() {
        return productRepository.findTop10ByOrderByPriceDesc();
    }

}
