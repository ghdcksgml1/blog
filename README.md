# ๐งค Blog

## ๐ application.yml

```yml
server:
  port: 8000 # ์๋ฒ ํฌํธ์ค์ 
  servlet:
    context-path: /blog # ์ง์์  : localhost:8000/blog/

spring:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver # mysql ์ค์ 
    url: jdbc:mysql://localhost:3306/blog?serverTimezone=Asia/Seoul
    username: root
    password: 

  jpa: # jpa ์ค์ 
    open-in-view: true # lazy Loading
    hibernate:
      ddl-auto: update # create:์์ฑ๋ชจ๋, update:์๋ฐ์ดํธ๋ชจ๋, none: ์์ฑ,์๋ฐ์ดํธ (x)
      naming:
        physical-strategy: org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl # ํ์ด๋ธ์ ๋ง๋ค๋ ๋ณ์๋ช ๊ทธ๋๋ก ํ์ด๋ธ์ ๋ฃ์ด์ค.
      use-new-id-generator-mappings: false # false: jpa๊ฐ ์ฌ์ฉํ๋ ๊ธฐ๋ณธ ๋๋ฒ๋ง ์ ๋ต์ ๋ฐ๋ผ๊ฐ์ง ์๋๋ค.
    show-sql: true # ์ฟผ๋ฆฌ ๋ณด์ฌ์ฃผ๊ธฐ
    properties:
      hibernate.format_sql: true # ์ฟผ๋ฆฌ ์์๊ฒ ๋ณด์ฌ์ฃผ๊ธฐ

  jackson:
    serialization:
      fail-on-empty-beans: false

```
<br/>

## ๐ Http ํต์ 

- ํจํท ์ค์์นญ
  
  A๊ฐ B์๊ฒ '๊ฐ๋๋ค๋ผ'๋ผ๋ ๋ฐ์ดํฐ๋ฅผ ๋ณด๋ผ ๋ '๊ฐ','๋','๋ค','๋ผ ์ด๋ ๊ฒ ํจํท๋จ์๋ก ์ชผ๊ฐ์ ๋ณด๋ด์ ์ ์กํ๋ค.
  
  C๋ผ๋ ์ ๊ฐ B์๊ฒ ๋ฐ์ดํฐ๋ฅผ ์ ์กํ๊ณ  ์ถ์ ๊ฒฝ์ฐ์๋ A์ ์ ์ ๊ณต์ ํ๋ค.
  
  ๋จ์  : ๋์์ ๋ณด๋ด๊ฒ ๋  ๊ฒฝ์ฐ, ํ๋์ ์ ์ ์ฌ์ฉํ๋ฏ๋ก A์ C์ ๋ฐ์ดํฐ๊ฐ ์์ด๊ฒ ๋๋ค.
  
  ๊ทธ๋์ ํด๊ฒฐ์ฑ์ผ๋ก ํจํท์ ํค๋๋ฅผ ์ถ๊ฐ์์ผ ์ด๋ค ๊ณณ์์ ์จ ๋์์ธ์ง ๊ตฌ๋ถํ๋ค.

- ์ํท ์ค์์นญ

  A์ B๊ฐ ๋ฐ์ดํฐ๋ฅผ ๊ตํํ ๋ ๋ฐ์ดํฐ๋ฅผ ํ๋ฐฉ์ ์ค์ด์ ์ ์กํ๋ค.
  
  ๋จ์  : C๋ผ๋ ์ ๊ฐ B์๊ฒ ๋ฐ์ดํฐ๋ฅผ ์ ์กํ๊ณ  ์ถ์ผ๋ฉด ์ ์ ํ๋ ์ถ๊ฐํด์ผํ๋ค.
  
  ์๋๋ ๋น ๋ฅด์ง๋ง, ๋น์ฉ์ด ๋ง์ด ๋ฌ.
  
  <br/>
  
 ## ๐ MIME ํ์
 
 **MIME ํ์**์ด๋ ํด๋ผ์ด์ธํธ์๊ฒ ์ ์ก๋ ๋ฌธ์์ ๋ค์์ฑ์ ์๋ ค์ฃผ๊ธฐ ์ํ ๋ฉ์ปค๋์ฆ์๋๋ค.
 
 ### ๋ฌธ๋ฒ
  
 ```
 type/subtype
 ```
  
 '/'๋ก ๊ตฌ๋ถ๋ ๋๊ฐ์ ๋ฌธ์์ด์ธ ํ์๊ณผ ์๋ธํ์์ผ๋ก ๊ตฌ์ฑ๋๋ค. (์คํ์ด์ค๋ ํ์ฉ X)
  
 ### ๊ฐ๋ณํ์
  
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
  
**MIME ํ์ ์ ์ฒด๋ชฉ๋ก :** https://developer.mozilla.org/ko/docs/Web/HTTP/Basics_of_HTTP/MIME_types/Common_type
  
<br/>
  
## ๐ Http ์์ฒญ ์ค์ต
  
```java
@Controller // ์ฌ์ฉ์๊ฐ ์์ฒญํ๋ฉด HTMLํ์ผ์ ์๋ต
@RestController // ์ฌ์ฉ์๊ฐ ์์ฒญํ๋ฉด Data๋ฅผ ์๋ต
```
  
```java
  // ์ฌ์ฉ์๊ฐ ์์ฒญ -> ์๋ต(HTMLํ์ผ)
// @Controller

// ์ฌ์ฉ์๊ฐ ์์ฒญ -> ์๋ต(Data)
@RestController
public class HttpController {

    // ์ธํฐ๋ท ์์ฒญ์ ๋ฌด์กฐ๊ฑด get๋ง ๊ฐ๋ฅ
    // http://localhost:8080/http/get (select)
    @GetMapping("/http/get")
    public String getTest(){
        return "get ์์ฒญ";
    }

    // http://localhost:8080/http/post (insert)
    @PostMapping("/http/post")
    public String postTest(){
        return "post ์์ฒญ";
    }

    // http://localhost:8080/http/put (update)
    @PutMapping("/http/put")
    public String putTest(){
        return "put ์์ฒญ";
    }

    // http://localhost:8080/http/delete (delete)
    @DeleteMapping("/http/delete")
    public String deleteTest(){
        return "delete ์์ฒญ";
    }
}
```

### ์๋ฐ์์ ๋ณ์๋ ๋ค private์ผ๋ก ๋ง๋ ๋ค.

๊ทธ ์ด์ ๋ ๋ณ์์ ์ง์  ์ ๊ทผํด์ ๊ฐ์ ์์ ํ๋ ๊ฒ์ ๊ฐ์ฒด์งํฅ์ ๋ง์ง ์์ผ๋ฏ๋ก

๋ฉ์๋๋ฅผ public์ผ๋ก ๋ง๋ค์ด ๋ฉ์๋๋ก ๊ฐ์ ๋ณ๊ฒฝํ๋๋ก ํด์ค๋ค.

<br/>

### @GetMapping

@RequestParam("๋ณ์๋ช") : get์์ฒญ์ผ๋ก ๋ค์ด์จ ๋ณ์๋ฅผ ์ฝ์ด๋ค์ผ ์ ์๋ค.

๋งค๊ฐ๋ณ์๋ฅผ ์๋์ ๊ฐ์ด ๊ฐ์ฒด๋ก ๋ฐ์ผ๋ฉด ํ๊บผ๋ฒ์ ๋ฐ์ ์ ์๋ค. (setter ํ์ํจ)

```java
...
  @GetMapping("/http/get")
  public String get(Member m){
    return m.getId() + m.getUsername();
  }
...
```

