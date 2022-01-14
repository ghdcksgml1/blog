# 🧤 Blog

## 📁 application.yml

```yml
spring:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/blog?serverTimezone=Asia/Seoul
    username: root
    password: 
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
  
