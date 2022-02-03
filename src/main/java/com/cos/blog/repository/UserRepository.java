package com.cos.blog.repository;

import com.cos.blog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


// DAO
// 자동으로 bean등록이 된다.
// @Repository 생략 가능하다.
public interface UserRepository extends JpaRepository<User,Long> {

    @Query(value = "select * from user where username = ?1 and password = ?2",nativeQuery = true)
    User login(String username, String password);
}
