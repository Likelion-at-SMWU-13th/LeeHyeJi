package org.example.seminar.repository;

import org.example.seminar.entity.Board;
import org.example.seminar.entity.Comment;
import org.example.seminar.entity.Writer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class BoardRepositoryTest {

    @Autowired
    BoardRepository boardRepository;

    @Autowired
    WriterRepository writerRepository;

    // 다대일 관계 조회: Board 조회 시 연관된 Writer 정보 확인
    @Test
    void findBoardWithWriterTest() {
        Writer writer = new Writer();
        writer.setName("이혜지");
        writerRepository.save(writer);

        Board board = new Board();
        board.setTitle("이혜지의 첫 게시물");
        board.setWriter(writer);
        boardRepository.save(board);

        System.out.println("--- Board를 작성한 Writer 정보 ---");
        Board foundBoard = boardRepository.findById(board.getId()).get();
        System.out.println("게시글 제목: " + foundBoard.getTitle());
        System.out.println("게시글 작성자: " + foundBoard.getWriter().getName());
    }

    // 일대다 관계 조회: Board 조회 시 연관된 Comment 목록 확인
    @Test
    void findBoardWithCommentsTest() {
        Board board = new Board();
        board.setTitle("댓글이 달린 게시글");

        Comment comment1 = new Comment();
        comment1.setContent("첫 번째 댓글입니다.");

        Comment comment2 = new Comment();
        comment2.setContent("두 번째 댓글입니다.");

        board.addComment(comment1);
        board.addComment(comment2);
        boardRepository.save(board);

        System.out.println("--- Board에 달린 Comment 목록 ---");
        Board foundBoard = boardRepository.findById(board.getId()).get();
        for (Comment comment : foundBoard.getComments()) {
            System.out.println("댓글 내용: " + comment.getContent());
        }
    }



}