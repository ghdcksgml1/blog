package com.cos.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

// 인증이 안된 사용자들이 출입할 수 있는 경로인 /auth/** 허용
// 그냥 주소가 / 이면 index.html 허용
// static 이하에 있는 /js/**, /css/**, /image/** 허용

@Controller
public class UserController {

    @GetMapping("/auth/joinForm")
    public String joinForm(){

        return "user/joinForm";
    }

    @GetMapping("/auth/loginForm")
    public String loginForm(){

        return "user/loginForm";
    }
}
