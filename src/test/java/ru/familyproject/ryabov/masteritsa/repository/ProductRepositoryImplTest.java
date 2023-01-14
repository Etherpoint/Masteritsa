package ru.familyproject.ryabov.masteritsa.repository;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class ProductRepositoryImplTest {
    @Autowired
    ProductRepositoryImpl productRepository;

    private SessionFactory sessionFactory;

    @BeforeEach
    void setUp() {
        sessionFactory = mock(SessionFactory.class);
    }

    @Test
    void successfullyGetProducts_WhenCallsMethod_getAll() {
        Assertions.assertNotNull(productRepository.getAll());
    }

    @Test
    void getErrorMessage_ErrorWhenOpenedSession_AndThrowHibernateException_WhenSessionFactoryOpenSessionInMethodGetAll() {
        productRepository = new ProductRepositoryImpl(sessionFactory);
        when(sessionFactory.openSession()).thenThrow(HibernateException.class);
        HibernateException exception = Assertions.assertThrows(HibernateException.class,
                () -> productRepository.getAll());
        Assertions
                .assertEquals("Error when opened session on sessionFactory in method getAll from ProductRepositoryImpl",
                        exception.getMessage());
    }

    @Test
    void successfullyGetProducts_WhenCallsMethodGetAllById() {
        Assertions.assertNotNull(productRepository.getAllById(1L));
    }

    @Test
    void getErrorMessage_ErrorWhenOpenedSession_AndThrowHibernateException_WhenSessionFactoryOpenSessionInMethodGetAllById() {
        productRepository = new ProductRepositoryImpl(sessionFactory);
        when(sessionFactory.openSession()).thenThrow(HibernateException.class);
        HibernateException exception = Assertions.assertThrows(HibernateException.class,
                () -> productRepository.getAllById(1L));
        Assertions
                .assertEquals("Error when opened session on sessionFactory in method getAllById from ProductRepositoryImpl",
                        exception.getMessage());
    }

    @Test
    void successfullyGetProduct_WhenCallsMethodGetById() {
        Assertions.assertNotNull(productRepository.getById(1L));
    }

    @Test
    void getErrorMessage_ErrorWhenOpenedSession_AndThrowHibernateException_WhenSessionFactoryOpenSessionInMethodGetById() {
        productRepository = new ProductRepositoryImpl(sessionFactory);
        when(sessionFactory.openSession()).thenThrow(HibernateException.class);
        HibernateException exception = Assertions.assertThrows(HibernateException.class,
                () -> productRepository.getById(1L));
        Assertions
                .assertEquals("Error when opened session on sessionFactory in method getById from ProductRepositoryImpl",
                        exception.getMessage());
    }
}