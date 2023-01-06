package ru.familyproject.ryabov.masteritsa.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.xpath;

@SpringBootTest
@AutoConfigureMockMvc
class SaleControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Test
    void getHtmlFileSalesWhenCallsMethodGetSales() throws Exception {
        this.mockMvc.perform(get("/sales")
                        .with(user("Наталья")))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(xpath("/html/body/h1").string("Страница находится в разработке"));
    }
}