### @PostMapping

PostMapping ๋ํ ๊ฐ์ฒด๋ก ๋งค๊ฐ๋ณ์๋ฅผ ๋ฐ์ ์ ์๋ค. (html์์ formํ๊ทธ๋ก ๋ณด๋์๋)

JSON ๋ฐ์ดํฐ๋ฅผ ๋ฐ๊ธฐ ์ํด์๋ @ResponseBody ํ๊ทธ๋ฅผ ์จ์ค์ผํ๋ค.

```java
// http://localhost:8080/http/post (insert)
  @PostMapping("/http/post")
  public String postTest(@RequestBody Member m){
      return "post ์์ฒญ" + m.getId();
  }
  ...
```

์ ์ฝ๋๋ฅผ ์คํํด์ JSON ํ์์ผ๋ก POST์์ฒญ์ ๋ณด๋ด๋ฉด JSON์ด ์๋ฐ์ Member ๊ฐ์ฒด๋ก ์ ๋ค์ด๊ฐ๋ค.

์ด ๊ณผ์  ์ฆ, JSON => ์๋ฐ ๊ฐ์ฒด ๋ก ๋ฐ๊พธ์ด์ฃผ๋ ๊ณผ์ ์ ์คํ๋ง ๋ถํธ์ MessageConverter๊ฐ ์ํํ๋ค.

๋๋จธ์ง @PutMapping๊ณผ @DeleteMapping๋ ๋ง์ฐฌ๊ฐ์ง๋ก @PostMapping๊ณผ ๋น์ทํ ์ผ์ ์ํํ๋ค.
  
<br/>
  
## ๐ AJAX ์์ฒญ

์ผ๋จ, ajax ์์ฒญ์ ํ๊ธฐ ์ํด์๋ ์๋ฐ์คํฌ๋ฆฝํธ์ ๋์์ด ํ์ํ๋ค.

staticํด๋์ js ํด๋๋ฅผ ํ๋ ๋ง๋ค์ด user.js๋ฅผ ๋ง๋ค์.

```javascript
// ํด๋ ๊ฒฝ๋ก : /resources/static/js/user.js
let index = {
    init:function(){
        // btn-save ๋ฒํผ์ด ํด๋ฆญ๋๋ฉด, saveํจ์๋ฅผ ํธ์ถ
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

        // ajax ์์ฒญ
        fetch('/blog/api/user',{
            method:'POST',headers:{'content-type':'application/json'},body:JSON.stringify(data)
        })
            .then(response => response.json())
            .then(data=>{
                alert("ํ์๊ฐ์ ์๋ฃ");
                console.log(data);
                // location.href="/blog";
            })
            .catch(error=>{alert(error.message)});
    }
};

index.init();
```

์์ ๊ฐ์ด jQuery ๋์ ์ Javascript๋ก ajax ์์ฒญ์ ํ๊ธฐ์ํด์๋ fetch ํจ์๊ฐ ํ์ํ๋ค.

fetchํจ์๋ Promise๊ฐ์ฒด๋ก ๋ฆฌํด์ด ๋๊ธฐ ๋๋ฌธ์, ๋ณ๋์ response.json()๊ณผ ๊ฐ์ด Promise๊ฐ์ฒด => json ์ผ๋ก์ ๋ณํ์ด ํ์ํ๋ค.

### ajax ์ฌ์ฉ ์ ์ฅ์ 

- ์นํ์ด์ง์ ์๋๊ฐ ํฅ์๋๋ค.
- ์๋ฒ์ ์ฒ๋ฆฌ๊ฐ ์๋ฃ๋  ๋๊น์ง ๊ธฐ๋ค๋ฆฌ์ง ์๊ณ  ์ฒ๋ฆฌ๊ฐ ๊ฐ๋ฅํ๋ค.
- ์๋ฒ์์ Data๋ง ์ ์กํ๋ฉด ๋๋ฏ๋ก ์ ์ฒด์ ์ธ ์ฝ๋ฉ์ ์์ด ์ค์ด๋ ๋ค.
- ๊ธฐ์กด ์น์์๋ ๋ถ๊ฐ๋ฅํ๋ ๋ค์ํ UI๋ฅผ ๊ฐ๋ฅํ๊ฒ ํด์ค๋ค.

<br/>

### ajax ์ฌ์ฉ ์ ๋จ์ 

- ํ์คํ ๋ฆฌ ๊ด๋ฆฌ๊ฐ ๋์ง ์๋๋ค.
- ํ์ด์ง ์ด๋ ์๋ ํต์ ์ผ๋ก ์ธํ ๋ณด์์์ ๋ฌธ์ ๊ฐ ์๋ค.
- ์ฐ์์ผ๋ก ๋ฐ์ดํฐ๋ฅผ ์์ฒญํ๋ฉด ์๋ฒ ๋ถํ๊ฐ ์ฆ๊ฐํ  ์ ์๋ค.
- ajax๋ฅผ ์ธ ์ ์๋ ๋ธ๋ผ์ฐ์ ์์ ๋ฌธ์ ๊ฐ ๋ฐ์ํ๋ค.
- HTTP ํด๋ผ์ด์ธํธ์ ๊ธฐ๋ฅ์ด ํ์ ๋์ด ์๋ค.
- ์ง์ํ๋ Charset์ด ํ์ ๋์ด ์๋ค.
- script๋ก ์์ฑ๋๋ฏ๋ก ๋๋ฒ๊น์ด ์ฉ์ดํ์ง ์๋ค.
- ๋์ผ-์ถ์ฒ ์ ์ฑ์ผ๋ก ๋ค๋ฅธ ๋๋ฉ์ธ๊ณผ๋ ํต์ ์ด ๋ถ๊ฐ๋ฅํ๋ค.

<br/>

## ๐ ์คํธ๋ง ๋ถํธ์ ํธ๋์ญ์

์ ํต์ ์ธ ๋ฐฉ์์ ์์์ฑ ์ปจํ์คํธ, JDBC, ํธ๋์ญ์์ ๋ชจ๋ ๊ฐ์ ๊ตฌ๊ฐ์์ ์ฐ๊ฒฐํ์ง๋ง, 

- lazy Loading์ ์ํด, ์ธ์์ ์์์ ์๋ธ๋ฆฟ์ด ์์๋๋ ์์ ๋ถํฐ (์ธ์์ ์์์ฑ ์ปจํ์คํธ๋ฅผ ํฌํจ)

- ํธ๋์ญ์๊ณผ JDBC์ ์์์ Service ๋ ์ด์ด๋ถํฐ

- ํธ๋์ญ์๊ณผ JDBC์ ์ข๋ฃ๋ Service ๋ ์ด์ด์์ ์ข๋ฃ

- ์ธ์(์์์ฑ ์ปจํ์คํธ)์ ์ปจํธ๋กค๋ฌ ์์ญ๊น์ง ๋๊ณ ๊ฐ๊ธฐ ๋๋ฌธ์ ์์์ฑ์ด ๋ณด์ฅ๋์ด select๊ฐ ๊ฐ๋ฅํด์ง๊ณ , lazy Loading์ด ๊ฐ๋ฅํด์ง๋ค.

<img width="782" alt="แแณแแณแแตแซแแฃแบ 2022-02-03 แแฉแแฎ 6 06 27" src="https://user-images.githubusercontent.com/79779676/152312415-d7b9bb42-d790-4007-b240-23fe5de43dff.png">

<br/>

### - ์ ํต์ ์ธ ๋ฐฉ์

