package com.joonsang.example.SpringbootBasic.restdocs;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.joonsang.example.SpringbootBasic.common.RestDocsConfiguration;
import com.joonsang.example.SpringbootBasic.entity.User;
import org.junit.Ignore;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@AutoConfigureRestDocs                      // REST Docs
@Import(RestDocsConfiguration.class)        // REST Docs Pretty Type
@Ignore                                     // Test 로 간주되지 않음
class SampleRestControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @Test
    @DisplayName("AA 테스트")
    public void AA() throws Exception {
        mockMvc.perform(get("/AA")
                .contentType(MediaType.APPLICATION_JSON_VALUE)    // Header의 Content-Type
                .accept(MediaTypes.HAL_JSON_VALUE)                // 요구 Content-Type
        )
                .andDo(print())                                      // 응답과 요청 출력
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

        mockMvc.perform(get("/restDocsTest")                // Request
                .contentType(MediaType.APPLICATION_JSON_VALUE)    // Header의 Content-Type
                .accept(MediaTypes.HAL_JSON_VALUE)                // 요구 Content-Type
                .content(objectMapper.writeValueAsString(user))
        )
                .andDo(print())                                      // 응답과 요청 출력

                .andExpect(status().is2xxSuccessful())

                /* HATEOAS */
//                .andExpect(jsonPath("_links.self").exists())

                /* RestDocs */
                .andDo(document("restDocsTest"
//                        links(                      // 링크 문서화
////                                linkWithRel("self").description("link to self")
//                        ),
//                        requestHeaders(             // 요청 헤더 문서화
//                                headerWithName(HttpHeaders.ACCEPT).description("accept header"),
//                                headerWithName(HttpHeaders.CONTENT_TYPE).description("content type header")
//                        ),
//                        requestFields(              // 요청 본문 문서화
////                                fieldWithPath("name").description("이름")
//                        ),
//                        responseHeaders(            // 응답 헤더 문서화
////                                headerWithName(HttpHeaders.LOCATION).description("Location header")
//                        ),
//                        responseFields(             // 응답 본문 문서화
////                                fieldWithPath("id").description("identifier of new event")
//                        )
                ))
        ;
    }

}