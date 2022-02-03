package com.cos.blog.service;

import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public void 회원가입(User user){
        userRepository.save(user);
    }

    @Transactional(readOnly = true) // Select 할때 트랜잭션 시작, 서비스 종료시에 트랜잭션 종료 (정합성)
    public User 로그인(User user){
        return userRepository.login(user.getUsername(), user.getPassword());
    }
}