์ ํต์ ์ธ ๋ฐฉ์์ ์๋์๊ฐ์ด ์ธ์ ์์์์ ์ ์์์ฑ ์ปจํ์คํธ, JDBC, Transaction ์ฐ๊ฒฐ์ ํ๋ฒ์ ์คํํ๊ณ  ํ๋ฒ์ ์ข๋ฃํ๋ค.

<img width="896" alt="แแณแแณแแตแซแแฃแบ 2022-02-03 แแฉแแฎ 6 30 45" src="https://user-images.githubusercontent.com/79779676/152316840-86c0a596-10ab-4101-9aca-e986cb885c31.png">

<br/>

### - lazy Loading ๋ฐฉ์

lazy Loading ๋ฐฉ์์ ์์์ ์ค๋ชํ๊ฒ์ฒ๋ผ ์ธ์์ด ์์๋ ๋ ์์์ฑ ์ปจํ์คํธ๊ฐ ์คํ๋๊ณ , Service ์์ ๋ถํฐ JDBC์ Transaction์ ์ฐ๊ฒฐ์ด ์ฐ๊ฒฐ๋๊ณ , ๋๊ธด๋ค.

์๋์ ๊ทธ๋ฆผ์ ์ต์ด ์ ์ ์ ๋ณด๋ฅผ ๊ฐ์ ธ์์๋์ธ๋ฐ, ์ ์ ์ ๋ณด๋ ๊ฐ์ฒด๋ฅผ ๊ฐ์ ธ์์ง๋ง, ์ ์ ์ ๋ณด์ ์ธ๋ํค์ธ ํ ์ ๋ณด๋ ํ๋ก์ ๊ฐ์ฒด๋ก ๊ฐ์ ธ์จ๋ค.

<img width="912" alt="แแณแแณแแตแซแแฃแบ 2022-02-03 แแฉแแฎ 6 35 04" src="https://user-images.githubusercontent.com/79779676/152317121-cb019e98-f9e8-4e62-bb17-7125e46209fe.png">

<br/>

์ด๋ ๊ฒ ์ ์ ์ ๋ณด๋ง ํ์ํ ๊ฒฝ์ฐ์๋ Controller์์ ๊ทธ๋ฅ ์ฐ๋ฉด ๋๋ค.

๋ง์ฝ, ํ ์ ๋ณด๋ฅผ ์ธ์ผ์ด ์์ด ํ ์ ๋ณด์ ํ๋ก์ ๊ฐ์ฒด๋ฅผ ํธ์ถํ๊ฒ ๋๋ฉด, ๋ค์ JDBC ์ฐ๊ฒฐ์ ํตํด select๋ฌธ์ ์ค์ํด ํ๋ก์ ๊ฐ์ฒด๊ฐ ์๋ ์ง์ง ํ ์ ๋ณด ๊ฐ์ฒด๋ฅผ ๊ฐ์ ธ์์ค๋ค.

<img width="919" alt="แแณแแณแแตแซแแฃแบ 2022-02-03 แแฉแแฎ 6 35 52" src="https://user-images.githubusercontent.com/79779676/152317768-2cf9bb01-410f-494e-bb44-0576599ba57b.png">

### ์ฌ์ฉ๋ฒ

Lazy Loading ๋ฐฉ์์ ์ฐ๊ธฐ ์ํด์๋ application.yml์ jpa ์ค์ ์์ open-in-view: true ์ค์ ์ ํด์ฃผ๋ฉด ๋๋ค. ( ๋งจ์์ ๋์์์. ๊ทธ๋ฆฌ๊ณ  Default๋ true์ )

๋ง์ฝ, open-in-view: false๋ฅผ ์ฐ๊ฒ๋๋ค๋ฉด, Service ๊ณ์ธต์์ ์์์ฑ ์ปจํ์คํธ์ JDBC, Transaction ์ฐ๊ฒฐ์ด ์ฐ๊ฒฐ๋๊ณ  ๋๊ธฐ๊ฒ ๋๋ค.

์ฆ, Controller์์ ์์์ฑ ์ปจํ์คํธ๋ก ์ ๊ทผ์ด ๋ถ๊ฐํ๋ค.

## ๐ ์ ํต์ ์ธ ๋ก๊ทธ์ธ ๋ฐฉ์

### ์ ํต์ ์ธ ๋ก๊ทธ์ธ ๊ตฌํ

userService์์ ๋ก๊ทธ์ธ ํจ์๋ฅผ ์คํ์ํค๊ณ , ( ๋ก๊ทธ์ธ ํจ์๋ @RequestBody๋ก ๋ฐ์ User์ ์์ด๋์ ํจ์ค์๋๊ฐ ์ผ์นํ๋์ง ํ์ธ )

์๋์ ์ฝ๋์ฒ๋ผ HttpSession ์ ํตํด ์ธ์์ ์์ฑํ๋ค.

```java
    @PostMapping("/api/user/login")
    public ResponseDto<Integer> login(@RequestBody User user, HttpSession session){
        User principal = userService.๋ก๊ทธ์ธ(user);
        if(principal != null){
            session.setAttribute("principle",principal);
        }
        return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
    }
```

thymeleaf์์ ์ธ์๊ฐ์ ํตํด ์ธ์๋ณ ํ์ํ  ์ ๋ณด๋ฅผ ๊ตฌ๋ถํด์ค๋ค.

```html
<!-- fragment bodyHeader ํ์ผ -->
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<div th:fragment="bodyHeader">
    <nav class="navbar navbar-expand-md bg-dark navbar-dark">
        <a class="navbar-brand" href="/blog">ํ</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="collapsibleNavbar">
            <ul th:if="${session.principle == null}" class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link" href="/blog/user/loginForm">๋ก๊ทธ์ธ</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/blog/user/joinForm">ํ์๊ฐ์</a>
                </li>
            </ul>
            <ul th:if="${session.principle != null}" class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link" href="/blog/board/writeForm">๊ธ์ฐ๊ธฐ</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/blog/user/userForm">๋ด ์ ๋ณด</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/blog/user/logout">๋ก๊ทธ์์</a>
                </li>
            </ul>
        </div>
    </nav>
</div>
```

<br/>

## ๐ ์คํ๋ง ์ํ๋ฆฌํฐ

[์ฐธ๊ณ ์๋ฃ](https://sjh836.tistory.com/165)

์คํ๋ง ์ํ๋ฆฌํฐ๋ ์คํ๋ง ๊ธฐ๋ฐ์ ์ดํ๋ฆฌ์ผ์ด์์ ๋ณด์(์ธ์ฆ๊ณผ ๊ถํ)์ ๋ด๋นํ๋ ํ๋ ์์ํฌ์ด๋ค.

๋ง์ฝ ์คํ๋ง ์ํ๋ฆฌํฐ๋ฅผ ์ฌ์ฉํ์ง ์์๋ค๋ฉด, ์์ ์ ํต์ ์ธ ๋ฐฉ๋ฒ์ฒ๋ผ ์์ฒด์ ์ผ๋ก ์ธ์์ ์ฒดํฌํ๊ณ , redirect๋ฑ์ ํด์ผํ๋ค.

spring security๋ filter ๊ธฐ๋ฐ์ผ๋ก ๋์ํ๊ธฐ ๋๋ฌธ์ spring MVC์ ๋ถ๋ฆฌ๋์ด ๊ด๋ฆฌ ๋ฐ ๋์ํ๋ค.

### ๋ณด์ ๊ด๋ จ ์ฉ์ด

- ์ ๊ทผ ์ฃผ์ฒด(Principal) : ๋ณดํธ๋ ๋์์ ์ ๊ทผํ๋ ์ ์ 
- ์ธ์ฆ (Authenticate) : ํ์ฌ ์ ์ ๊ฐ ๋๊ตฌ์ธ์ง ํ์ธ ex) ๋ก๊ทธ์ธ
  - ์ ํ๋ฆฌ์ผ์ด์์ ์์์ ์ํํ  ์ ์๋ ์ฃผ์ฒด์์ ์ฆ๋ชํ๋ค.
