# 🧤 Blog

## 📁 application.yml

```yml
server:
  port: 8000 # 서버 포트설정
  servlet:
    context-path: /blog # 진입점 : localhost:8000/blog/

spring:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver # mysql 설정
    url: jdbc:mysql://localhost:3306/blog?serverTimezone=Asia/Seoul
    username: root
    password: hks13579

  jpa: # jpa 설정
    open-in-view: true # lazy Loading
    hibernate:
      ddl-auto: update # create:생성모드, update:업데이트모드, none: 생성,업데이트 (x)
      naming:
        physical-strategy: org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl # 테이블을 만들때 변수명 그대로 테이블에 넣어줌.
      use-new-id-generator-mappings: false # false: jpa가 사용하는 기본 넘버링 전략을 따라가지 않는다.
    show-sql: true # 쿼리 보여주기
    properties:
      hibernate.format_sql: true # 쿼리 예쁘게 보여주기

  jackson:
    serialization:
      fail-on-empty-beans: false

```
<br/>

## 📁 Http 통신

- 패킷 스위칭
  
  A가 B에게 '가나다라'라는 데이터를 보낼 때 '가','나','다','라 이렇게 패킷단위로 쪼개서 보내서 전송한다.
  
  C라는 애가 B에게 데이터를 전송하고 싶은 경우에는 A와 선을 공유한다.
  
  단점 : 동시에 보내게 될 경우, 하나의 선을 사용하므로 A와 C의 데이터가 섞이게 된다.
  
  그래서 해결책으로 패킷에 헤더를 추가시켜 어떤 곳에서 온 녀석인지 구분한다.

- 서킷 스위칭

  A와 B가 데이터를 교환할때 데이터를 한방에 실어서 전송한다.
  
  단점 : C라는 애가 B에게 데이터를 전송하고 싶으면 선을 하나 추가해야한다.
  
  속도는 빠르지만, 비용이 많이 듬.
  
  <br/>
  
 ## 📁 MIME 타입
 
 **MIME 타입**이란 클라이언트에게 전송된 문서의 다양성을 알려주기 위한 메커니즘입니다.
 
 ### 문법
  
 ```
 type/subtype
 ```
  
 '/'로 구분된 두개의 문자열인 타입과 서브타입으로 구성된다. (스페이스는 허용 X)
  
 ### 개별타입
  
 ```
 text/plain
 text/html
 image/jpeg
 image/png
 audio/mpeg
 audio/ogg
 audio/*
 video/mp4
 application/octet-stream
 ...
 ```
  
**MIME 타입 전체목록 :** https://developer.mozilla.org/ko/docs/Web/HTTP/Basics_of_HTTP/MIME_types/Common_type
  
<br/>
  
## 📁 Http 요청 실습
  
```java
@Controller // 사용자가 요청하면 HTML파일을 응답
@RestController // 사용자가 요청하면 Data를 응답
```
  
```java
  // 사용자가 요청 -> 응답(HTML파일)
// @Controller

// 사용자가 요청 -> 응답(Data)
@RestController
public class HttpController {

    // 인터넷 요청은 무조건 get만 가능
    // http://localhost:8080/http/get (select)
    @GetMapping("/http/get")
    public String getTest(){
        return "get 요청";
    }

    // http://localhost:8080/http/post (insert)
    @PostMapping("/http/post")
    public String postTest(){
        return "post 요청";
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
```

### 자바에서 변수는 다 private으로 만든다.

그 이유는 변수에 직접 접근해서 값을 수정하는 것은 객체지향에 맞지 않으므로

메소드를 public으로 만들어 메소드로 값을 변경하도록 해준다.

<br/>

### @GetMapping

@RequestParam("변수명") : get요청으로 들어온 변수를 읽어들일 수 있다.

매개변수를 아래와 같이 객체로 받으면 한꺼번에 받을 수 있다. (setter 필요함)

```java
...
  @GetMapping("/http/get")
  public String get(Member m){
    return m.getId() + m.getUsername();
  }
...
```

### @PostMapping

PostMapping 또한 객체로 매개변수를 받을 수 있다. (html에서 form태그로 보냈을때)

JSON 데이터를 받기 위해서는 @ResponseBody 태그를 써줘야한다.

