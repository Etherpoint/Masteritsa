package ru.familyproject.ryabov.masteritsa.repository;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.familyproject.ryabov.masteritsa.entity.Product;

import static org.mockito.Mockito.*;

@SpringBootTest
class ProductRepositoryImplTest {
    @Autowired
    ProductRepositoryImpl productRepository;
    @MockBean
    private SessionFactory sessionFactory;
    @MockBean
    private Session session;
    @MockBean
    private Query<Product> result;

    //
    // Tests for method getAll
    //
    @Test
    void getSessionWhenSessionFactoryCalls_openSession_InMethodGetAll() {
        successfulMethod_getAll();
        verify(sessionFactory, times(1)).openSession();
    }

    @Test
    void getQueryResultWhenCalls_createQuery_InMethodGetAll() {
        successfulMethod_getAll();
        verify(session, times(1))
                .createQuery("SELECT p FROM product p", Product.class);
    }

    @Test
    void getListWhenCallsMethodList_InMethodGetAll() {
        successfulMethod_getAll();
        verify(result, times(1)).list();
    }

    @Test
    void getErrorMessage_ErrorWhenOpenedSession_AndThrowHibernateException_WhenSessionFactoryOpenSessionInMethodGetAll() {
        when(sessionFactory.openSession()).thenThrow(HibernateException.class);
        HibernateException exception = Assertions.assertThrows(HibernateException.class,
                () -> productRepository.getAll());
        Assertions
                .assertEquals("Error when opened session on sessionFactory in method getAll from ProductRepositoryImpl",
                        exception.getMessage());
    }
    void successfulMethod_getAll(){
        when(sessionFactory.openSession()).thenReturn(session);
        when(session
                .createQuery("SELECT p FROM product p", Product.class))
                .thenReturn(result);
        productRepository.getAll();
    }

    //
    // Tests for method getAllById
    //
    @Test
    void getSessionWhenSessionFactoryCalls_openSession_InMethodGetAllById() {
        successfulMethod_getAllById();
        verify(sessionFactory, times(1)).openSession();
    }

    @Test
    void getQueryResultWhenCalls_createQuery_InMethodGetAllById() {
        successfulMethod_getAllById();
        verify(session, times(1))
                .createQuery("SELECT p FROM product p WHERE p.productType.id = :id", Product.class);
    }

    @Test
    void methodSetParameterCallsOneTime_InMethodGetAllById() {
        successfulMethod_getAllById();
        verify(result, times(1))
                .setParameter("id", 1L);
    }

    @Test
    void getListWhenCallsMethodList_InMethodGetAllById() {
        successfulMethod_getAllById();
        verify(result, times(1)).list();
    }
    @Test
    void getErrorMessage_ErrorWhenOpenedSession_AndThrowHibernateException_WhenSessionFactoryOpenSessionInMethodGetAllById() {
        when(sessionFactory.openSession()).thenThrow(HibernateException.class);
        HibernateException exception = Assertions.assertThrows(HibernateException.class,
                () -> productRepository.getAllById(1L));
        Assertions
                .assertEquals("Error when opened session on sessionFactory in method getAllById from ProductRepositoryImpl",
                        exception.getMessage());
    }

    void successfulMethod_getAllById(){
        when(sessionFactory.openSession()).thenReturn(session);
        when(session
                .createQuery("SELECT p FROM product p WHERE p.productType.id = :id", Product.class))
                .thenReturn(result);
        productRepository.getAllById(1L);
    }

    //
    // Tests for method getById
    //
    @Test
    void getSessionWhenSessionFactoryCalls_openSession_InMethodGetById() {
        successfulMethod_getById();
        verify(sessionFactory, times(1)).openSession();
    }

    @Test
    void getQueryResultWhenCalls_createQuery_InMethodGetById() {
        successfulMethod_getById();
        verify(session, times(1))
                .createQuery("SELECT p FROM product p WHERE p.id = :id", Product.class);
    }

    @Test
    void methodSetParameterCallsOneTime_InMethodGetById() {
        successfulMethod_getById();
        verify(result, times(1))
                .setParameter("id", 1L);
    }

    @Test
    void getUniqueResultWhenCallsMethodList_InMethodGetById() {
        successfulMethod_getById();
        verify(result, times(1)).uniqueResult();
    }

    @Test
    void getErrorMessage_ErrorWhenOpenedSession_AndThrowHibernateException_WhenSessionFactoryOpenSessionInMethodGetById() {
        when(sessionFactory.openSession()).thenThrow(HibernateException.class);
        HibernateException exception = Assertions.assertThrows(HibernateException.class,
                () -> productRepository.getById(1L));
        Assertions
                .assertEquals("Error when opened session on sessionFactory in method getById from ProductRepositoryImpl",
                        exception.getMessage());
    }

    void successfulMethod_getById(){
        when(sessionFactory.openSession()).thenReturn(session);
        when(session
                .createQuery("SELECT p FROM product p WHERE p.id = :id", Product.class))
                .thenReturn(result);
        productRepository.getById(1L);
    }
}