- ์ธ๊ฐ (Authorize) : ํ์ฌ ์ ์ ๊ฐ ์ด๋ค ์๋น์ค, ํ์ด์ง์ ์ ๊ทผํ  ์ ์๋ ๊ถํ์ด ์๋์ง ๊ฒ์ฌ
- ๊ถํ : ์ธ์ฆ๋ ์ฃผ์ฒด๊ฐ ์ ํ๋ฆฌ์ผ์ด์์ ๋์์ ์ํํ  ์ ์๋๋ก ํ๋ฝ๋์ด์๋์ง๋ฅผ ๊ฒฐ์ 
  - ๊ถํ ์น์ธ์ด ํ์ํ ๋ถ๋ถ์ผ๋ก ์ ๊ทผํ๋ ค๋ฉด ์ธ์ฆ ๊ณผ์ ์ ํตํด ์ฃผ์ฒด๊ฐ ์ฆ๋ช ๋์ด์ผ๋ง ํ๋ค.
  - ๊ถํ ๋ถ์ฌ์๋ ๋๊ฐ์ง ์์ญ์ด ์กด์ฌํ๋๋ฐ ์น ์์ฒญ ๊ถํ, ๋ฉ์๋ ํธ์ถ ๋ฐ ๋๋ฉ์ธ ์ธ์คํด์ค์ ๋ํ ์ ๊ทผ ๊ถํ ๋ถ์ฌ

<br/>

### ํ์๋ฆฌํ์์ ์๋ฐ ์ํ๋ฆฌํฐ ์ฌ์ฉํ๊ธฐ

```java
// build.gradle
  implementation 'org.thymeleaf.extras:thymeleaf-extras-springsecurity5'
```

```html
<!-- html ํ์ผ -->
<html xmlns:th="http://www.thymeleaf.org"
      xmlns:sec="http://www.thymeleaf.org/extras/spring-security">
```

๊ธฐ์กด์ ํ๊ทธ์์ xmlns:sec ๊ฐ ์ถ๊ฐ ๋ ํํ์ด๋ค.

์๋์ ๊ฐ์ด ์ฌ์ฉํ  ์ ์๋ค.
  
```html
  <!--ROLE_USER ๊ถํ์ ๊ฐ๋๋ค๋ฉด ์ด ๊ธ์ด ๋ณด์-->
  <h1 sec:authorize="hasRole('ADMIN')">Has admin Role</h1>

  <!--ROLE_ADMIN ๊ถํ์ ๊ฐ๋๋ค๋ฉด ์ด ๊ธ์ด ๋ณด์-->
  <h1 sec:authorize="hasRole('USER')">Has user Role</h1> 

  <!--์ด๋ค ๊ถํ์ด๊ฑด ์๊ด์์ด ์ธ์ฆ์ด ๋์๋ค๋ฉด ์ด ๊ธ์ด ๋ณด์-->
  <div sec:authorize="isAuthenticated()">
      Only Authenticated user can see this Text
  </div>
  
  <!--์ธ์ฆ๋์ง ์์ ์ฌ์ฉ์์ ๊ฒฝ์ฐ ์ด ๊ธ์ด ๋ณด์-->
  <div sec:authorize="isAnonymous()">
      Only Authenticated user can see this Text
  </div>

  <!--์ธ์ฆ์ ์ฌ์ฉ๋ ๊ฐ์ฒด์ ๋ํ ์ ๋ณด-->
  <b>Authenticated DTO:</b>
  <div sec:authentication="principal"></div>

  <!--์ธ์ฆ์ ์ฌ์ฉ๋ ๊ฐ์ฒด์ Username (ID)-->
  <b>Authenticated username:</b>
  <div sec:authentication="name"></div>

  <!--๊ฐ์ฒด์ ๊ถํ-->
  <b>Authenticated user role:</b>
  <div sec:authentication="principal.authorities"></div>
```

<br/>

### ๋ก๊ทธ์ธ ํ์ด์ง ์ปค์คํฐ๋ง์ด์ง ํ๊ธฐ

blog ํจํค์ง์ config ํจํค์ง๋ฅผ ํ๋ ์์ฑํ๊ณ , SecurityConfig ํด๋์ค๋ฅผ ์์ฑํด์ค๋ค.

```java
package com.cos.blog.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

// ๋น ๋ฑ๋ก: ์คํ๋ง ์ปจํ์ด๋์์ ๊ฐ์ฒด๋ฅผ ๊ด๋ฆฌํ  ์ ์๊ฒ ํ๋ ๊ฒ
@Configuration // ๋น ๋ฑ๋ก: IoC
@EnableWebSecurity // ํํฐ ์ถ๊ฐ: ์ํ๋ฆฌํฐ ํํฐ๋ฅผ ๊ฑฐ๋ ๊ฒ
@EnableGlobalAuthentication // ํน์  ์ฃผ์๋ก ์ ๊ทผ์ํ๋ฉด ๊ถํ ๋ฐ ์ธ์ฆ์ ๋ฏธ๋ฆฌ ์ฒดํฌํ๋ ๊ฒ
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
                .authorizeRequests()
                    .antMatchers("/auth/**")// /auth/ ์ดํ์ ๋ชจ๋  ๊ฒฝ๋ก๋
                    .permitAll() // ๋๊ตฌ๋ ์ ๊ทผ์ด ๊ฐ๋ฅํ๋ค
                    .anyRequest() // ๊ทธ๊ฒ ์๋๊ณ ๋
                    .authenticated() // ํ๋ฝ๋ ์ฌ๋๋ง ์ ๊ทผ ๊ฐ๋ฅํ๋ค.
                .and()
                    .formLogin()
                    .loginPage("/auth/loginForm");
    }
}

```

์์ ๊ฐ์ด WebSecurityConfigurerAdapter๋ฅผ ์์ํ๊ณ , configure๋ฅผ Override ํ๋ฉด, login ํ์ด์ง ์ปค์คํฐ๋ง์ด์ง์ด ๊ฐ๋ฅํด์ง๋ค.

<br/>

### ๋น๋ฐ๋ฒํธ ํด์๊ฐ์ผ๋ก ๋ณ๊ฒฝํ๊ธฐ

๊ฐ์ฌํ๊ฒ๋, ๋ฌธ์์ด์ ๋ฃ์ผ๋ฉด ํด์๊ฐ์ผ๋ก ๋ณ๊ฒฝํด์ฃผ๋ ํด๋์ค๋ฅผ Spring Security์์ ์ง์ํด์ค๋ค.

์ฐ๋ฆฌ๋ BCryptPasswordEncoder๋ฅผ ์ธ ๊ฒ์ด๋ค.

```java
package com.cos.blog.config;

  @Bean // ์คํ๋ง์ด ๊ด๋ฆฌํ๋ IoC๊ฐ ๋๋ค.
  BCryptPasswordEncoder encodePWD(){ return new BCryptPasswordEncoder(); }
```