```java
// http://localhost:8080/http/post (insert)
  @PostMapping("/http/post")
  public String postTest(@RequestBody Member m){
      return "post 요청" + m.getId();
  }
  ...
```

위 코드를 실행해서 JSON 형식으로 POST요청을 보내면 JSON이 자바의 Member 객체로 잘 들어간다.

이 과정 즉, JSON => 자바 객체 로 바꾸어주는 과정을 스프링 부트의 MessageConverter가 수행한다.

나머지 @PutMapping과 @DeleteMapping도 마찬가지로 @PostMapping과 비슷한 일을 수행한다.
  
<br/>
  
## 📁 AJAX 요청

일단, ajax 요청을 하기 위해서는 자바스크립트의 도움이 필요하다.

static폴더에 js 폴더를 하나 만들어 user.js를 만들자.

```javascript
// 폴더 경로 : /resources/static/js/user.js
let index = {
    init:function(){
        // btn-save 버튼이 클릭되면, save함수를 호출
        document.querySelector("#btn-save").addEventListener('click',()=>{
            this.save();
        });
    },

    save:function(){
        let data = {
            username: document.querySelector("#username").value,
            password: document.querySelector("#password").value,
            email: document.querySelector("#email").value
        }

        // console.log(data);

        // ajax 요청
        fetch('/blog/api/user',{
            method:'POST',headers:{'content-type':'application/json'},body:JSON.stringify(data)
        })
            .then(response => response.json())
            .then(data=>{
                alert("회원가입 완료");
                console.log(data);
                // location.href="/blog";
            })
            .catch(error=>{alert(error.message)});
    }
};

index.init();
```

위와 같이 jQuery 대신에 Javascript로 ajax 요청을 하기위해서는 fetch 함수가 필요하다.

fetch함수는 Promise객체로 리턴이 되기 때문에, 별도의 response.json()과 같이 Promise객체 => json 으로의 변환이 필요하다.

### ajax 사용 시 장점

- 웹페이지의 속도가 향상된다.
- 서버의 처리가 완료될 때까지 기다리지 않고 처리가 가능하다.
- 서버에서 Data만 전송하면 되므로 전체적인 코딩의 양이 줄어든다.
- 기존 웹에서는 불가능했던 다양한 UI를 가능하게 해준다.

<br/>

### ajax 사용 시 단점

- 히스토리 관리가 되지 않는다.
- 페이지 이동 없는 통신으로 인한 보안상의 문제가 있다.
- 연속으로 데이터를 요청하면 서버 부하가 증가할 수 있다.
- ajax를 쓸 수 없는 브라우저에서 문제가 발생한다.
- HTTP 클라이언트의 기능이 한정되어 있다.
- 지원하는 Charset이 한정되어 있다.
- script로 작성되므로 디버깅이 용이하지 않다.
- 동일-출처 정책으로 다른 도메인과는 통신이 불가능하다.

<br/>

## 📁 스트링 부트의 트랜잭션

전통적인 방식은 영속성 컨텍스트, JDBC, 트랜잭션을 모두 같은 구간에서 연결했지만, 

- lazy Loading을 위해, 세션의 시작은 서블릿이 시작되는 시점부터 (세션은 영속성 컨텍스트를 포함)

- 트랜잭션과 JDBC의 시작은 Service 레이어부터

- 트랜잭션과 JDBC의 종료는 Service 레이어에서 종료

- 세션(영속성 컨텍스트)은 컨트롤러 영역까지 끌고가기 때문에 영속성이 보장되어 select가 가능해지고, lazy Loading이 가능해진다.

<img width="782" alt="스크린샷 2022-02-03 오후 6 06 27" src="https://user-images.githubusercontent.com/79779676/152312415-d7b9bb42-d790-4007-b240-23fe5de43dff.png">

<br/>

### - 전통적인 방식

전통적인 방식은 아래와같이 세션 시작시점에 영속성 컨텍스트, JDBC, Transaction 연결을 한번에 실행하고 한번에 종료한다.

<img width="896" alt="스크린샷 2022-02-03 오후 6 30 45" src="https://user-images.githubusercontent.com/79779676/152316840-86c0a596-10ab-4101-9aca-e986cb885c31.png">

<br/>

### - lazy Loading 방식

lazy Loading 방식은 위에서 설명한것처럼 세션이 시작될때 영속성 컨텍스트가 실행되고, Service 시점부터 JDBC와 Transaction의 연결이 연결되고, 끊긴다.

