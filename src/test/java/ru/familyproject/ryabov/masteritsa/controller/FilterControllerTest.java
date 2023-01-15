package ru.familyproject.ryabov.masteritsa.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import ru.familyproject.ryabov.masteritsa.entity.User;
import ru.familyproject.ryabov.masteritsa.service.ProductService;
import ru.familyproject.ryabov.masteritsa.service.ProductTypeService;
import ru.familyproject.ryabov.masteritsa.service.UserService;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
class FilterControllerTest {
    @Autowired
    FilterController filterController;
    @MockBean
    UserService userService;
    @MockBean
    ProductService productService;
    @MockBean
    ProductTypeService productTypeService;
    @MockBean
    Model model;
    @MockBean
    UserDetails userDetails;
    @MockBean
    User user;
    @Test
    void getAllProducts_WhenCallsMethod_getAll_InMethodGetAllProducts(){
        filterController.getAllProducts(model, userDetails);
        verify(productService, times(1)).getAll();
    }

    @Test
    void getAllProductTypes_WhenCallsMethod_getAll_InMethodGetAllProducts(){
        filterController.getAllProducts(model, userDetails);
        verify(productTypeService, times(1)).getAll();
    }

    @Test
    void getAllProductTypes_WhenCallsMethod_getAll_InMethodGetAllProductsById(){
        filterController.getAllProductsById(model, anyLong(), userDetails);
        verify(productTypeService, times(1)).getAll();
    }

    @ParameterizedTest
    @CsvSource({
            "1",
            "2",
            "3"
    })
    void getAllProductsWhereIdEqualsOne_WhenCallsMethod_getAllProductsById_WithParameterEqualsOne_InMethodGetAllProductsById(Long id) {
        filterController.getAllProductsById(model, id, userDetails);
        verify(productService, times(1)).getAllById(id);
    }
    @Test
    void shouldLoadUserByUsername_WhenUserIsNotNull_InMethodGetAllProducts(){
        filterController.getAllProducts(model, userDetails);
        Mockito
                .when(userService
                        .loadUserByUsername(userDetails.getUsername()))
                .thenReturn(user);
        Mockito
                .verify(userService, Mockito.times(1))
                .loadUserByUsername(userDetails.getUsername());
    }
    @Test
    void should_not_LoadUserByUsername_WhenUserIsNull_InMethodGetAllProducts(){
        filterController.getAllProducts(model,null);
        Mockito
                .verify(userService, Mockito.times(0))
                .loadUserByUsername(anyString());
    }

    @ParameterizedTest
    @CsvSource({
            "1",
            "2",
            "3"
    })
    void shouldLoadUserByUsername_WhenUserIsNotNull_InMethodGetAllProductsById(Long id){
        filterController.getAllProductsById(model, id, userDetails);
        Mockito
                .when(userService
                        .loadUserByUsername(userDetails.getUsername()))
                .thenReturn(user);
        Mockito
                .verify(userService, Mockito.times(1))
                .loadUserByUsername(userDetails.getUsername());
    }

    @ParameterizedTest
    @CsvSource({
            "1",
            "2",
            "3"
    })
    void should_not_LoadUserByUsername_WhenUserIsNull_InMethodGetAllProductsById(Long id){
        filterController.getAllProductsById(model, id,null);
        Mockito
                .verify(userService, Mockito.times(0))
                .loadUserByUsername(anyString());
    }
}