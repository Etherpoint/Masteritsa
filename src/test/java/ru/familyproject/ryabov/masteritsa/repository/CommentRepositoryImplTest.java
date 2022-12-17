package ru.familyproject.ryabov.masteritsa.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.mockito.Mockito.anyLong;

@SpringJUnitConfig
@SpringBootTest
class CommentRepositoryImplTest {
    @Autowired
    CommentRepositoryImpl commentRepository;
    @Test
    void successfullyGetCommentsWithProductIdEqualsOne_WhenCallsMethod_getAllCommentsById_WithParameterEqualsOne(){
        Assertions.assertNotNull(commentRepository.getAllCommentsById(anyLong()));
    }
    @Test
    void getMessageErrorWhileConfiguringSessionFactory(){
    }
}