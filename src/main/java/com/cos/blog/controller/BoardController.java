package com.cos.blog.controller;

import com.cos.blog.config.auth.PrincipalDetail;
import com.cos.blog.model.Board;
import com.cos.blog.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping({"","/"})
    public String index(Model model, @PageableDefault(size = 5,sort = "id",direction = Sort.Direction.DESC) Pageable pageable){
        Page<Board> boards = boardService.contentList(pageable);
        model.addAttribute("boards",boards);
        return "index"; // viewResolver 작동
    }

    @GetMapping("/board/{id}")
    public String boardView(Model model,@PathVariable Long id,@AuthenticationPrincipal PrincipalDetail principal){
        Board board = boardService.contentView(id);
        model.addAttribute("board",board);
        model.addAttribute("principal",principal);
        return "/board/viewForm";
    }

    @GetMapping("/board/saveForm")
    public String saveForm(){
        return "board/saveForm";
    }

    @GetMapping("/board/{id}/updateForm")
    public String updateForm(@PathVariable Long id, Model model){
        Board board = boardService.contentView(id);
        model.addAttribute("board",board);
        return "/board/updateForm";
    }
}