아래의 그림은 최초 선수 정보를 가져왔을때인데, 선수 정보는 객체를 가져왔지만, 선수 정보의 외래키인 팀 정보는 프록시 객체로 가져온다.

<img width="912" alt="스크린샷 2022-02-03 오후 6 35 04" src="https://user-images.githubusercontent.com/79779676/152317121-cb019e98-f9e8-4e62-bb17-7125e46209fe.png">

<br/>

이렇게 선수 정보만 필요한 경우에는 Controller에서 그냥 쓰면 된다.

만약, 팀 정보를 쓸일이 있어 팀 정보의 프록시 객체를 호출하게 되면, 다시 JDBC 연결을 통해 select문을 실시해 프록시 객체가 아닌 진짜 팀 정보 객체를 가져와준다.

<img width="919" alt="스크린샷 2022-02-03 오후 6 35 52" src="https://user-images.githubusercontent.com/79779676/152317768-2cf9bb01-410f-494e-bb44-0576599ba57b.png">

### 사용법

Lazy Loading 방식을 쓰기 위해서는 application.yml의 jpa 설정에서 open-in-view: true 설정을 해주면 된다. ( 맨위에 나와있음. 그리고 Default는 true임 )

만약, open-in-view: false를 쓰게된다면, Service 계층에서 영속성 컨텍스트와 JDBC, Transaction 연결이 연결되고 끊기게 된다.

즉, Controller에서 영속성 컨텍스트로 접근이 불가하다.

## 📁 전통적인 로그인 방식

### 전통적인 로그인 구현

userService에서 로그인 함수를 실행시키고, ( 로그인 함수는 @RequestBody로 받은 User의 아이디와 패스워드가 일치하는지 확인 )

아래의 코드처럼 HttpSession 을 통해 세션을 생성한다.

```java
    @PostMapping("/api/user/login")
    public ResponseDto<Integer> login(@RequestBody User user, HttpSession session){
        User principal = userService.로그인(user);
        if(principal != null){
            session.setAttribute("principle",principal);
        }
        return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
    }
```

thymeleaf에서 세션값을 통해 세션별 표시할 정보를 구분해준다.

```html
<!-- fragment bodyHeader 파일 -->
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<div th:fragment="bodyHeader">
    <nav class="navbar navbar-expand-md bg-dark navbar-dark">
        <a class="navbar-brand" href="/blog">홈</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="collapsibleNavbar">
            <ul th:if="${session.principle == null}" class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link" href="/blog/user/loginForm">로그인</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/blog/user/joinForm">회원가입</a>
                </li>
            </ul>
            <ul th:if="${session.principle != null}" class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link" href="/blog/board/writeForm">글쓰기</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/blog/user/userForm">내 정보</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/blog/user/logout">로그아웃</a>
                </li>
            </ul>
        </div>
    </nav>
</div>
```

<br/>

## 📁 스프링 시큐리티

[참고자료](https://sjh836.tistory.com/165)

스프링 시큐리티는 스프링 기반의 어플리케이션의 보안(인증과 권한)을 담당하는 프레임워크이다.

만약 스프링 시큐리티를 사용하지 않았다면, 위의 전통적인 방법처럼 자체적으로 세션을 체크하고, redirect등을 해야한다.

spring security는 filter 기반으로 동작하기 때문에 spring MVC와 분리되어 관리 및 동작한다.

### 보안 관련 용어

- 접근 주체(Principal) : 보호된 대상에 접근하는 유저
- 인증 (Authenticate) : 현재 유저가 누구인지 확인 ex) 로그인
  - 애플리케이션의 작업을 수행할 수 있는 주체임을 증명한다.
- 인가 (Authorize) : 현재 유저가 어떤 서비스, 페이지에 접근할 수 있는 권한이 있는지 검사
- 권한 : 인증된 주체가 애플리케이션의 동작을 수행할 수 있도록 허락되어있는지를 결정
  - 권한 승인이 필요한 부분으로 접근하려면 인증 과정을 통해 주체가 증명 되어야만 한다.
  - 권한 부여에도 두가지 영역이 존재하는데 웹 요청 권한, 메소드 호출 및 도메인 인스턴스에 대한 접근 권한 부여

