package com.cos.blog.controller.api;

import com.cos.blog.config.auth.PrincipalDetail;
import com.cos.blog.controller.dto.ResponseDto;
import com.cos.blog.model.Board;
import com.cos.blog.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class BoardApiController {

    private final BoardService boardService;

    @PostMapping("/api/board")
    public ResponseDto<Integer> save(@RequestBody Board board, @AuthenticationPrincipal PrincipalDetail principal){
        boardService.save(board,principal.getUser());
        return new ResponseDto<>(HttpStatus.OK.value(), 1);
    }

    @DeleteMapping("/api/board/{id}")
    public ResponseDto<Long> deleteById(@PathVariable Long id){
        boardService.delete(id);
        return new ResponseDto<>(HttpStatus.OK.value(), 1L);
    }

    @PutMapping("/api/board/{id}")
    public ResponseDto<Long> updateById(@PathVariable Long id,@RequestBody Board board){
        boardService.update(id,board);
        return new ResponseDto<>(HttpStatus.OK.value(), 1L);
    }
}
