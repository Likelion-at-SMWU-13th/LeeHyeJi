package org.example.seminar.product.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.example.seminar.product.entity.Product;
import org.example.seminar.product.entity.QProduct;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    EntityManager em;

    // 더미 데이터 삽입
    @BeforeEach
    void setUp() {
        productRepository.save(new Product("연필", 500, 200));
        productRepository.save(new Product("샤프펜슬", 2000, 150));
        productRepository.save(new Product("모나미펜", 2000, 150));
        productRepository.save(new Product("펜", 2000, 230));
        productRepository.save(new Product("샤프", 2000, 182));
        productRepository.save(new Product("펜홀더", 2000, 392));
        productRepository.save(new Product("지우개", 800, 300));
        productRepository.save(new Product("볼펜", 1000, 500));
        productRepository.save(new Product("형광펜", 1200, 250));
        productRepository.save(new Product("노트", 1500, 400));
        productRepository.save(new Product("수첩", 2500, 180));
        productRepository.save(new Product("파일", 3000, 120));
        productRepository.save(new Product("바인더", 4500, 100));
        productRepository.save(new Product("자", 700, 350));
        productRepository.save(new Product("파란형광펜", 2000, 200));
        productRepository.save(new Product("펜형지우개", 2000, 392));
        productRepository.save(new Product("펜슬", 2000, 300));
        productRepository.save(new Product("가위", 2500, 80));
        productRepository.save(new Product("풀", 1000, 220));
        productRepository.save(new Product("테이프", 1500, 160));
        productRepository.save(new Product("색연필", 5000, 90));
        productRepository.save(new Product("크레파스", 6000, 70));
        productRepository.save(new Product("빨간펜", 2000, 200));
        productRepository.save(new Product("펜클립", 2300, 392));
        productRepository.save(new Product("3색볼펜", 3000, 100));
        productRepository.save(new Product("5색볼펜", 5200, 392));
    }

    // 과제
    @Test
    void queryDslHw() {
        JPAQueryFactory query = new JPAQueryFactory(em);
        QProduct qProduct = QProduct.product;

        // 이름에 '펜'이 들어가고 가격이 저렴한 상품 Top10 조회
        List<Product> productList = query.selectFrom(qProduct)
                .where(qProduct.name.contains("펜"))
                .orderBy(qProduct.price.asc())
                .limit(10)
                .fetch();

        for (Product product : productList) {
            System.out.println("---------------");
            System.out.println("Product Id : " + product.getId());
            System.out.println("Product Name : " + product.getName());
            System.out.println("Product Price : " + product.getPrice());
            System.out.println("Product Stock : " + product.getStock());
            System.out.println("---------------");
        }

    }
}