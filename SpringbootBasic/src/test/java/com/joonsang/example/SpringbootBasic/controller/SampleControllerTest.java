package com.joonsang.example.SpringbootBasic.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.joonsang.example.SpringbootBasic.entity.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.restdocs.headers.HeaderDocumentation.*;
import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.linkWithRel;
import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.links;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
class SampleControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @Test
    @DisplayName("Hateoas 테스트")
    public void hateoasTest() throws Exception {

        mockMvc.perform(get("/hateoasTest")
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .accept(MediaTypes.HAL_JSON_VALUE))
                    .andExpect(status().isOk())
                    .andDo(print()
                )

                /* HATEOAS */
                .andExpect(jsonPath("_links.self").exists())
        ;
    }

    @Test
    @DisplayName("RestDocs 테스트")
    public void restDocsTest() throws Exception {
        User user = User.builder()
                .id(1L)
                .username("joonsang")
                .password("1234")
                .build();

        mockMvc.perform(get("/restDocsTest")
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .accept(MediaTypes.HAL_JSON_VALUE)
                    .content(objectMapper.writeValueAsString(user))
                )
                .andDo(print())
                .andExpect(status().is2xxSuccessful())

                /* HATEOAS */
//                .andExpect(jsonPath("_links.self").exists())

                /* RestDocs */
                .andDo(document("create-restDocsTest",
                        links(                      // 링크 문서화
//                                linkWithRel("self").description("link to self")
                        ),
                        requestHeaders(             // 요청 헤더 문서화
                                headerWithName(HttpHeaders.ACCEPT).description("accept header"),
                                headerWithName(HttpHeaders.CONTENT_TYPE).description("content type header")
                        ),
                        requestFields(              // 요청 본문 문서화
//                                fieldWithPath("name").description("이름")
                        ),
                        responseHeaders(            // 응답 헤더 문서화
//                                headerWithName(HttpHeaders.LOCATION).description("Location header")
                        ),
                        responseFields(             // 응답 본문 문서화
//                                fieldWithPath("id").description("identifier of new event")
                        )
                ))
        ;
    }

}