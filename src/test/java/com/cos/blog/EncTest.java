package com.cos.blog;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
public class EncTest {

    @Test
    public void 해쉬암호화(){
        String encPassword = new BCryptPasswordEncoder().encode("1234");
        System.out.println("encPassword = " + encPassword);
    }
}
