package com.cos.blog.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity // User 클래스가 MySQL에 테이블이 생성이 된다.
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
// @DynamicInsert // insert시에 null인 필드를 제외시켜준다.
public class User extends TimeZone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 프로젝트에서 연결된 DB의 넘버링 전략을 따라간다.
    private Long id;

    @Column(nullable = false,length = 30,unique = true)
    private String username;

    @Column(nullable = false,length = 100)
    private String password;

    @Enumerated(value = EnumType.STRING)
    @ColumnDefault("'USER'")
    private RoleType role;

    @Column(nullable = false,length = 50)
    private String email;

    // 쓴 게시글들
    @OneToMany(mappedBy = "user")
    private List<Board> boards = new ArrayList<>();

    // 쓴 댓글들
    @OneToMany(mappedBy = "writer")
    private List<Reply> replies = new ArrayList<>();
}