์์๊ฐ์ด SecurityConfig ํด๋์ค์ ํด๋น ๋ฉ์๋๋ฅผ ์ถ๊ฐํด์ฃผ๋ฉด ๋๋ค. @Bean ์ด๋ธํ์ด์์ ํตํด ์คํ๋ง IoC์์ ๊ด๋ฆฌํ๋๋ก ํ๋ค.

BCryptPasswordEncoder์ encode( ) ๋ฉ์๋๋ฅผ ์ฌ์ฉํ๋ฉด String Wrapper Class ๋ก ํด์๋ ๊ฐ์ ๋ฐํํด์ค๋ค.

๊ธฐ์กด์ ํ์๊ฐ์์ ํ  ๋ password ๊ทธ๋๋ก DB์ ์ ์ฅํ์ง๋ง, ์ด์ ๋ ํด์๋ก ๋ณํ๋ ๊ฐ์ ๋ฃ์ด์ค๋ค.

๋ฐ๋ ํ์๊ฐ์ Service ๊ฐ์ฒด๋ฅผ ๋ด๋ณด์.

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

    private final UserRepository userRepository; // ์์กด์ฑ ์ฃผ์

    private final BCryptPasswordEncoder encoder; // BCryptPasswordEncoder ํด๋์ค ์์กด์ฑ ์ฃผ์

    @Transactional
    public void ํ์๊ฐ์(User user){
        String rawPassword = user.getPassword(); // ์๋ password
        String encPassword = encoder.encode(rawPassword); // ํด์
        user.setPassword(encPassword);
        user.setRole(RoleType.USER);
        userRepository.save(user);
    }

}
```

์ ์ฝ๋์ ๊ฐ์ด ์๋ User ๊ฐ์ฒด๋ฅผ ๋ฐ์์ rawPassword๋ฅผ ํด์๊ฐ์ผ๋ก ๋ณ๊ฒฝํด์ฃผ๊ณ , ๋ณ๊ฒฝ๋ ํด์๊ฐ์ User ๊ฐ์ฒด์ password๋ก ์ค์ ํด์ค๋ค.

๊ทธ ๋ค์ userRepository.save() ๋ฉ์๋๋ฅผ ํตํด DB์ ํ์์ ๋ณด๋ฅผ ์ ์ฅํ๋ค.

<br/>

### XSS์ CSRF ๊ณต๊ฒฉ

<strong>XSS</strong>๋ (Cross Site Scripting) ์ ์ฝ์๋ก ์ฃผ๋ก ๋ค๋ฅธ ์น์ฌ์ดํธ์ ์ ๋ณด๋ฅผ ๊ตํํ๋ ์์ผ๋ก ์๋ํ๋ฏ๋ก ์ฌ์ดํธ ๊ฐ ์คํธ๋ฆฝํ์ด๋ผ๊ณ  ํ๋ค.

ํฌ๋ก์ค ์ฌ์ดํธ ์คํฌ๋ฆฝํ์ ์๋ฐ์คํฌ๋ฆฝํธ๋ฅผ ์ฌ์ฉํ์ฌ ๊ณต๊ฒฉํ๋ ๊ฒฝ์ฐ๊ฐ ๋ง๋ค. ๊ฒ์ํ ๊ฐ์ ๊ณต๊ฐ์ <script> </script> ํ๊ทธ๋ฅผ ์ ์ฅํ์๋, ์ด ์คํฌ๋ฆฝํธ๊ฐ ์๋ฒ์ ์ ์ฅ๋ผ ์คํ์ด ๋์ด

์๋ฒ์ ๋ฏผ๊ฐํ ์ ๋ณด๋ฅผ ๋นผ์ฌ์๊ฐ ์๊ฒ๋๋ค.

<br/>

<strong>CSRF</strong>๋ (Cross Site Request Fogery)์ ์ฝ์๋ก ์ฌ์ดํธ๊ฐ ์์ฒญ์ ์์กฐํ๋ ๊ณต๊ฒฉ์ด๋ค.

์ ๋ํ ์ฌ์ฉ์๊ฐ ์์ ์ ์์ง์๋ ๋ฌด๊ดํ๊ฒ ๊ณต๊ฒฉ์๊ฐ ์๋ํ ํ์๋ฅผ ์น์ฌ์ดํธ์ ์์ฒญํ๊ฒํ๋ ๊ณต๊ฒฉ์ ๋งํ๋ค.

ex) ์๋ฅผ๋ค์ด, ์ด์์๊ฐ http://www.example.com/point?100&username?ghdcksgml ํด๋น GET์์ฒญ์ ๋ณด๋ด๋ฉด

ghdcksgml๋ผ๋ ์ ์ ์๊ฒ 100ํฌ์ธํธ๊ฐ ์ง๊ธ๋๋ค๊ณ  ํด๋ณด์.

์๋ ์ด์์๋ง์ด ๊ถํ์ด ์๊ธฐ๋๋ฌธ์, ์ผ๋ฐ์ฌ์ฉ์๊ฐ ํด๋น GET์์ฒญ์ ๋ณด๋ด๋ ์๋ฌด๋ฐ ์๋ต์ด ์๊ณ , ์ค์ง ์ด์์๋ง์ด ํด๋น GET์์ฒญ์ ํ์๋ ๋์ํ๋ค.

์ด ์ ์ ์ด์ฉํด ์๋์ ๊ฐ์ด ๋์๋ฅผ ์งํํ๋ค.

<img width="590" alt="แแณแแณแแตแซแแฃแบ 2022-02-08 แแฉแแฎ 4 57 21" src="https://user-images.githubusercontent.com/79779676/152942625-e2317b81-0075-43b7-8998-b272199bbcec.png">

์ด์์๊ฐ ๋์ฌ์ ํด๋น ๋งํฌ๋ฅผ ํด๋ฆญํ๊ฒ ๋๋ค๋ฉด ghdcksgml๋ผ๋ ์ ์ ์๊ฒ 100ํฌ์ธํธ๊ฐ ๋ค์ด๊ฐ๊ฒ ๋๋ ๊ฒ์ด๋ค.

์ด๊ฒ์ด ๋ฐ๋ก CSRF ๊ณต๊ฒฉ์ด๋ค.

<img width="814" alt="แแณแแณแแตแซแแฃแบ 2022-02-08 แแฉแแฎ 4 59 35" src="https://user-images.githubusercontent.com/79779676/152943017-edc70a0d-db25-4279-a513-802d72578edf.png">

์ถ์ฒ: https://lucete1230-cyberpolice.tistory.com/23

<br/>

### ์คํ๋ง ์ํ๋ฆฌํฐ ๋ก๊ทธ์ธ

์คํ๋ง ์ํ๋ฆฌํฐ๊ฐ ๋ก๊ทธ์ธ ์์ฒญ์ ๊ฐ๋ก์ฑ๊ฒ ๋ง๋ ๋ค.

```java
@Override
    protected void configure(HttpSecurity http) throws Exception{
        http
                .csrf().disable() // csrf ํ ํฐ ๋นํ์ฑํ (ํ์คํธ์ ๊ฑธ์ด๋๋ ๊ฒ ์ข์)
                .authorizeRequests()
                    .antMatchers("/","/auth/**","/js/**","/css/**","/image/**")// /auth/ ์ดํ์ ๋ชจ๋  ๊ฒฝ๋ก๋
                    .permitAll() // ๋๊ตฌ๋ ์ ๊ทผ์ด ๊ฐ๋ฅํ๋ค
                .anyRequest() // ๊ทธ๊ฒ ์๋๊ณ ๋
                    .authenticated() // ํ๋ฝ๋ ์ฌ๋๋ง ์ ๊ทผ ๊ฐ๋ฅํ๋ค.
                .and()
                    .formLogin()
                    .loginPage("/auth/loginForm")
                    .loginProcessingUrl("/auth/loginProc") // ์คํ๋ง ์ํ๋ฆฌํฐ๊ฐ ํด๋น ์ฃผ์๋ก ์์ฒญ์ค๋ ๋ก๊ทธ์ธ์ ๊ฐ๋ก์ฑ์ ๋์  ๋ก๊ทธ์ธ ํด์ค๋ค.
                    .defaultSuccessUrl("/");
    }
