package ru.familyproject.ryabov.masteritsa.repository;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import ru.familyproject.ryabov.masteritsa.entity.Comment;

import static org.mockito.Mockito.*;

@SpringJUnitConfig
@SpringBootTest
class CommentRepositoryImplTest {
    @Autowired
    CommentRepositoryImpl commentRepository;
    @MockBean
    private SessionFactory sessionFactory;
    @MockBean
    private Session session;
    @MockBean
    private Query<Comment> result;
    @MockBean
    private Comment comment;
    @MockBean
    private Transaction transaction;

    @Test
    void getSessionWhenSessionFactoryCalls_openSession_InMethodGetAllCommentsById() {
        successfulValidation();
        verify(sessionFactory, times(1)).openSession();
    }

    @Test
    void getQueryResultWhenCalls_createQuery_InMethodGetAllCommentsById() {
        successfulValidation();
        verify(session, times(1))
                .createQuery("SELECT c FROM comment c WHERE c.product.id= :id", Comment.class);
    }

    @Test
    void methodSetParameterCallsOneTime_InMethodGetAllCommentsById() {
        successfulValidation();
        verify(result, times(1))
                .setParameter("id", 1L);
    }

    @Test
    void getListWhenCallsMethodList_InMethodGetAllCommentsById() {
        successfulValidation();
        verify(result, times(1)).list();
    }

    @Test
    void getErrorMessage_ErrorWhenOpenedSession_AndThrowHibernateException_WhenSessionFactoryOpenSession_InMethodGetAllCommentsById() {
        when(sessionFactory.openSession()).thenThrow(HibernateException.class);
        HibernateException exception = Assertions.assertThrows(HibernateException.class,
                () -> commentRepository.getAllCommentsById(1L));
        Assertions.
                assertEquals("Error when opened session on sessionFactory in method getAllCommentsById from CommentRepositoryImpl",
                        exception.getMessage());
    }

    void successfulValidation() {
        when(sessionFactory.openSession()).thenReturn(session);
        when(session
                .createQuery("SELECT c FROM comment c WHERE c.product.id= :id", Comment.class))
                .thenReturn(result);
        commentRepository.getAllCommentsById(1L);
    }

    //-------------------------------------------Тесты для метода saveComment--------------------------------

    @Test
    void getSessionWhenSessionFactoryCalls_openSession_InMethod_saveComment() {
        successfulMethod_saveComment();
        verify(sessionFactory, times(1)).openSession();
    }

    @Test
    void getTransactionWhenCalls_beginTransaction_InMethod_saveComment() {
        successfulMethod_saveComment();
        verify(session, times(1)).beginTransaction();
    }

    @Test
    void methodSaveCallsOneTime_InMethod_saveComment() {
        successfulMethod_saveComment();
        verify(session, times(1))
                .save(comment);
    }

    @Test
    void methodCommitCallsOneTime_InMethod_saveComment() {
        successfulMethod_saveComment();
        verify(transaction, times(1)).commit();
    }

    @Test
    void getErrorMessage_ErrorWhenOpenedSession_AndThrowHibernateException_WhenSessionFactoryOpenSessionInMethod_saveComment() {
        when(sessionFactory.openSession()).thenThrow(HibernateException.class);
        HibernateException exception = Assertions.assertThrows(HibernateException.class,
                () -> commentRepository.saveComment(comment));
        Assertions.assertEquals(exception.getMessage(), "Error when opened session on sessionFactory in method saveComment from CommentRepositoryImpl");
    }

    void successfulMethod_saveComment() {
        Mockito.when(sessionFactory.openSession()).thenReturn(session);
        Mockito.when(session.beginTransaction()).thenReturn(transaction);
        commentRepository.saveComment(comment);
    }
}