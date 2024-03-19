package com.example.helloworld;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // RESTful 웹 서비스의 컨트롤러임을 나타냄
@SpringBootApplication // Spring Boot 애플리케이션을 시작하기 위한 메타 어노테이션
public class HelloworldApplication {

    // "/" 경로에 대한 HTTP GET 요청을 처리하는 메서드
    @RequestMapping("/")
    String home() {
        return "Hello World!!!"; // "Hello World!!!" 문자열을 반환하여 요청에 응답
    }

    // 애플리케이션의 진입점
    public static void main(String[] args) {
        SpringApplication.run(HelloworldApplication.class, args); // Spring Boot 애플리케이션 시작
    }

}