```

์ ์ฝ๋์ฒ๋ผ "/auth/loginFrom" ์ด๋ผ๋ ์์ฒญ์ด ๋ค์ด์ค๋ฉด, ์คํ๋ง ์ํ๋ฆฌํฐ๊ฐ ํด๋น ์์ฒญ์ ๊ฐ๋ก์ฑ ๋์  ๋ก๊ทธ์ธ ํด์ค๋ค.

antMatchers์ ์ค์ ๋ ํ์ด์ง๋ฅผ ์ ์ธํ ๋ชจ๋  ํ์ด์ง์ ์์ฒญ์ loginPage๋ก ๋์ด์จ๋ค. 

defaultSuccessUrl์ ์ ์์ ์ผ๋ก ์๋ฃ๋๋ฉด, ํด๋น URL๋ก ์ด๋ํ๋ค.

<br/>

๊ฐ๋ก์ฑ์ ๋ก๊ทธ์ธ์ ํ  ๋ ๊ทธ๋ ๋ด๊ฐ ๋ง๋ค์ด์ผ๋  ํด๋์ค๊ฐ ํ๋ ์๋ค.

๋ฐ๋ก, UserDetails๋ฅผ ๊ฐ์ง๊ณ  ์๋ User Object๋ฅผ ๋ง๋ค์ด์ผํ๋ค. (๋ก๊ทธ์ธ ์์ฒญ์ ํ๊ณ  ์ธ์์ ๋ฑ๋ก์ ํด์ค์ผํ๋๋ฐ ๊ทธ๋ฅ User ์ค๋ธ์ ํธ๋ฅผ ๋ฆฌํดํ๋ฉด ํ์์ด ๋ง์ง ์๊ธฐ๋๋ฌธ์)

```java
package com.cos.blog.config.auth;

import com.cos.blog.model.User;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

// ์คํ๋ง ์ํ๋ฆฌํฐ๊ฐ ๋ก๊ทธ์ธ ์์ฒญ์ ๊ฐ๋ก์ฑ์ ๋ก๊ทธ์ธ์ ์งํํ๊ณ  ์๋ฃ๊ฐ ๋๋ฉด, UserDetails ํ์์ ์ค๋ธ์ ํธ๋ฅผ
// ์คํ๋ง ์ํ๋ฆฌํฐ์ ๊ณ ์ ํ ์ธ์์ ์ฅ์์ ์ ์ฅ์ ํด์ค๋ค.
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

    // ๊ณ์ ์ด ๋ง๋ฃ๋์ง ์์๋์ง ๋ฆฌํดํ๋ค. (true:๋ง๋ฃ์๋จ)
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // ๊ณ์ ์ด ์ ๊ฒจ์๋์ง ์์๋์ง ๋ฆฌํดํ๋ค. (true:์ ๊ธฐ์ง ์์)
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // ๋น๋ฐ๋ฒํธ๊ฐ ๋ง๋ฃ๋์ง ์์๋์ง ๋ฆฌํดํ๋ค. (true:๋ง๋ฃ์๋จ)
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // ๊ณ์ ์ด ํ์ฑํ(์ฌ์ฉ๊ฐ๋ฅ)์ธ์ง ๋ฆฌํดํ๋ค. (true:ํ์ฑํ)
    @Override
    public boolean isEnabled() {
        return true;
    }

    // ๊ณ์ ์ด ๊ฐ๊ณ ์๋ ๊ถํ ๋ชฉ๋ก์ ๋ฆฌํดํ๋ค. (๊ถํ์ด ์ฌ๋ฌ๊ฐ ์์ ์ ์์ด์ ๋ฃจํ๋ฅผ ๋์์ผ ํ๋๋ฐ ์ฐ๋ฆฌ๋ 1๊ฐ๋ฐ์ ์์.)
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Collection<GrantedAuthority> collectors = new ArrayList<>();

        collectors.add(()->{ return "ROLE_"+user.getRole();}); // ์์ ROLE_์ ๋ถ์ด๋๊ฑด ์๋ฐ ์ํ๋ฆฌํฐ ๊ท์น์.

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

@Service // Bean ๋ฑ๋ก
@RequiredArgsConstructor
public class PrincipalDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    // ์คํ๋ง์ด ๋ก๊ทธ์ธ ์์ฒญ์ ๊ฐ๋ก์ฑ๋, username,password ๋ณ์ 2๊ฐ๋ฅผ ๊ฐ๋ก์ฑ๋๋ฐ
    // password ๋ถ๋ถ ์ฒ๋ฆฌ๋ ์์์ ํจ.
    // username์ด DB์ ์๋์ง๋ง ํ์ธํด์ฃผ๋ฉด ๋จ.
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User principal = userRepository.findByUsername(username) // username์ด ์ผ์นํ๋ ์ฌ์ฉ์ ์ฐพ๊ธฐ
                .orElseThrow(()->{
                    return new UsernameNotFoundException("ํด๋น ์ฌ์ฉ์๋ฅผ ์ฐพ์ ์ ์์ต๋๋ค.");
                });
        return new PrincipalDetail(principal); // ์ํ๋ฆฌํฐ์ ์ธ์์ ์ ์  ์ ๋ณด๊ฐ ์ ์ฅ์ด ๋จ.
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

// ๋น ๋ฑ๋ก: ์คํ๋ง ์ปจํ์ด๋์์ ๊ฐ์ฒด๋ฅผ ๊ด๋ฆฌํ  ์ ์๊ฒ ํ๋ ๊ฒ
@Configuration // ๋น ๋ฑ๋ก: IoC
@EnableWebSecurity // ํํฐ ์ถ๊ฐ: ์ํ๋ฆฌํฐ ํํฐ๋ฅผ ๊ฑฐ๋ ๊ฒ
@EnableGlobalAuthentication // ํน์  ์ฃผ์๋ก ์ ๊ทผ์ํ๋ฉด ๊ถํ ๋ฐ ์ธ์ฆ์ ๋ฏธ๋ฆฌ ์ฒดํฌํ๋ ๊ฒ
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final PrincipalDetailService principalDetailService; // DI

    @Bean // ์คํ๋ง์ด ๊ด๋ฆฌํ๋ IoC๊ฐ ๋๋ค.
    public BCryptPasswordEncoder encodePWD(){
        return new BCryptPasswordEncoder();
    }

    // ์ํ๋ฆฌํฐ๊ฐ ๋์  ๋ก๊ทธ์ธํด์ฃผ๋๋ฐ password๋ฅผ ๊ฐ๋ก์ฑ๊ธฐ๋ฅผ ํ๋๋ฐ
    // ํด๋น password๊ฐ ๋ญ๋ก ํด์ฌ๊ฐ ๋์ด ํ์๊ฐ์์ด ๋์๋์ง ์์์ผ
    // ๊ฐ์ ํด์ฌ๋ก ์ํธํํด์ DB์ ์๋ ํด์ฌ๋ ๋น๊ตํ  ์ ์์.
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(principalDetailService).passwordEncoder(encodePWD());
        // principalDetailService๋ฅผ ํตํด์ ๋ก๊ทธ์ธ์ํ  ๋ password๋ฅผ encodePWD๋ก ์ธ์ฝ๋ํด์ ๋น๊ต๋ฅผ ์์์ ํด์ค๋ค.
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
                .csrf().disable() // csrf ํ ํฐ ๋นํ์ฑํ (ํ์คํธ์ ๊ฑธ์ด๋๋ ๊ฒ ์ข์)
                .authorizeRequests()
                    .antMatchers("/","/auth/**","/js/**","/css/**","/image/**")// /auth/ ์ดํ์ ๋ชจ๋  ๊ฒฝ๋ก๋
                    .permitAll() // ๋๊ตฌ๋ ์ ๊ทผ์ด ๊ฐ๋ฅํ๋ค
                .anyRequest() // ๊ทธ๊ฒ ์๋๊ณ ๋
                    .authenticated() // ํ๋ฝ๋ ์ฌ๋๋ง ์ ๊ทผ ๊ฐ๋ฅํ๋ค.
                .and()
                    .formLogin()
                    .loginPage("/auth/loginForm")
                    .loginProcessingUrl("/auth/loginProc") // ์คํ๋ง ์ํ๋ฆฌํฐ๊ฐ ํด๋น ์ฃผ์๋ก ์์ฒญ์ค๋ ๋ก๊ทธ์ธ์ ๊ฐ๋ก์ฑ์ ๋์  ๋ก๊ทธ์ธ ํด์ค๋ค.
                    .defaultSuccessUrl("/");
    }
}

