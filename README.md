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
    open-in-view: true
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
