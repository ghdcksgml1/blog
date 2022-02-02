package com.cos.blog.test;

import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

// html파일이 아니라 data를 리턴해주는 controller
@RestController
@RequiredArgsConstructor
public class DummyControllerTest {

     // 의존성 주입(DI)
    private final UserRepository userRepository;

    // save함수는 id를 전달하지 않으면 insert를 해주고
    // save함수는 id를 전달하면 해당 id에 대한 데이터가 있으면 update를 해주고
    // save함수는 id를 전달하면 해당 id에 대한 데이터가 없으면 insert를 해요.

    @DeleteMapping("/dummy/user/{id}")
    public String delete(@PathVariable Long id){
        try {
            userRepository.deleteById(id);
        }catch(EmptyResultDataAccessException e){
            return "삭제에 실패하였습니다. 해당 id는 DB에 없습니다.";
        }
        return "삭제 되었습니다. id: "+id;
    }

    @Transactional // 더티 체킹 (함수 종료시에 자동 commit이 됨.)
    @PutMapping("/dummy/user/{id}")
    public User updateUser(@PathVariable Long id,@RequestBody User requestUser){ //json데이터를 요청=>Java Object(MessageConverter의 Jackson라이브러리)로 변환요청
        System.out.println("id = " + id);
        System.out.println("password = " + requestUser.getPassword());
        System.out.println("email = " + requestUser.getEmail());

        User user = userRepository.findById(id).orElseThrow(()->{
            return new IllegalArgumentException("수정에 실패하였습니다.");
        });
        user.setUsername(requestUser.getUsername());
        user.setPassword(requestUser.getPassword());
        user.setEmail(requestUser.getEmail());

//        userRepository.save(user);
        return user;
    }

    @GetMapping("/dummy/user")
    public List<User> list(){
        return userRepository.findAll();
    }

    // 한페이지당 2건에 데이터를 리턴받아 볼 예정
    @GetMapping("/dummy/user/page")
    public List<User> pageList(@PageableDefault(size=2,sort="id",direction= Sort.Direction.DESC)Pageable pageable){
        List<User> users = userRepository.findAll(pageable).getContent();
        return users;
    }

    @GetMapping("/dummy/user/{id}")
    public User detail(@PathVariable("id") Long id){
        // user/4을 찾으면 내가 데이터베이스에서 못찾아오게 되면 user가 null이 될것아냐?
        // 그럼 return 할때 null이 리턴되니까 프로그램에 문제가 되지 않겠니?
        // Optional로 너의 user객체를 감싸서 가져올테니 null인지 아닌지 판단해서 return해!
        User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
            @Override
            public IllegalArgumentException get() {
                return new IllegalArgumentException("해당 유저는 없습니다. id: "+id);
            }
        });
        // 요청: 웹브라우저
        // user 객체: 자바 오브젝트
        // 변환(웹브라우저가 이해할 수 있는 데이터) -> json
        // 스프링부트 = MessageConverter라는 애가 응답시에 자동 작동
        // 만약에 자바 오브젝트를 리턴하게 되면, MessageConverter가 Jackson이라는 라이브러리를 호출해서
        // user 오브젝트를 json으로 변환해서 브라우저에게 던져줍니다.
        return user;
    }

    @PostMapping("/dummy/join")
    public String join(User user){
        System.out.println("id = " + user.getId());
        System.out.println("username = " + user.getUsername());
        System.out.println("password = " + user.getPassword());
        System.out.println("email = " + user.getEmail());
        userRepository.save(user);
        return "회원가입이 완료되었습니다.";
    }
}
