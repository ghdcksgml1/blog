package com.cos.blog.test;

import lombok.*;

@Data
// @AllArgsConstructor // 전체 생성자
@NoArgsConstructor // 빈생성자
public class Member {
    private int id;
    private String username;
    private String password;
    private String email;

    @Builder
    public Member(int id, String username, String password, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
    }
}
