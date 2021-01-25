package com.joonsang.example.SpringbootBasic.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    @DisplayName("상태코드 OK")
    public void hello() throws Exception {
        mockMvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string("hello"))
        ;
    }

    @Test
    @DisplayName("HttpMessageConverters 테스트")
    public void createUser_JSON() throws Exception {

        String userJson = "{\"username\":\"joonsang\", \"password\":\"1234\"}";

        mockMvc.perform(post("/users/create")
                .contentType(MediaType.APPLICATION_JSON)        // 클라이언트가 요청에 담아 보내는 데이터(body)의 형식(MediaType)
                .accept(MediaType.APPLICATION_JSON)             // 클라이언트가 서버에 어떤 형식(MediaType)으로 달라는 요청
                .content(userJson))                             // 내용 ^^
                .andDo(print())                                 // 실패할 경우 andDo(print()) 가 자동으로 발동. 성공 시, 스킵
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.username", is(equalTo("joonsang"))))
            .andExpect(jsonPath("$.password", is(equalTo("1234"))))
        ;
    }


}