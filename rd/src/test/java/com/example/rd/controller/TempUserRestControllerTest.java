package com.example.rd.controller;


import com.example.rd.entity.TempUser;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@AutoConfigureRestDocs                      // REST Docs
class TempUserRestControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @Test
    @DisplayName("Restdocs 적용 (1) - 전체 유저 조회")
    public void readAll() throws Exception {

        mockMvc.perform(get("/readAll")                       // 요청 - URL
                    .contentType(MediaType.APPLICATION_JSON_VALUE)      // 요청 - 콘텐츠 타입
                    .accept(MediaTypes.HAL_JSON_VALUE)                  // 요청 - 미디어 타입
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("docReadAll"));                 // 문서 이름
    }



    @Test
    @DisplayName("Restdocs 적용 (1) - 유저 생성")
    public void createUser() throws Exception {
        TempUser tempUser = TempUser.builder()
                .id(1L)
                .username("junsang")
                .password("1234")
                .build();

        mockMvc.perform(post("/createUser")                             // 요청 - URL
                        .contentType(MediaType.APPLICATION_JSON_VALUE)      // 요청 - 콘텐츠 타입
                        .accept(MediaTypes.HAL_JSON_VALUE)                  // 요청 - 미디어 타입
                        .content(objectMapper.writeValueAsString(tempUser)) // 요청 - 콘텐츠
        )
                .andDo(print())                                      // 응답과 요청 출력
                .andExpect(status().isOk())
                .andDo(document("docCreateUser"));
    }

}