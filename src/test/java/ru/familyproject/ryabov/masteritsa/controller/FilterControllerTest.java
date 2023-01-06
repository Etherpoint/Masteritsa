package ru.familyproject.ryabov.masteritsa.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.familyproject.ryabov.masteritsa.entity.Product;
import ru.familyproject.ryabov.masteritsa.entity.ProductType;
import ru.familyproject.ryabov.masteritsa.service.ProductService;
import ru.familyproject.ryabov.masteritsa.service.ProductTypeService;
import ru.familyproject.ryabov.masteritsa.service.UserService;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class FilterControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    UserService service;
    @MockBean
    ProductService productService;
    @MockBean
    ProductTypeService productTypeService;

    private List<ProductType> types;

    private List<Product> products;

    @BeforeEach
    void init() {
        ProductType type1 = new ProductType(1L, "Корона 1");
        ProductType type2 = new ProductType(2L, "Корона 2");
        this.types = new ArrayList<>();
        types.add(type1);
        types.add(type2);

        this.products = new ArrayList<>();
        products.add(new Product(1L, "ТестКорона1", 100.0D, "img1.img", "Описание", "Зеленый", type1, new ArrayList<>()));
        products.add(new Product(2L, "ТестКорона2", 150.0D, "img2.img", "Описание", "Зеленый", type1, new ArrayList<>()));
    }


    @Test
    void getAllProducts_WhenCallsMethod_getAll_InMethodGetAllProducts() throws Exception {
        Mockito.when(productService.getAll()).thenReturn(products);
        this.mockMvc.perform(get("/products/all").with(user("Наталья")))
                .andDo(print())
                .andExpect(status().isOk());
        verify(productService, times(1)).getAll();
    }

    @Test
    void getAllProductTypes_WhenCallsMethod_getAll_InMethodGetAllProducts() throws Exception {
        Mockito.when(productTypeService.getAll()).thenReturn(types);
        this.mockMvc.perform(get("/products/all").with(user("Наталья")))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Корона 1")));
        verify(productTypeService, times(1)).getAll();
    }

    @Test
    void getAllProductTypes_WhenCallsMethod_getAll_InMethodGetAllProductsById() throws Exception {
        Mockito.when(productTypeService.getAll()).thenReturn(types);
        this.mockMvc.perform(get("/products/1").with(user("Наталья")));
        verify(productTypeService, times(1)).getAll();
    }

    @Test
    void getAllProductsWhereIdEqualsOne_WhenCallsMethod_getAllProductsById_WithParameterEqualsOne_InMethodGetAllProductsById() throws Exception {
        Mockito.when(productService.getAllById(1L)).thenReturn(products);
        this.mockMvc.perform(get("/products/1").with(user("Наталья")));
        verify(productService, times(1)).getAllById(1L);
    }
}