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


