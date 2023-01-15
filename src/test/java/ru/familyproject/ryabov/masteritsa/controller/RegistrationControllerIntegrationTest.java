package ru.familyproject.ryabov.masteritsa.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.familyproject.ryabov.masteritsa.utils.Endpoints;

import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class RegistrationControllerIntegrationTest {
    @Autowired
    MockMvc mockMvc;

    @Test
    void getRegistrationPage_WhenCallsGetMethod_registration() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get(Endpoints.REGISTRATION))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Создание аккаунта")));
    }

    @ParameterizedTest
    @CsvSource(delimiterString = ", ",
            value = {
                    "Danila, Данила, Рябов, пароль, pochta@mail.ru",
                    "Victor, Виктор, Викторов, сложный пароль, victor@gmail.com",
                    "Anna, Анна, Александрова, password, anna@yandex.ru"
            })
    void getRedirectOnMainPageWhenCallsPostMethod_addUser(
            String name,
            String firstName,
            String lastName,
            String password,
            String email) throws Exception {
        MockHttpServletRequestBuilder multipart = multipart(Endpoints.REGISTRATION)
                .file("file", "image.png".getBytes())
                .param("name", name)
                .param("firstName", firstName)
                .param("lastName", lastName)
                .param("email", email)
                .param("password", password)
                .with(csrf());
        this.mockMvc.perform(multipart)
                .andDo(print())
                .andExpect(status().is3xxRedirection());
    }
}