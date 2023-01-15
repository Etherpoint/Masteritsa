package ru.familyproject.ryabov.masteritsa.controller;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import ru.familyproject.ryabov.masteritsa.utils.Endpoints;

import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AccountControllerIntegrationTest {
    @Autowired
    MockMvc mockMvc;

    @ParameterizedTest
    @CsvSource(delimiterString = ", ",
            value = {
            "1, Наталья"
    })
    void getAccountPageWhenCallsMethod_getAccount(Long id, String username) throws Exception {
        this.mockMvc.perform(get(Endpoints.ACCOUNT + "/" + id)
                .with(user(username)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(username)));
    }
}