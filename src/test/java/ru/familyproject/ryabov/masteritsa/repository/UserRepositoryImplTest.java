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
import ru.familyproject.ryabov.masteritsa.entity.User;

import static org.mockito.Mockito.*;

@SpringBootTest
class UserRepositoryImplTest {
    @Autowired
    UserRepository userRepository;
    @MockBean
    private SessionFactory sessionFactory;
    @MockBean
    private Session session;
    @MockBean
    private Transaction transaction;
    @MockBean
    private Query<User> query;
    @MockBean
    private User user;

    private final String username = "Наталья";

    //
    //////////////////////////////////////Tests for method save
    //
    @Test
    void getSessionWhenSessionFactoryCalls_openSession_InMethodSave() {
        successfulMethod_save();
        verify(sessionFactory, times(1)).openSession();
    }

    @Test
    void getTransactionWhenCalls_beginTransaction_InMethodSave() {
        successfulMethod_save();
        verify(session, times(1)).beginTransaction();
    }

    @Test
    void methodSaveCallsOneTime_InMethodSave() {
        successfulMethod_save();
        verify(session, times(1))
                .save(user);
    }

    @Test
    void methodCommitCallsOneTime_InMethodSave() {
        successfulMethod_save();
        verify(transaction, times(1)).commit();
    }

    @Test
    void getErrorMessage_ErrorWhenOpenedSession_AndThrowHibernateException_WhenSessionFactoryOpenSessionInMethod_save() {
        when(sessionFactory.openSession()).thenThrow(HibernateException.class);
        HibernateException exception = Assertions.assertThrows(HibernateException.class,
                () -> userRepository.save(user));
        Assertions.assertEquals(exception.getMessage(), "Error when opened session on sessionFactory in method save from UserRepositoryImpl");
    }

    void successfulMethod_save() {
        Mockito.when(sessionFactory.openSession()).thenReturn(session);
        Mockito.when(session.beginTransaction()).thenReturn(transaction);
        userRepository.save(user);
    }

    //
    //////////////////////////////////////Tests for method delete
    //
    @Test
    void getSessionWhenSessionFactoryCalls_openSession_InMethodDelete() {
        successfulMethod_delete();
        verify(sessionFactory, times(1)).openSession();
    }

    @Test
    void getTransactionWhenCalls_beginTransaction_InMethodDelete() {
        successfulMethod_delete();
        verify(session, times(1)).beginTransaction();
    }

    @Test
    void methodDeleteCallsOneTime_InMethodDelete() {
        successfulMethod_delete();
        verify(session, times(1))
                .delete(user);
    }

    @Test
    void methodCommitCallsOneTime_InMethodDelete() {
        successfulMethod_delete();
        verify(transaction, times(1)).commit();
    }

    @Test
    void getErrorMessage_ErrorWhenOpenedSession_AndThrowHibernateException_WhenSessionFactoryOpenSessionInMethod_delete() {
        when(sessionFactory.openSession()).thenThrow(HibernateException.class);
        HibernateException exception = Assertions.assertThrows(HibernateException.class,
                () -> userRepository.delete(user));
        Assertions.assertEquals(exception.getMessage(), "Error when opened session on sessionFactory in method delete from UserRepositoryImpl");
    }

    void successfulMethod_delete() {
        Mockito.when(sessionFactory.openSession()).thenReturn(session);
        Mockito.when(session.beginTransaction()).thenReturn(transaction);
        userRepository.delete(user);
    }

    //
    //////////////////////////////////////Tests for method findByUsername
    //
    @Test
    void getSessionWhenSessionFactoryCalls_openSession_InMethodGetById() {
        successfulMethod_findByUsername();
        verify(sessionFactory, times(1)).openSession();
    }

    @Test
    void getQueryResultWhenCalls_createQuery_InMethodGetById() {
        successfulMethod_findByUsername();
        verify(session, times(1))
                .createQuery("SELECT u FROM users u WHERE u.name = :username", User.class);
    }

    @Test
    void methodSetParameterCallsOneTime_InMethodGetById() {
        successfulMethod_findByUsername();
        verify(query, times(1))
                .setParameter("username", username);
    }

    @Test
    void getUniqueResultWhenCallsMethodList_InMethodGetById() {
        successfulMethod_findByUsername();
        verify(query, times(1)).uniqueResult();
    }

    @Test
    void getErrorMessage_ErrorWhenOpenedSession_AndThrowHibernateException_WhenSessionFactoryOpenSessionInMethodGetById() {
        when(sessionFactory.openSession()).thenThrow(HibernateException.class);
        HibernateException exception = Assertions.assertThrows(HibernateException.class,
                () -> userRepository.findByUsername(username));
        Assertions
                .assertEquals("Error when opened session on sessionFactory in method findByUsername from UserRepositoryImpl",
                        exception.getMessage());
    }

    void successfulMethod_findByUsername(){
        when(sessionFactory.openSession()).thenReturn(session);
        when(session
                .createQuery("SELECT u FROM users u WHERE u.name = :username", User.class))
                .thenReturn(query);
        userRepository.findByUsername(username);
    }
}