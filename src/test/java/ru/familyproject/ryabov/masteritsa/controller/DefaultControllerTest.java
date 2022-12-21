package ru.familyproject.ryabov.masteritsa.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.familyproject.ryabov.masteritsa.entity.ProductType;
import ru.familyproject.ryabov.masteritsa.service.ProductService;
import ru.familyproject.ryabov.masteritsa.service.ProductTypeService;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class DefaultControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    ProductTypeService productTypeService;
    @MockBean
    ProductService productService;
    private List<ProductType> types;

    @BeforeEach
    void init(){
        this.types = new ArrayList<>();
        types.add(new ProductType(1L, "Корона 1"));
    }

    @Test
    void contentLoadingWhenCallsMethodIndex() throws Exception {
        Mockito.when(productTypeService.getAll()).thenReturn(types);
        this.mockMvc.perform(get("/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Главная страница")))
                .andExpect(content().string(containsString("Корона 1")));
        verify(productTypeService, times(1)).getAll();
    }
}