```

ํด๋น ์ฝ๋๋ฅผ ์ถ๊ฐํด์ค๋ค.

๋ก๊ทธ์ธ์์ฒญ์ด ์ค๋ ์๊ฐ, loginProcessingUrl์ด ๊ฐ๋ก์ฑ๋ค. => username๊ณผ password ์ ๋ณด๋ฅผ PrincipalDetailService์ ์๋ loadUserByUsername์ผ๋ก ๋ณด๋ธ๋ค.

=> username์ ๋น๊ตํด์ PrincipalDetail์ ๋ฆฌํดํด์ค๋ค. => ๋ฆฌํดํ ๋ ๋น๋ฐ๋ฒํธ ์ฒดํฌ๋ฅผ ํ๋ค. SecurityConfig์ configure์ ํตํด์ principalDetailService๊ฐ ๋ก๊ทธ์ธ ์์ฒญ์ ํ๊ณ 

=> auth.userDetailsService(principalDetailService) ์ด ๋ฆฌํด์ด ๋๋ฉด, passwordEncoder๋ฅผ ํตํด encodePWD๋ก ๋ค์ ์ํธํ๋ฅผ ํ๊ณ , ๋ฐ์ดํฐ ๋ฒ ์ด์ค์ ๋น๊ตํ๋ค.

=> ๋น๊ต๊ฐ ๋๋๋ฉด Spring Security ์์ญ์ PrincipalDetail๋ก ๊ฐ์ธ์ ธ์ ์ ์ฅ์ด ๋๋ค.

<img width="1326" alt="แแณแแณแแตแซแแฃแบ 2022-02-08 แแฉแแฎ 10 29 04" src="https://user-images.githubusercontent.com/79779676/152996644-77c6465f-061e-401e-b65b-7bd56e682128.png">


## ๐ ๊ธ์ฐ๊ธฐ ๊ธฐ๋ฅ ๊ตฌํํ๊ธฐ

๊ธ์ฐ๊ธฐ ๊ธฐ๋ฅ์ ๊ตฌํํ๊ธฐ ์ํด์ ๊ฐ์ฅ๋จผ์  ๊ธ์ฐ๊ธฐ ํ์ด์ง๋ฅผ ์์ฑํด์คฌ๋ค. (/resources/templates/board/saveForm.html)

๊ทธ ๋ค์ ํ์์ ๊ธ์ฐ๊ธฐ ๋ฒํผ์ ๋๋ ์๋, ๊ธ์ฐ๊ธฐ ํ์ด์ง๋ก ๋งคํ๋๋๋ก BoardController์ (/board/saveForm) GET ์์ฒญ์ ๋ฐ์ ์ ์๋๋ก GetMapping์ ์ถ๊ฐํ๋ค.

๊ธ์ ์ธ ์ ์๋ ํ์ด์ง์ธ saveForm.html์๋ ์ ๋ชฉ๊ณผ ๋ด์ฉ์ ์์ฑํ  ์ ์๋๋กํ๊ณ , ๊ธ์ฐ๊ธฐ ์๋ฃ๋ฒํผ์ ajaxํต์ ์ ์ํด formํ๊ทธ ๋ฐ์ผ๋ก ๊บผ๋๋ค.

```html
<!doctype html>
<html xmlns:th="http://www.thymeleaf.org">
<head th:replace="/layout/fragments/header::header"/>
<body>

<div class="container">
    <div th:replace="/layout/fragments/bodyHeader::bodyHeader"/>

    <form>
        <div class="form-group">
            <label for="title">Username</label>
            <input type="text" class="form-control" placeholder="Enter title" id="title">
        </div>
        <div class="form-group">
            <label for="content">Content:</label>
            <textarea class="form-control summernote" rows="5" id="content"></textarea>
        </div>
    </form>
    <button id="save" class="btn btn-primary">๊ธ์ฐ๊ธฐ ์๋ฃ</button>
    <script>
        $('.summernote').summernote({
            placeholder: 'Hello Bootstrap 4',
            tabsize: 2,
            height: 300
        });
    </script>
    <div th:replace="/layout/fragments/footer::footer"/>
</div>
</body>
<script src="/js/board.js"></script>
</html>
```

๋ด์ฉ ๋ถ๋ถ์ ํ์คํธ ์์๋ฅผ ๋ง๋ค ์ ์์ง๋ง, ๋๋ฌด ํ์ ํด์ summer note๋ฅผ ์ฌ์ฉํ๋ค. [summer note](https://summernote.org/getting-started/#for-bootstrap-4)

ํด๋น ์ฝ๋์์ ์์์๋ถํฐ scriptํ๊ทธ 2๊ฐ๊น์ง๋ ๊ฐ๊ฐ jQuery, boostrap4์ ๊ด๋ จ๋ ๋ด์ฉ์ด๋ฏ๋ก ์ด๋ฏธ ์ถ๊ฐํ๋ค๋ฉด ์ง์์ฃผ์.

์ด์  ajax ํต์ ์ ํด์ ๋ฐ์ดํฐ๋ฒ ์ด์ค์ ์์ฑํ ๊ธ์ ๋ฃ์ด์ค์ผํ๋ฏ๋ก, (resources/static/js/board.js) ํ์ผ์ ์์ฑํ๋ค.

user.js์ ๋น์ทํ๋ฏ๋ก ๋ฐ์ดํฐ ๋ง์ถฐ์ฃผ๊ณ  ํต์ ~

```javascript
let index = {
    init:function(){
        // btn-save ๋ฒํผ์ด ํด๋ฆญ๋๋ฉด, saveํจ์๋ฅผ ํธ์ถ
        document.querySelector("#save").addEventListener('click',()=>{
            this.save();
        });
    },

    save:function(){
        let data = {
            title: document.querySelector("#title").value,
            content: document.querySelector("#content").value
        }

        // ajax ์์ฒญ
        fetch("/api/board",{
            method:'POST',headers:{'content-type':'application/json'},body:JSON.stringify(data)
        })
            .then(response => response.json())
            .then(data=>{
                alert("๊ธ์ฐ๊ธฐ ์๋ฃ");
                console.log(data);
                location.href="/";
            })
            .catch(error=>{alert(error.message)});
    }
};

