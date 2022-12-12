package ru.familyproject.ryabov.masteritsa.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
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

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.mockito.Mockito.*;

@WebMvcTest
class FilterControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    ProductService productService;
    @MockBean
    ProductTypeService productTypeService;

    private List<ProductType> types;

    private List<Product> products;

    @BeforeEach
    void init(){
        this.types = new ArrayList<>();
        types.add(new ProductType(1L, "Корона 1"));

        this.products = new ArrayList<>();
        products.add(new Product());
    }


    @Test
    void getAllProducts_WhenCallsMethod_getAll_InMethodGetAllProducts() throws Exception {
        Mockito.when(productTypeService.getAll()).thenReturn(types);
        this.mockMvc.perform(get("/products/all"));
        verify(productTypeService, times(1)).getAll();
    }

    @Test
    void getAllProductTypes_WhenCallsMethod_getAll_InMethodGetAllProducts() throws Exception {
        Mockito.when(productTypeService.getAll()).thenReturn(types);
        this.mockMvc.perform(get("/products/all"));
        verify(productTypeService, times(1)).getAll();
    }

    @Test
    void getAllProductTypes_WhenCallsMethod_getAll_InMethodGetAllProductsById() throws Exception {
        Mockito.when(productTypeService.getAll()).thenReturn(types);
        this.mockMvc.perform(get("/products/1"));
        verify(productTypeService, times(1)).getAll();
    }

    @Test
    void getAllProductsWhereIdEqualsOne_WhenCallsMethod_getAllProductsById_WithParameterEqualsOne_InMethodGetAllProductsById() throws Exception {
        Mockito.when(productService.getAllById(1L)).thenReturn(products);
        this.mockMvc.perform(get("/products/1"));
        verify(productService, times(1)).getAllById(1L);
    }
}