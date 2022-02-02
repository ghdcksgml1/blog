package com.cos.blog.test;

import org.springframework.web.bind.annotation.*;

// 사용자가 요청 -> 응답(HTML파일)
// @Controller

// 사용자가 요청 -> 응답(Data)
@RestController
public class HttpController {

    private static final String TAG = "HttpContoller : ";

    @GetMapping("/http/lombok")
    public String lombokTest(){
        Member m = Member.builder().username("ssar").password("!@34").email("ssar@nate.com").build();

        System.out.println(TAG+"getter : "+m.getId());
        m.setId(5000);
        System.out.println(TAG+"getter : "+m.getId());
        return "lombok test 완료";
    }

    // 인터넷 요청은 무조건 get만 가능
    // http://localhost:8080/http/get (select)
    @GetMapping("/http/get")
    public String getTest(Member m){
        return "get 요청 : "+m.getId()+" = "+m.getUsername();
    }

    // http://localhost:8080/http/post (insert)
    @PostMapping("/http/post")
    public String postTest(@RequestBody Member m){
        return "post 요청" + m.getId();
    }

    // http://localhost:8080/http/put (update)
    @PutMapping("/http/put")
    public String putTest(){
        return "put 요청";
    }

    // http://localhost:8080/http/delete (delete)
    @DeleteMapping("/http/delete")
    public String deleteTest(){
        return "delete 요청";
    }
}
