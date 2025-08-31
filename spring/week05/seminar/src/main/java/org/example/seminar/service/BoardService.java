package org.example.seminar.service;

import org.example.seminar.dao.BoardDao;
import org.example.seminar.dto.BoardDto;
import org.example.seminar.entity.BoardEntity;
import org.example.seminar.entity.PostEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class BoardService {
    private static final Logger logger = LoggerFactory.getLogger(BoardService.class);
    private final BoardDao boardDao;

    public BoardService(@Autowired BoardDao boardDao) {
        this.boardDao = boardDao;
    }

    // CREATE
    public void createBoard(BoardDto dto){
        this.boardDao.createBoard(dto);
    }

    // READ (단일)
    public BoardDto readBoard(Long id) {
        BoardEntity boardEntity = this.boardDao.readBoard(id);
        List<Long> postIds = new ArrayList<>();
        for (PostEntity postEntity : boardEntity.getPostEntityList()) {
            postIds.add(postEntity.getId());
        }

        return new BoardDto(
                boardEntity.getId(),
                boardEntity.getName(),
                postIds
        );
    }

    // READ (전체)
    public List<BoardDto> readBoardsAll() {
        Iterator<BoardEntity> iterator = this.boardDao.readBoardAll();
        List<BoardDto> boardDtoList = new ArrayList<>();
        while (iterator.hasNext()) {
            BoardEntity boardEntity = iterator.next();

            List<Long> postIds = new ArrayList<>();
            for (PostEntity postEntity : boardEntity.getPostEntityList()) {
                postIds.add(postEntity.getId());
            }

            boardDtoList.add(new BoardDto(
                    boardEntity.getId(),
                    boardEntity.getName(),
                    postIds
            ));
        }
        return boardDtoList;
    }

    // UPDATE
    public void updateBoard(Long id, BoardDto dto) {
        this.boardDao.updateBoard(id, dto);
    }

    // DELETE
    public void deleteBoard(Long id) {
        this.boardDao.deleteBoard(id);
    }

}
