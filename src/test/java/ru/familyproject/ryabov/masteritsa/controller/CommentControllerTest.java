package ru.familyproject.ryabov.masteritsa.controller;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;
import ru.familyproject.ryabov.masteritsa.entity.Comment;
import ru.familyproject.ryabov.masteritsa.entity.User;
import ru.familyproject.ryabov.masteritsa.service.CommentService;
import ru.familyproject.ryabov.masteritsa.service.ProductService;
import ru.familyproject.ryabov.masteritsa.service.UserService;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@SpringBootTest
class CommentControllerTest {

    @Autowired
    CommentController commentController;
    @MockBean
    UserService userService;
    @MockBean
    ProductService productService;
    @MockBean
    CommentService commentService;
    @MockBean
    UserDetails userDetails;
    @MockBean
    Comment mockComment;
    @MockBean
    User user;

    //---------------------------------------Тест кейсы для метода saveComment---------------------------


    @ParameterizedTest
    @CsvSource({
            "1",
            "2",
            "3"
    })
    void shouldLoadUserByUsername_WhenUserIsNotNull_inMethod_saveComment(Long id) {
        commentController.saveComment(userDetails, id, mockComment);
        when(userService
                .loadUserByUsername(userDetails.getUsername()))
                .thenReturn(user);
        verify(userService, times(1))
                .loadUserByUsername(userDetails.getUsername());
    }

    @ParameterizedTest
    @CsvSource({
            "1",
            "2",
            "3"
    })
    void should_not_LoadUserByUsername_WhenUserIsNull_inMethod_saveComment(Long id) {
        commentController.saveComment(userDetails, id, mockComment);
        verify(userService, times(0))
                .loadUserByUsername(anyString());
    }

    @ParameterizedTest
    @CsvSource({
            "1",
            "2",
            "3"
    })
    void shouldSetProductAndFieldShouldNotBeEqualsNull_inMethod_saveComment(Long id) {
        commentController.saveComment(userDetails, id, mockComment);
        verify(mockComment, times(1))
                .setProduct(productService.getById(id));
    }

    @ParameterizedTest
    @CsvSource({
            "1",
            "2",
            "3"
    })
    void doesNotCallMethod_loadUserByUsernameIfUserEqualsNull_inMethod_saveComment(Long id) {
        commentController.saveComment(null, id, mockComment);
        verify(userService, times(0))
                .loadUserByUsername(userDetails.getUsername());
        verify(mockComment, times(1))
                .setUser(null);
    }

    @ParameterizedTest
    @CsvSource(delimiterString = ", ",
            value = {
                    "1",
                    "2",
                    "3"
            })
    void shouldCallMethod_loadUserByUsernameIfUserNotEqualsNull_inMethod_saveComment(Long id) {
        commentController.saveComment(userDetails, id, mockComment);
        verify(userService, times(1))
                .loadUserByUsername(userDetails.getUsername());
        verify(mockComment, times(1))
                .setUser(userService.loadUserByUsername(userDetails.getUsername()));
    }

    @ParameterizedTest
    @CsvSource({
            "1",
            "2",
            "3"
    })
    void shouldCallMethod_saveCommentOnTheCommentService_inMethod_saveComment(Long id) {
        commentController.saveComment(userDetails, id, mockComment);
        verify(commentService, times(1))
                .saveComment(mockComment);
    }

}