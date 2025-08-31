package org.example.seminar.dao;

import org.example.seminar.dto.BoardDto;
import org.example.seminar.entity.BoardEntity;
import org.example.seminar.repository.BoardRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.util.Iterator;
import java.util.Optional;

@Repository
public class BoardDao {
    private static final Logger logger = LoggerFactory.getLogger(BoardDao.class);
    private final BoardRepository boardRepository;

    public BoardDao(@Autowired BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    // CREATE
    public void createBoard(BoardDto dto) {
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setName(dto.getName());
        this.boardRepository.save(boardEntity);
    }

    // READ (단일)
    public BoardEntity readBoard(Long id) {
        Optional<BoardEntity> boardEntity = this.boardRepository.findById(id);
        if (boardEntity.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return boardEntity.get();
    }

    // READ (전체)
    public Iterator<BoardEntity> readBoardAll() {
        return this.boardRepository.findAll().iterator();
    }

    // UPDATE
    public void updateBoard(Long id, BoardDto dto ) {
        Optional<BoardEntity> targetEntity = this.boardRepository.findById(id);
        if (targetEntity.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        BoardEntity boardEntity = targetEntity.get();
        boardEntity.setName(dto.getName() == null ? boardEntity.getName() : dto.getName());
        this.boardRepository.save(boardEntity);
    }

    // DELETE
    public void deleteBoard(Long id) {
        Optional<BoardEntity> targetEntity = this.boardRepository.findById(id);
        if (targetEntity.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        this.boardRepository.delete(targetEntity.get());
    }

}
