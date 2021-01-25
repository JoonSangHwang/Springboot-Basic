package com.joonsang.example.SpringbootBasic.controller;

import com.joonsang.example.SpringbootBasic.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    /**
     * << MessageConverter >>
     *
     * - 스프링 프레임워크에서 제공하는 인터페이스
     * - RequestBody 에 담긴 내용을 Controller 에서 사용하기 위해 Argument 로 변환할 때 사용
     * - ResponseBody 를 작성할 때 사용
     * - 관련 어노테이션 : @ResponseBody / @RequestBody
     * - @RestController 인 경우, 모든 메소드에 @ResponseBody 부여한것과 같아서 생략 가능하다.
     *   기본적으로 MessageConverter 가 적용 되기 때문이다.
     * - Composition 타입일 경우, 기본적으로 JsonMessageConverter 이며 Primitive 타입일 경우, StringMessageConverter 이다.
     * - @Controller 인 경우, @ResponseBody 를 생략하면, MessageConverter 가 적용 안됨. 안그러면 ViewNameResolver 를 타서 view 를 찾게 됨.
     */
    @PostMapping("/user")
    public @ResponseBody User create(@RequestBody User user) {
        return null;
    }

    @PostMapping("/users/create")
    public User createUser(@RequestBody User user) {
        return user;
    }

}
