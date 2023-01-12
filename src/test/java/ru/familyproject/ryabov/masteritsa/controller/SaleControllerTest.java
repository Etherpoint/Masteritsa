package ru.familyproject.ryabov.masteritsa.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.familyproject.ryabov.masteritsa.utils.Endpoints;

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
    @WithMockUser(username = "Наталья")
    void getHtmlFileSalesWhenCallsMethodGetSales() throws Exception {
        this.mockMvc.perform(get(Endpoints.SALES))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(xpath("/html/body/h1").string("Страница находится в разработке"));
    }
}