package ru.familyproject.ryabov.masteritsa.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import ru.familyproject.ryabov.masteritsa.entity.User;
import ru.familyproject.ryabov.masteritsa.service.CommentService;
import ru.familyproject.ryabov.masteritsa.service.ProductService;
import ru.familyproject.ryabov.masteritsa.service.ProductTypeService;
import ru.familyproject.ryabov.masteritsa.service.UserService;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@WebMvcTest
class ProductControllerTest {
    @Autowired
    ProductController productController;
    @MockBean
    ProductService productService;
    @MockBean
    ProductTypeService productTypeService;
    @MockBean
    UserService userService;
    @MockBean
    UserDetails userDetails;
    @MockBean
    CommentService commentService;
    @MockBean
    Model model;
    @MockBean
    User user;

    @ParameterizedTest
    @CsvSource({
            "1",
            "2",
            "3",
    })
    void getAllProductTypesPageWhenCallsMethod_getAllProductsById_InMethodGetProduct(Long id) {
        productController.getProduct(model, id, userDetails);
        verify(productTypeService, times(1)).getAll();
    }

    @ParameterizedTest
    @CsvSource({
            "1",
            "2",
            "3",
    })
    void getAllCommentsWhereIdEqualsOne_WhenCallsMethod_getAllCommentsById_WithParameterEqualsOne_InMethodGetProduct(Long id) {
        productController.getProduct(model, id, userDetails);
        Mockito.verify(productService, Mockito.times(1)).getAllCommentsById(id);
    }

    @ParameterizedTest
    @CsvSource({
            "1",
            "2",
            "3",
    })
    void getProductWhereIdEqualsOne_WhenCallsMethod_getById_WithParameterEqualsOne_InMethodGetProduct(Long id) {
        productController.getProduct(model, id, userDetails);
        Mockito.verify(productService, Mockito.times(1)).getById(id);
    }

    @ParameterizedTest
    @CsvSource({
            "1",
            "2",
            "3",
    })
    void shouldLoadUserByUsername_WhenUserIsNotNull(Long id){
        productController.getProduct(model, id, userDetails);
        Mockito
                .when(userService
                        .loadUserByUsername(userDetails.getUsername()))
                .thenReturn(user);
        Mockito
                .verify(userService, Mockito.times(1))
                .loadUserByUsername(userDetails.getUsername());
    }

    @Test
    void should_not_LoadUserByUsername_WhenUserIsNull(){
        productController.getProduct(model, anyLong(),null);
        Mockito
                .verify(userService, Mockito.times(0))
                .loadUserByUsername(anyString());
    }
}