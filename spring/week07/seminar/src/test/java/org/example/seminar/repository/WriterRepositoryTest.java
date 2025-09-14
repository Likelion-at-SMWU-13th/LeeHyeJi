package org.example.seminar.repository;

import org.example.seminar.entity.Board;
import org.example.seminar.entity.Writer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootTest
class WriterRepositoryTest {

    @Autowired
    WriterRepository writerRepository;

    @Autowired
    BoardRepository boardRepository;

    // Cascade 테스트: Writer 저장 시 Board 함께 저장
    @Test
    void cascadePersistTest() {
        Writer writer = new Writer();
        writer.setName("이혜지");

        Board board1 = new Board();
        board1.setTitle("첫 번째 게시물");
        board1.setContent("첫 번째 게시물의 내용입니다.");

        Board board2 = new Board();
        board2.setTitle("두 번째 게시물");
        board2.setContent("두 번째 게시물의 내용입니다.");

        writer.addBoard(board1);
        writer.addBoard(board2);

        writerRepository.save(writer);

        System.out.println("--- Writer가 작성한 Board 목록 ---");
        Writer foundWriter = writerRepository.findById(writer.getId()).get();
        for (Board board : foundWriter.getBoards()) {
            System.out.println("게시글 제목: " + board.getTitle() + " | 게시글 내용: " + board.getContent());
        }
    }
}