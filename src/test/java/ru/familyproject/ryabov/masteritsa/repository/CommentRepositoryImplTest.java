package ru.familyproject.ryabov.masteritsa.repository;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
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
    void successfullyGetCommentsWithProductIdEqualsOne_WhenCallsMethod_getAllCommentsById_WithParameterEqualsOne(){
        Assertions.assertNotNull(commentRepository.getAllCommentsById(1L));
    }
    @Test
    void getErrorMessageErrorMappingQueryAndThrowIllegalArgumentExceptionWhenSessionCreatedQuery(){
        SessionFactory sessionFactoryEmpty = new Configuration().configure().buildSessionFactory();
        commentRepository = new CommentRepositoryImpl(sessionFactoryEmpty);
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class,
                () -> commentRepository.getAllCommentsById(1L));
        Assertions.assertEquals("Error mapping query when created query in method getAllCommentsById from CommentRepositoryImpl", exception.getMessage());
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