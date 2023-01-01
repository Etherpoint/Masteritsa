package ru.familyproject.ryabov.masteritsa.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.familyproject.ryabov.masteritsa.entity.Comment;
import ru.familyproject.ryabov.masteritsa.entity.Product;
import ru.familyproject.ryabov.masteritsa.entity.ProductType;
import ru.familyproject.ryabov.masteritsa.entity.User;
import ru.familyproject.ryabov.masteritsa.service.ProductService;
import ru.familyproject.ryabov.masteritsa.service.ProductTypeService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebMvcTest
class ProductControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    ProductService productService;

    @MockBean
    ProductTypeService productTypeService;

    private List<ProductType> types;

    private List<Comment> comments;

    private Product product;

    @BeforeEach
    void init() {
        this.types = new ArrayList<>();
        types.add(new ProductType(1L, "Корона 1"));

        this.comments = new ArrayList<>();
        comments.add(new Comment(1L, "Тестовый комментарий", "2022-03-12", new User(), product));

        product = new Product(1L,
                "Корона",
                20.0D,
                "image.png",
                "Описание",
                "Зеленый",
                types.get(0),
                comments);
        Mockito.when(productService.getById(1L)).thenReturn(product);
    }

    @Test
    void getAllProductTypesPageWhenCallsMethod_getAllProductsById_InMethodGetProduct() throws Exception {
        Mockito.when(productTypeService.getAll()).thenReturn(types);
        this.mockMvc.perform(get("/product/1").with(user("u")));
        verify(productTypeService, times(1)).getAll();
    }

    @Test
    void getAllCommentsWhereIdEqualsOne_WhenCallsMethod_getAllCommentsById_WithParameterEqualsOne_InMethodGetProduct() throws Exception {
        Mockito.when(productService.getAllCommentsById(1L)).thenReturn(comments);
        this.mockMvc.perform(get("/product/1").with(user("u")));
        Mockito.verify(productService, Mockito.times(1)).getAllCommentsById(1L);
    }

    @Test
    void getProductWhereIdEqualsOne_WhenCallsMethod_getById_WithParameterEqualsOne_InMethodGetProduct() throws Exception {
        this.mockMvc.perform(get("/product/1").with(user("u")));
        Mockito.verify(productService, Mockito.times(1)).getById(1L);
    }
}