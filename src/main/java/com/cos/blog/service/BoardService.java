package com.cos.blog.service;

import com.cos.blog.model.Board;
import com.cos.blog.model.User;
import com.cos.blog.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    @Transactional
    public void save(Board board, User user){
        board.setCount(0L);
        board.setUser(user);
        boardRepository.save(board);
    }

    @Transactional
    public Page<Board> contentList(Pageable pageable){
        return boardRepository.findAll(pageable);
    }

    @Transactional
    public Board contentView(Long id){
        Board board = boardRepository.findById(id).orElseThrow(() -> {
            return new IllegalArgumentException("no such data");
        });
        return board;
    }

    @Transactional
    public void delete(Long id){
        boardRepository.deleteById(id);
    }

    @Transactional
    public void update(Long id,Board board){
        Board findBoard = boardRepository.findById(id).orElseThrow(() -> {
            return new IllegalArgumentException("오류");
        });
        findBoard.setTitle(board.getTitle());
        findBoard.setContent(board.getContent());
        // 해당 함수로 종료시(Service가 종료될 때) 트랜잭션이 종료됩니다. 이때 더티체킹 - 자동 업데이트가 됨.
    }
}
