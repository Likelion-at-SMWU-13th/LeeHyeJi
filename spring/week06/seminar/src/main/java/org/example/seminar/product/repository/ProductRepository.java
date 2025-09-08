package org.example.seminar.product.repository;

import org.example.seminar.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    // JPA: 가장 비싼 상품 Top10 조회
    List<Product> findTop10ByOrderByPriceDesc();

    // JPQL: 가격이 2,000원 이하이고, 재고가 많은 상품 Top5 조회
    @Query("SELECT p FROM Product p WHERE p.price <= :price ORDER BY p.stock DESC LIMIT 5")
    List<Product> findTop5ByPriceAndStock(@Param("price") int price);

}


