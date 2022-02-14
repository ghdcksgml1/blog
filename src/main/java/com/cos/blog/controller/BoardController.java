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

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @Transactional
    @GetMapping({"","/"})
    public String index(Model model, @PageableDefault(size = 5,sort = "id",direction = Sort.Direction.DESC) Pageable pageable){
        Page<Board> boards = boardService.contentList(pageable);
        model.addAttribute("boards",boards);
        return "index"; // viewResolver 작동
    }

    @GetMapping("/board/saveForm")
    public String saveForm(){
        return "board/saveForm";
    }


}
