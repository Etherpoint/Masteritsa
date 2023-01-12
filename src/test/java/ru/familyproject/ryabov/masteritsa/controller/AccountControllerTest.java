package ru.familyproject.ryabov.masteritsa.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.ui.Model;
import ru.familyproject.ryabov.masteritsa.entity.User;
import ru.familyproject.ryabov.masteritsa.service.UserService;

@SpringBootTest
class AccountControllerTest {
    @Autowired
    AccountController accountController;
    @MockBean
    UserService userService;
    @MockBean
    User user;
    @MockBean
    Model model;

    @ParameterizedTest
    @CsvSource({
            "1",
            "2",
            "3"
    })
    void throwExceptionWhenUserIdNotEqualsParameterId(Long id) {
        Mockito.when(user.getId()).thenReturn(0L);
        Assertions
                .assertThrows(RuntimeException.class,
                        () -> accountController.getAccount(user, id, model));
    }

    @ParameterizedTest
    @CsvSource({
            "1",
            "2",
            "3"
    })
    void shouldLoadUserByUsername_WhenUserIsNotNull_InMethodGetAllProducts(Long id) {
        Mockito.when(user.getId()).thenReturn(id);
        Mockito
                .when(userService
                        .loadUserByUsername(user.getUsername()))
                .thenReturn(user);
        accountController.getAccount(user, id, model);
        Mockito
                .verify(userService, Mockito.times(1))
                .loadUserByUsername(user.getUsername());
    }
}