package com.cos.blog.controller.dto;

import com.cos.blog.model.Board;
import com.cos.blog.model.Reply;
import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Long id;
    private String username;
    private String password;
    private RoleType role;
    private String email;

    public UserDto(Long id,String username){
        this.id = id;
        this.username = username;
    }

    public static UserDto form(User user) {
        return new UserDto(user.getId(),user.getUsername());
    }
}