index.init();
```

์ด๋ ๊ฒ ๋๋ฉด, ๊ธ์ ์์ฑํ๊ณ  ๊ธ์ฐ๊ธฐ ์๋ฃ ๋ฒํผ์ ๋๋ฅด๊ฒ ๋๋ฉด, /api/board ๋ก post์์ฒญ์ด ๋ค์ด๊ฐ๊ฒ ๋๋ค.

์ฐ๋ฆฌ๋ ์์ง board์ ๋ํ api ์ฒ๋ฆฌ๋ฅผ ํ์ง ์์๊ธฐ ๋๋ฌธ์

BoardApiController(ํด๋์ค), BoardService(ํด๋์ค), BoardRepository(์ธํฐํ์ด์ค) ๋ฅผ ๊ฐ๊ฐ ์์ฑํด์ค๋ค.

BoardApiController๋ฅผ ํตํด BoardService์ Board,User ๊ฐ์ฒด๋ฅผ ๋๊ธฐ๋ฉด

BoardService๋ Board์ User๊ฐ์ฒด๋ฅผ ๋ฃ์ด DB์ ์ ์ฅํด์ค๋ค. ( Board์ ๋๊ฐ์ด๊ฑด์ง ์ ์ฅํ๊ธฐ ์ํ Userํ์ด๋ธ์ด ์๊ธฐ ๋๋ฌธ์ )

```java
// BoardApiController.class
package com.cos.blog.controller.api;

import com.cos.blog.config.auth.PrincipalDetail;
import com.cos.blog.controller.dto.ResponseDto;
import com.cos.blog.model.Board;
import com.cos.blog.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BoardApiController {

    private final BoardService boardService;

    @PostMapping("/api/board")
    public ResponseDto<Integer> save(@RequestBody Board board, @AuthenticationPrincipal PrincipalDetail principal){
        boardService.save(board,principal.getUser());
        return new ResponseDto<>(HttpStatus.OK.value(), 1);
    }
}
```

```java
// BoardService.class
package com.cos.blog.service;

import com.cos.blog.model.Board;
import com.cos.blog.model.User;
import com.cos.blog.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    @Transactional
    public void save(Board board, User user){
        board.setCount(0L);
        board.setUser(user);
        boardRepository.save(board);
    }

}
```

```java
// BoardRepository.class
package com.cos.blog.repository;

import com.cos.blog.model.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board,Long> {

}
```

์ด๋ ๊ฒ ๊ตฌํํด์ ๊ธ์ฐ๊ธฐ ์๋ฃ๋ฅผ ๋๋ฌ๋ณด๋ฉด ์๋์ฒ๋ผ DB์ ์ ๋ค์ด๊ฐ ๋ชจ์ต์ ๋ณผ ์ ์๋ค.

<img width="704" alt="แแณแแณแแตแซแแฃแบ 2022-02-10 แแฉแแฎ 8 39 31" src="https://user-images.githubusercontent.com/79779676/153401536-0cc1d461-934c-4fc7-92ae-1cc2a3a3e830.png">

<br/>

## ๐ ๊ธ ๋ชฉ๋ก ๋ถ๋ฌ์ค๊ธฐ

์ง๊ธ ๊ตฌํํ๊ณ  ์๋ ๊ฒ์ํ์ ๋ชจ๋  ์ ์ ์ ๊ธ ๋ชฉ๋ก๋ค์ ๋ณผ ์ ์๊ธฐ ๋๋ฌธ์ ๊ธ ๋ชฉ๋ก์ ๋ถ๋ฌ์ค๋๊ฒ์ ์์ฃผ ๊ฐ๋จํฉ๋๋ค.

๊ทธ๋ฅ BoardRepository์ findAll() ๋ฉ์๋๋ฅผ ์๋ ฅํ๋ฉด Listํํ๋ก ์ ์ฒด ๊ธ ๋ชฉ๋ก์ ์กฐํํ  ์ ์์ต๋๋ค.

BoardService๋ contentList๋ฉ์๋๋ฅผ ์์ฑํด์ฃผ๊ณ  ๊ธ ๋ชฉ๋ก์ ๊ฐ์ ธ์จ ๋ค List๋ก ๋ฐํํด์ฃผ๋ฉด ๋ฉ๋๋ค.

```java
package com.cos.blog.service;

import com.cos.blog.model.Board;
import com.cos.blog.model.User;
import com.cos.blog.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    @Transactional
    public void save(Board board, User user){
        board.setCount(0L);
        board.setUser(user);
        boardRepository.save(board);
    }

    // ์๋ซ ๋ถ๋ถ์๋๋น.
    @Transactional(readOnly = true)
    public List<Board> contentList(){
        return boardRepository.findAll();
    }
}
```

์ด๋ ๊ฒ Listํํ๋ก ๋ฐํํ๊ฒ๋๋ฉด, BoardController์์๋ index.html(๊ฒ์ํ)์ผ๋ก ํด๋น ๋ฆฌ์คํธ๋ฅผ ๋๊ฒจ์ฃผ๋ฉด ๋๋๋ฐ

์คํ๋ง์์๋ Model ๊ฐ์ฒด๋ฅผ ์ด์ฉํฉ๋๋ค.

```java
package com.cos.blog.controller;

import com.cos.blog.config.auth.PrincipalDetail;
import com.cos.blog.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    // ์๋ถ๋ถ ์๋๋น.
    @GetMapping({"","/"})
    public String index(Model model){
        model.addAttribute("boards",boardService.contentList());
        return "index"; // viewResolver ์๋
    }

    @GetMapping("/board/saveForm")
    public String saveForm(){
        return "board/saveForm";
    }
}
```

model.addAttribute("key","value") ์ด๋ค.

index.html์์ model์ ์ถ๊ฐํ ๊ฒ์ ์ฌ์ฉํ  ์ ์๋ค.

```html
<!doctype html>
<html xmlns:th="http://www.thymeleaf.org">
<head th:replace="/layout/fragments/header::header"/>
<body>

<div class="container">
    <div th:replace="/layout/fragments/bodyHeader::bodyHeader"/>

    <div th:each="board : ${boards}">
        <div class="card m-2">
            <div class="card-body">
                <h4 class="card-title" th:text="${board.getTitle()}">์ ๋ชฉ ์ ๋ ๋ถ๋ถ</h4>
                <a href="#" class="btn btn-primary">์์ธ ๋ณด๊ธฐ</a>
            </div>
        </div>
    </div>

    <div th:replace="/layout/fragments/footer::footer"/>
</div>
</body>
</html>
```

th:each ๋ foreach์ ๊ฐ์ ์ญํ ์ ํ๋ค. boards๊ฐ List๋ก ๋์ด์๊ธฐ๋๋ฌธ์, boards๋ฅผ ํ๊ฐ์ฉ ๊ฐ์ ธ์ ์ ๋ชฉ์ ๋ฃ์ด์ค๋ค.

<br/>

## ๐ ์คํ๋ง ์ํ๋ฆฌํฐ์ ๊ตฌ์กฐ

<img width="790" alt="แแณแแณแแตแซแแฃแบ 2022-02-15 แแฉแแฎ 4 59 31" src="https://user-images.githubusercontent.com/79779676/154017847-96a747ed-896d-4b2c-aabb-ed73dc647be2.png">