<br/>

### 타임리프에서 자바 시큐리티 사용하기

```java
// build.gradle
  implementation 'org.thymeleaf.extras:thymeleaf-extras-springsecurity5'
```

```html
<!-- html 파일 -->
<html xmlns:th="http://www.thymeleaf.org"
      xmlns:sec="http://www.thymeleaf.org/extras/spring-security">
```

기존의 태그에서 xmlns:sec 가 추가 된 형태이다.

아래와 같이 사용할 수 있다.
  
```html
  <!--ROLE_USER 권한을 갖는다면 이 글이 보임-->
  <h1 sec:authorize="hasRole('ADMIN')">Has admin Role</h1>

  <!--ROLE_ADMIN 권한을 갖는다면 이 글이 보임-->
  <h1 sec:authorize="hasRole('USER')">Has user Role</h1> 

  <!--어떤 권한이건 상관없이 인증이 되었다면 이 글이 보임-->
  <div sec:authorize="isAuthenticated()">
      Only Authenticated user can see this Text
  </div>
  
  <!--인증되지 않은 사용자의 경우 이 글이 보임-->
  <div sec:authorize="isAnonymous()">
      Only Authenticated user can see this Text
  </div>

  <!--인증시 사용된 객체에 대한 정보-->
  <b>Authenticated DTO:</b>
  <div sec:authentication="principal"></div>

  <!--인증시 사용된 객체의 Username (ID)-->
  <b>Authenticated username:</b>
  <div sec:authentication="name"></div>

  <!--객체의 권한-->
  <b>Authenticated user role:</b>
  <div sec:authentication="principal.authorities"></div>
```

<br/>

### 로그인 페이지 커스터마이징 하기

blog 패키지에 config 패키지를 하나 생성하고, SecurityConfig 클래스를 생성해준다.

```java
package com.cos.blog.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

// 빈 등록: 스프링 컨테이너에서 객체를 관리할 수 있게 하는 것
@Configuration // 빈 등록: IoC
@EnableWebSecurity // 필터 추가: 시큐리티 필터를 거는 것
@EnableGlobalAuthentication // 특정 주소로 접근을하면 권한 및 인증을 미리 체크하는 것
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
                .authorizeRequests()
                    .antMatchers("/auth/**")// /auth/ 이하의 모든 경로는
                    .permitAll() // 누구나 접근이 가능하다
                    .anyRequest() // 그게 아니고는
                    .authenticated() // 허락된 사람만 접근 가능하다.
                .and()
                    .formLogin()
                    .loginPage("/auth/loginForm");
    }
}

```

위와 같이 WebSecurityConfigurerAdapter를 상속하고, configure를 Override 하면, login 페이지 커스터마이징이 가능해진다.

<br/>

### 비밀번호 해시값으로 변경하기

감사하게도, 문자열을 넣으면 해시값으로 변경해주는 클래스를 Spring Security에서 지원해준다.

우리는 BCryptPasswordEncoder를 쓸 것이다.

```java
package com.cos.blog.config;

  @Bean // 스프링이 관리하는 IoC가 된다.
  BCryptPasswordEncoder encodePWD(){ return new BCryptPasswordEncoder(); }
```

위와같이 SecurityConfig 클래스에 해당 메소드를 추가해주면 된다. @Bean 어노테이션을 통해 스프링 IoC에서 관리하도록 한다.

BCryptPasswordEncoder의 encode( ) 메소드를 사용하면 String Wrapper Class 로 해시된 값을 반환해준다.

기존에 회원가입을 할 때 password 그대로 DB에 저장했지만, 이제는 해시로 변환된 값을 넣어준다.

바뀐 회원가입 Service 객체를 봐보쟈.

```java
package com.cos.blog.service;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository; // 의존성 주입

    private final BCryptPasswordEncoder encoder; // BCryptPasswordEncoder 클래스 의존성 주입

    @Transactional
    public void 회원가입(User user){
        String rawPassword = user.getPassword(); // 원래 password
        String encPassword = encoder.encode(rawPassword); // 해시
        user.setPassword(encPassword);
        user.setRole(RoleType.USER);
        userRepository.save(user);
    }

}
```

위 코드와 같이 원래 User 객체를 받아서 rawPassword를 해시값으로 변경해주고, 변경된 해시값을 User 객체의 password로 설정해준다.

