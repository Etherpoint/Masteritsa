package ru.familyproject.ryabov.masteritsa.controller;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import ru.familyproject.ryabov.masteritsa.entity.User;
import ru.familyproject.ryabov.masteritsa.service.UserService;

import static org.mockito.ArgumentMatchers.anyString;

@SpringBootTest
class SaleControllerTest {
    @Autowired
    SaleController saleController;
    @MockBean
    UserService userService;
    @MockBean
    UserDetails userDetails;
    @MockBean
    Model model;
    @MockBean
    User user;
    @Test
    void shouldLoadUserByUsername_WhenUserIsNotNull(){
        saleController.getSales(model, userDetails);
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
        saleController.getSales(model, null);
        Mockito
                .verify(userService, Mockito.times(0))
                .loadUserByUsername(anyString());
    }
}