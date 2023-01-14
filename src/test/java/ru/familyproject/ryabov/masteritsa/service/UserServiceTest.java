package ru.familyproject.ryabov.masteritsa.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.familyproject.ryabov.masteritsa.entity.User;
import ru.familyproject.ryabov.masteritsa.repository.RoleRepositoryImpl;
import ru.familyproject.ryabov.masteritsa.repository.UserRepositoryImpl;

@SpringBootTest
class UserServiceTest {
    @Autowired
    UserService userService;
    @MockBean
    UserRepositoryImpl userRepository;
    @MockBean
    RoleRepositoryImpl roleRepository;
    @MockBean
    PasswordEncoder encoder;

    private User user;

    @BeforeEach
    public void createUser() {
        user = new User();
        user.setPassword("пароль");
    }

    @Test
    void shouldSetUserPassword_WhenCallsMethod_save() {
        userService.save(user);
        Assertions.assertNotEquals(encoder.encode("пароль"), user.getPassword());
    }

    @Test
    void shouldSetEnabled_WhenCallsMethod_save() {
        userService.save(user);
        Assertions.assertTrue(user.isEnabled());
    }

    @Test
    void shouldSetRoles_WhenCallsMethod_save() {
        userService.save(user);
        Assertions.assertNotNull(user.getRoles());
    }

    @Test
    void shouldSaveUser_WhenCallsMethod_save() {
        userService.save(user);
        Mockito.verify(userRepository, Mockito.times(1)).save(user);
    }

    @Test
    void shouldDeleteUser_WhenCallsMethod_delete(){
        userService.delete(user);
        Mockito.verify(userRepository, Mockito.times(1)).delete(user);
    }
    @Test
    void shouldFindByUsername_WhenCallsMethod_loadUserByUsername(){
        userService.loadUserByUsername(Mockito.anyString());
        Mockito.verify(userRepository, Mockito.times(1)).findByUsername(Mockito.anyString());
    }
}