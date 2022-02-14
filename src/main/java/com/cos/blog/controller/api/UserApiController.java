package com.cos.blog.controller.api;

import com.cos.blog.controller.dto.ResponseDto;
import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequiredArgsConstructor
public class UserApiController {

    private final UserService userService;

    @PostMapping("/auth/joinProc")
    public ResponseDto<Long> save(@RequestBody User user){
        userService.회원가입(user);
        return new ResponseDto<Long>(HttpStatus.OK.value(),1L);
    }

    @PutMapping("/user")
    public ResponseDto<Long> update(@RequestBody User user){
        userService.회원수정(user);
        return new ResponseDto<>(HttpStatus.OK.value(), 1L);
    }

}
