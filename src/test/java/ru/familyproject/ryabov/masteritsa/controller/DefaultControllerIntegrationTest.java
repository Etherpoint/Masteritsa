package ru.familyproject.ryabov.masteritsa.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class DefaultControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;
    @Test
    void contentLoadingWhenCallsMethodIndex() throws Exception {
        this.mockMvc.perform(get("/")
                        .with(user("Наталья")))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Главная страница")))
                .andExpect(content().string(containsString("Славяночка")))
                .andExpect(content().string(containsString("Наталья")));
    }
}