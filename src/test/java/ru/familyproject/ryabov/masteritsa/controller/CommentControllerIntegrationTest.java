package ru.familyproject.ryabov.masteritsa.controller;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import ru.familyproject.ryabov.masteritsa.utils.Endpoints;

import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class CommentControllerIntegrationTest {
    @Autowired
    MockMvc mockMvc;
    @ParameterizedTest
    @CsvSource(delimiterString = ", ",
            value = {
                    "1, Тестовый комментарий-1",
                    "2, Тестовый комментарий-2",
                    "3, Тестовый комментарий-3"
            })
    void saveCommentAndReloadPage(String id, String comment) throws Exception {
        MockHttpServletRequestBuilder multipart = multipart(Endpoints.COMMENT + "/" + id)
                .param("description", comment)
                .with(csrf());
        this.mockMvc.perform(multipart.with(user("Наталья")))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl(Endpoints.PRODUCT + "/" + id));

        this.mockMvc.perform(get(Endpoints.PRODUCT + "/" + id).with(user("Наталья")))
                .andDo(print())
                .andExpect(content().string(containsString(comment)));
    }
}