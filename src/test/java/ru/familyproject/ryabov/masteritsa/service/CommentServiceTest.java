package ru.familyproject.ryabov.masteritsa.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.familyproject.ryabov.masteritsa.entity.Comment;
import ru.familyproject.ryabov.masteritsa.repository.CommentRepositoryImpl;

@SpringBootTest
class CommentServiceTest {
    @Autowired
    private CommentService commentService;
    @MockBean
    CommentRepositoryImpl commentRepository;
    Comment comment;
    @BeforeEach
    void createComment(){
        comment = new Comment();
    }

    @Test
    void shouldSetDateOfCreate_inMethod_saveComment(){
        commentService.saveComment(comment);
        Assertions.assertNotNull(comment.getDateOfCreate());
    }

    @Test
    void shouldSaveComment_inMethod_saveComment(){
        commentService.saveComment(comment);
        Mockito
                .verify(commentRepository, Mockito.times(1))
                .saveComment(comment);
    }
}