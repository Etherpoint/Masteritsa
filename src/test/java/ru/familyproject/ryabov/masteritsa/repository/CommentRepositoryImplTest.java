package ru.familyproject.ryabov.masteritsa.repository;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringJUnitConfig
@SpringBootTest
class CommentRepositoryImplTest {
    @Autowired
    CommentRepositoryImpl commentRepository;
    private static SessionFactory sessionFactory;

    @BeforeAll
    static void setUp(){
        sessionFactory = mock(SessionFactory.class);
    }

    @Test
    void successfullyGetComments_WhenCallsMethod_getAllCommentsById(){
        Assertions.assertNotNull(commentRepository.getAllCommentsById(1L));
    }
    @Test
    void getErrorMessageErrorWhenOpenedSessionAndThrowHibernateExceptionWhenSessionFactoryOpenSession(){
        commentRepository = new CommentRepositoryImpl(sessionFactory);
        when(sessionFactory.openSession()).thenThrow(HibernateException.class);
        HibernateException exception = Assertions.assertThrows(HibernateException.class,
                () -> commentRepository.getAllCommentsById(1L));
        Assertions.assertEquals("Error when opened session on sessionFactory in method getAllCommentsById from CommentRepositoryImpl", exception.getMessage());
    }
}