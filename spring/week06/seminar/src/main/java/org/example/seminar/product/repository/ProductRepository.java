package org.example.seminar.product.repository;

import org.example.seminar.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    // JPA: 가장 비싼 상품 Top10 조회
    List<Product> findTop10ByOrderByPriceDesc();



}