그 뒤에 userRepository.save() 메소드를 통해 DB에 회원정보를 저장한다.

<br/>

### XSS와 CSRF 공격

<strong>XSS</strong>는 (Cross Site Scripting) 의 약자로 주로 다른 웹사이트와 정보를 교환하는 식으로 작동하므로 사이트 간 스트립팅이라고 한다.

크로스 사이트 스크립팅은 자바스크립트를 사용하여 공격하는 경우가 많다. 게시판 같은 공간에 <script> </script> 태그를 저장했을때, 이 스크립트가 서버에 저장돼 실행이 되어

서버의 민감한 정보를 빼올수가 있게된다.

<br/>

<strong>CSRF</strong>는 (Cross Site Request Fogery)의 약자로 사이트간 요청을 위조하는 공격이다.

선량한 사용자가 자신의 의지와는 무관하게 공격자가 의도한 행위를 웹사이트에 요청하게하는 공격을 말한다.

ex) 예를들어, 운영자가 http://www.example.com/point?100&username?ghdcksgml 해당 GET요청을 보내면

ghdcksgml라는 유저에게 100포인트가 지급된다고 해보자.

위는 운영자만이 권한이 있기때문에, 일반사용자가 해당 GET요청을 보내도 아무런 응답이 없고, 오직 운영자만이 해당 GET요청을 했을때 동작한다.

이 점을 이용해 아래와 같이 낚시를 진행한다.

<img width="590" alt="스크린샷 2022-02-08 오후 4 57 21" src="https://user-images.githubusercontent.com/79779676/152942625-e2317b81-0075-43b7-8998-b272199bbcec.png">

운영자가 낚여서 해당 링크를 클릭하게 된다면 ghdcksgml라는 유저에게 100포인트가 들어가게 되는 것이다.

이것이 바로 CSRF 공격이다.

<img width="814" alt="스크린샷 2022-02-08 오후 4 59 35" src="https://user-images.githubusercontent.com/79779676/152943017-edc70a0d-db25-4279-a513-802d72578edf.png">

출처: https://lucete1230-cyberpolice.tistory.com/23

<br/>

### 스프링 시큐리티 로그인

스프링 시큐리티가 로그인 요청을 가로채게 만든다.

```java
@Override
    protected void configure(HttpSecurity http) throws Exception{
        http
                .csrf().disable() // csrf 토큰 비활성화 (테스트시 걸어두는 게 좋음)
                .authorizeRequests()
                    .antMatchers("/","/auth/**","/js/**","/css/**","/image/**")// /auth/ 이하의 모든 경로는
                    .permitAll() // 누구나 접근이 가능하다
                .anyRequest() // 그게 아니고는
                    .authenticated() // 허락된 사람만 접근 가능하다.
                .and()
                    .formLogin()
                    .loginPage("/auth/loginForm")
                    .loginProcessingUrl("/auth/loginProc") // 스프링 시큐리티가 해당 주소로 요청오는 로그인을 가로채서 대신 로그인 해준다.
                    .defaultSuccessUrl("/");
    }
```

위 코드처럼 "/auth/loginFrom" 이라는 요청이 들어오면, 스프링 시큐리티가 해당 요청을 가로채 대신 로그인 해준다.

antMatchers에 설정된 페이지를 제외한 모든 페이지의 요청은 loginPage로 넘어온다. 

defaultSuccessUrl은 정상적으로 완료되면, 해당 URL로 이동한다.

<br/>

가로채서 로그인을 할 때 그때 내가 만들어야될 클래스가 하나 있다.

바로, UserDetails를 가지고 있는 User Object를 만들어야한다. (로그인 요청을 하고 세션에 등록을 해줘야하는데 그냥 User 오브젝트를 리턴하면 타입이 맞지 않기때문에)

