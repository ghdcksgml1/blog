package com.cos.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

// 빈 등록: 스프링 컨테이너에서 객체를 관리할 수 있게 하는 것
@Configuration // 빈 등록: IoC
@EnableWebSecurity // 필터 추가: 시큐리티 필터를 거는 것
@EnableGlobalAuthentication // 특정 주소로 접근을하면 권한 및 인증을 미리 체크하는 것
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean // 스프링이 관리하는 IoC가 된다.
    public BCryptPasswordEncoder encodePWD(){
        return new BCryptPasswordEncoder();
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
                    .loginPage("/auth/loginForm");
    }
}
