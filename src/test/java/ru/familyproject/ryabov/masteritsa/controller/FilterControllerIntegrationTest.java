package ru.familyproject.ryabov.masteritsa.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
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
class FilterControllerIntegrationTest {
    @Autowired
    MockMvc mockMvc;

    @Test
    void contentLoadingWhenCallsMethodGetAllProducts() throws Exception {
        this.mockMvc.perform(get("/products/all").with(user("Наталья")))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Корона Славяночка")))
                .andExpect(content().string(containsString("Корона Карнавал")))
                .andExpect(content().string(containsString("image-1.jpg")))
                .andExpect(content().string(containsString("image-2.jpg")))
        .andExpect(content().string(containsString("Наталья")));
    }

    @ParameterizedTest
    @CsvSource({
            "2,Корона Славяночка,image-1.jpg",
            "9,Корона Карнавал,image-2.jpg"
    })
    void contentLoadingWhenCallsMethodGetAllProductsByIdEqualsOne(String id, String name, String imageSrc) throws Exception {
        this.mockMvc.perform(get("/products/" + id).with(user("Наталья")))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(name)))
                .andExpect(content().string(containsString(imageSrc)))
        .andExpect(content().string(containsString("Наталья")));
    }

    @ParameterizedTest
    @CsvSource({
            "2,Корона Карнавал",
            "9,Корона Cлавяночка",
            "2,image-2.jpg",
            "9,image-1.jpg"
    })
    void ThrowsAssertionErrorWhenCallsMethodGetAllProducts(String id, String unfoundInfo) {
        Assertions.assertThrows(AssertionError.class,
                () -> this.mockMvc.perform(get("/products/" + id))
                        .andDo(print())
                        .andExpect(status().isOk())
                        .andExpect(content().string(containsString(unfoundInfo)))
                        .andExpect(content().string(containsString("Наталья"))));
    }
}