```java
package com.cos.blog.config.auth;

import com.cos.blog.model.User;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

// 스프링 시큐리티가 로그인 요청을 가로채서 로그인을 진행하고 완료가 되면, UserDetails 타입의 오브젝트를
// 스프링 시큐리티의 고유한 세션저장소에 저장을 해준다.
@AllArgsConstructor
public class PrincipalDetail implements UserDetails {
    
    private User user; // composition

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    // 계정이 만료되지 않았는지 리턴한다. (true:만료안됨)
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // 계정이 잠겨있는지 않았는지 리턴한다. (true:잠기지 않음)
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // 비밀번호가 만료되지 않았는지 리턴한다. (true:만료안됨)
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // 계정이 활성화(사용가능)인지 리턴한다. (true:활성화)
    @Override
    public boolean isEnabled() {
        return true;
    }

    // 계정이 갖고있는 권한 목록을 리턴한다. (권한이 여러개 있을 수 있어서 루프를 돌아야 하는데 우리는 1개밖에 없음.)
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Collection<GrantedAuthority> collectors = new ArrayList<>();

        collectors.add(()->{ return "ROLE_"+user.getRole();}); // 앞에 ROLE_을 붙이는건 자바 시큐리티 규칙임.

        return collectors;
    }
}

```

```java
package com.cos.blog.config.auth;

import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service // Bean 등록
@RequiredArgsConstructor
public class PrincipalDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    // 스프링이 로그인 요청을 가로챌때, username,password 변수 2개를 가로채는데
    // password 부분 처리는 알아서 함.
    // username이 DB에 있는지만 확인해주면 됨.
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User principal = userRepository.findByUsername(username) // username이 일치하는 사용자 찾기
                .orElseThrow(()->{
                    return new UsernameNotFoundException("해당 사용자를 찾을 수 없습니다.");
                });
        return new PrincipalDetail(principal); // 시큐리티의 세션에 유저 정보가 저장이 됨.
    }
}

```

```java
package com.cos.blog.config;

import com.cos.blog.config.auth.PrincipalDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

// 빈 등록: 스프링 컨테이너에서 객체를 관리할 수 있게 하는 것
@Configuration // 빈 등록: IoC
@EnableWebSecurity // 필터 추가: 시큐리티 필터를 거는 것
@EnableGlobalAuthentication // 특정 주소로 접근을하면 권한 및 인증을 미리 체크하는 것
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final PrincipalDetailService principalDetailService; // DI

    @Bean // 스프링이 관리하는 IoC가 된다.
    public BCryptPasswordEncoder encodePWD(){
        return new BCryptPasswordEncoder();
    }

    // 시큐리티가 대신 로그인해주는데 password를 가로채기를 하는데
    // 해당 password가 뭘로 해쉬가 되어 회원가입이 되었는지 알아야
    // 같은 해쉬로 암호화해서 DB에 있는 해쉬랑 비교할 수 있음.
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(principalDetailService).passwordEncoder(encodePWD());
        // principalDetailService를 통해서 로그인을할 때 password를 encodePWD로 인코드해서 비교를 알아서 해준다.
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
                .csrf().disable() // csrf 토큰 비활성화 (테스트시 걸어두는 게 좋음)
                .authorizeRequests()
                    .antMatchers("/","/auth/**","/js/**","/css/**","/image/**")// /auth/ 이하의 모든 경로는
                    .permitAll() // 누구나 접근이 가능하다
                .anyRequest() // 그게 아니고는
                    .authenticated() // 허락된 사람만 접근 가능하다.
                .and()
                    .formLogin()
                    .loginPage("/auth/loginForm")
                    .loginProcessingUrl("/auth/loginProc") // 스프링 시큐리티가 해당 주소로 요청오는 로그인을 가로채서 대신 로그인 해준다.
                    .defaultSuccessUrl("/");
    }
}

```

해당 코드를 추가해준다.

로그인요청이 오는 순간, loginProcessingUrl이 가로챈다. => username과 password 정보를 PrincipalDetailService에 있는 loadUserByUsername으로 보낸다.

=> username을 비교해서 PrincipalDetail을 리턴해준다. => 리턴할때 비밀번호 체크를 한다. SecurityConfig의 configure을 통해서 principalDetailService가 로그인 요청을 하고

=> auth.userDetailsService(principalDetailService) 이 리턴이 되면, passwordEncoder를 통해 encodePWD로 다시 암호화를 하고, 데이터 베이스와 비교한다.

=> 비교가 끝나면 Spring Security 영역에 PrincipalDetail로 감싸져서 저장이 된다.

<img width="1326" alt="스크린샷 2022-02-08 오후 10 29 04" src="https://user-images.githubusercontent.com/79779676/152996644-77c6465f-061e-401e-b65b-7bd56e682128.png">
