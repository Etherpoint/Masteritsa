package ru.familyproject.ryabov.masteritsa.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import ru.familyproject.ryabov.masteritsa.utils.Endpoints;

import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class AboutInfoControllerIntegrationTest {
    @Autowired
    MockMvc mockMvc;
    @Test
    void getHtmlFileAboutWhenCallsMethodGetInfo() throws Exception {
        this.mockMvc.perform(get(Endpoints.ABOUT).with(user("Наталья")))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(xpath("/html/body/section/header/div/div/h1[1]")
                        .string("Данный сайт предназначен для презентации и удобной иллюстрации украшений ручной работы."))
                .andExpect(content().string(containsString("Наталья")));
    }
}