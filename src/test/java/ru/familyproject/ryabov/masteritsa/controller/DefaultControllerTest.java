package ru.familyproject.ryabov.masteritsa.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.Model;
import ru.familyproject.ryabov.masteritsa.entity.ProductType;
import ru.familyproject.ryabov.masteritsa.service.ProductService;
import ru.familyproject.ryabov.masteritsa.service.ProductTypeService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebMvcTest
class DefaultControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    ProductTypeService productTypeService;
    @MockBean
    ProductService productService;
    @MockBean
    Model model;

    private List<ProductType> types;

    @BeforeEach
    void init(){
        this.types = new ArrayList<>();
        types.add(new ProductType(1L, "Корона 1"));
        types.add(new ProductType(2L, "Корона 2"));
        types.add(new ProductType(3L, "Корона 3"));
    }

    @Test
    void doReturnMainPageWhen() throws Exception {
        Mockito.when(productTypeService.getAll()).thenReturn(types);
        given(productTypeService.getAll()).willReturn(types);
        this.mockMvc.perform(get("/"));
        verify(productTypeService, times(1)).getAll();
    }
}