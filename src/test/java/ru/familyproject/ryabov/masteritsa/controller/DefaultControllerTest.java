package ru.familyproject.ryabov.masteritsa.controller;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import ru.familyproject.ryabov.masteritsa.service.ProductTypeService;
import ru.familyproject.ryabov.masteritsa.service.UserService;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
class DefaultControllerTest {
    @Autowired
    DefaultController defaultController;
    @MockBean
    UserService userService;
    @MockBean
    ProductTypeService productTypeService;
    @MockBean
    Model model;
    @MockBean
    UserDetails userDetails;

    @Test
    void getAllProductTypesOnMainPageWhenCallsMethod_index(){
        defaultController.index(model, userDetails);
        verify(productTypeService, times(1)).getAll();
    }
    @Test
    void shouldLoadUserByUsername_WhenUserIsNotNull(){
        defaultController.index(model, userDetails);
        verify(userService, times(1))
                .loadUserByUsername(userDetails.getUsername());
    }
    @Test
    void should_not_LoadUserByUsername_WhenUserIsNull(){
        defaultController.index(model, null);
        Mockito
                .verify(userService, Mockito.times(0))
                .loadUserByUsername(anyString());
    }
}