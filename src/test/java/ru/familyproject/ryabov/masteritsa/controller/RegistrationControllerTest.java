package ru.familyproject.ryabov.masteritsa.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.AdditionalMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.multipart.MultipartFile;
import ru.familyproject.ryabov.masteritsa.entity.User;
import ru.familyproject.ryabov.masteritsa.service.UserService;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.CopyOption;
import java.nio.file.Path;

import static org.mockito.Mockito.*;

@SpringBootTest
class RegistrationControllerTest {
    @Autowired
    RegistrationController registrationController;
    @MockBean
    User user;
    @MockBean
    MultipartFile file;
    @MockBean
    UserService userService;
    @MockBean
    InputStream inputStream;
    @MockBean
    Path path;
    @MockBean
    CopyOption option;

    @Test
    void getSuccessfullyMethod_registration() {
        Assertions.assertEquals(registrationController.registration(), "registration");
    }

    @ParameterizedTest
    @CsvSource({
            "Наталья",
            "Виктория",
            "Елена"
    })
    void callsMethod_loadUserByUsername_WhenCallsMethod_addUser(String username) {
        when(user.getUsername()).thenReturn(username);
        when(userService.loadUserByUsername(username))
                .thenReturn(user);
        registrationController.addUser(user, file);
        verify(userService, times(1)).loadUserByUsername(username);
    }

    @ParameterizedTest
    @CsvSource({
            "Наталья",
            "Виктория",
            "Елена"
    })
    void shouldSaveUserWhenUserIsNull_InMethod_addUser(String username){
        when(user.getUsername())
                .thenReturn(username);
        when(userService.loadUserByUsername(username))
                .thenReturn(null);
        when(file.isEmpty()).thenReturn(true);
        //when(user == null).thenReturn(true);
        registrationController.addUser(user, file);
        verify(userService, times(1)).save(user);
    }

    @ParameterizedTest
    @CsvSource({
            "Наталья",
            "Виктория",
            "Елена"
    })
    void shouldNotSaveUserWhenUserIsNotNull_InMethod_addUser(String username) {
        when(user.getUsername()).thenReturn(username);
        when(userService.loadUserByUsername(username))
                .thenReturn(user);
        registrationController.addUser(user, file);
        verify(userService, times(0)).save(user);
    }

    @ParameterizedTest
    @CsvSource({
            "Наталья",
            "Виктория",
            "Елена"
    })
    void shouldSetImage_WhenUserIsNull_And_FileIsNotEmpty(String username) throws IOException {
        when(user.getUsername())
                .thenReturn(username);
        when(userService.loadUserByUsername(username))
                .thenReturn(null);
        when(file.isEmpty())
                .thenReturn(false);
        when(file.getInputStream())
                .thenReturn(inputStream);
        registrationController.addUser(user, file);
        verify(user, times(1))
                .setImage(AdditionalMatchers.not(eq("avatar.png")));
    }

    @ParameterizedTest
    @CsvSource({
            "Наталья",
            "Виктория",
            "Елена"
    })
    void shouldCallTheMethod_getOriginalFilename_WhenUserIsNull_And_FileIsNotEmpty(String username) throws IOException {
        when(user.getUsername())
                .thenReturn(username);
        when(userService.loadUserByUsername(username))
                .thenReturn(null);
        when(file.isEmpty())
                .thenReturn(false);
        when(file.getInputStream())
                .thenReturn(inputStream);
        registrationController.addUser(user, file);
        verify(file, times(1)).getOriginalFilename();
    }

    @ParameterizedTest
    @CsvSource({
            "Наталья",
            "Виктория",
            "Елена"
    })
    void shouldCatchIOExceptionWhenCrashFiles_copy(String username) throws IOException {
        when(user.getUsername())
                .thenReturn(username);
        when(userService.loadUserByUsername(username))
                .thenReturn(null);
        when(file.isEmpty())
                .thenReturn(false);
        when(file.getInputStream())
                .thenThrow(IOException.class);
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class,
                () -> registrationController.addUser(user, file));
        Assertions.assertEquals(exception.getMessage(), "ОШИБКА ПРИ СОХРАНЕНИИ КАРТИНКИ");
    }

    @ParameterizedTest
    @CsvSource({
            "Наталья",
            "Виктория",
            "Елена"
    })
    void shouldSetImageTo_avatarDotPng_WhenUserIsNull_And_FileIsEmpty(String username){
        when(user.getUsername())
                .thenReturn(username);
        when(userService.loadUserByUsername(username))
                .thenReturn(null);
        when(file.isEmpty())
                .thenReturn(true);
        registrationController.addUser(user, file);
        verify(user, times(1)).setImage("avatar.png");
    }
}