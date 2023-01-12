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

@SpringBootTest
class AboutInfoControllerTest {
    @Autowired
    AboutInfoController aboutInfoController;
    @MockBean
    UserService userService;
    @MockBean
    Model model;
    @MockBean
    UserDetails userDetails;
    @MockBean
    User user;

    @Test
    void shouldLoadUserByUsername_WhenUserIsNotNull(){
        aboutInfoController.getInfo(model, userDetails);
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
        aboutInfoController.getInfo(model, null);
        Mockito
                .verify(userService, Mockito.times(0))
                .loadUserByUsername(userDetails.getUsername());
    }
}