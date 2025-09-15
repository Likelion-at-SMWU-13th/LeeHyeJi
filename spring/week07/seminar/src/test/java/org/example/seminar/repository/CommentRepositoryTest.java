package org.example.seminar.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.example.seminar.entity.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional
class CommentRepositoryTest {

    @Autowired
    EntityManager em;
    private JPAQueryFactory queryFactory;

    @Autowired
    WriterRepository writerRepository;

    @Autowired
    BoardRepository boardRepository;

    @Autowired
    CommentRepository commentRepository;

    @BeforeEach
    void setUp() {
        queryFactory = new JPAQueryFactory(em);

        Writer writerA = new Writer();
        writerA.setName("이혜지");
        writerA.setAge(25);

        Writer writerB = new Writer();
        writerB.setName("김숙멋");
        writerB.setAge(26);
        writerRepository.saveAll(List.of(writerA, writerB));

        Board board = new Board();
        board.setTitle("QueryDSL 테스트용 게시글");
        board.setContent("내용입니다.");
        board.setWriter(writerA);
        boardRepository.save(board);

        Comment comment1 = new Comment();
        comment1.setContent("이혜지의 첫 댓글입니다.");
        comment1.setWriter(writerA);
        comment1.setBoard(board);

        Comment comment2 = new Comment();
        comment2.setContent("김숙멋의 첫 번째 댓글입니다.");
        comment2.setWriter(writerB);
        comment2.setBoard(board);

        Comment comment3 = new Comment();
        comment3.setContent("이혜지의 두 번째 댓글입니다.");
        comment3.setWriter(writerA);
        comment3.setBoard(board);

        commentRepository.saveAll(List.of(comment1, comment2, comment3));
    }

    @Test
    // 특정 작성자가 쓴 댓글 조회
    void queryDslBasicTest() {
        QComment qComment = QComment.comment;

        List<Comment> comments = queryFactory
                .selectFrom(qComment)
                .where(qComment.writer.name.eq("이혜지"))
                .fetch();

        System.out.println("--- '이혜지'가 작성한 댓글 목록 ---");
        for (Comment comment : comments) {
            System.out.println(comment.getContent());
        }
    }

    @Test
    // 댓글 내용과 작성자 이름 선택적으로 조회
    void queryDslProjectionTest() {
        QComment qComment = QComment.comment;
        QWriter qWriter = QWriter.writer;

        List<com.querydsl.core.Tuple> result = queryFactory
                .select(qComment.content, qWriter.name)
                .from(qComment)
                .join(qComment.writer, qWriter)
                .fetch();

        System.out.println("--- 댓글 내용과 작성자 이름 목록 ---");
        for (com.querydsl.core.Tuple tuple : result) {
            String content = tuple.get(qComment.content);
            String writerName = tuple.get(qWriter.name);
            System.out.println("댓글 내용: " + content + " | 작성자: " + writerName);
        }
    }
}