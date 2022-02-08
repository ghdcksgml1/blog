package com.cos.blog.controller;

import com.cos.blog.config.auth.PrincipalDetail;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class BoardController {

    @GetMapping({"","/"})
    public String index(@AuthenticationPrincipal PrincipalDetail principal){
        System.out.println("id:"+principal.getUsername());
        return "index";
    }
}
