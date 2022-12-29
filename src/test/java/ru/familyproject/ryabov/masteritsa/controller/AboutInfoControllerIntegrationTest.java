package ru.familyproject.ryabov.masteritsa.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.xpath;

@SpringBootTest
@AutoConfigureMockMvc
class AboutInfoControllerIntegrationTest {
    @Autowired
    MockMvc mockMvc;

    @Test
    void getHtmlFileAboutWhenCallsMethodGetInfo() throws Exception {
        this.mockMvc.perform(get("/about"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(xpath("/html/body/section/header/div/div/h1[1]")
                        .string("Данный сайт предназначен для презентации и удобной иллюстрации украшений ручной работы."));
    }
}