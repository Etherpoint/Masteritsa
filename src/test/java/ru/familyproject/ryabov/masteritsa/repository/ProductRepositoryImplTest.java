package ru.familyproject.ryabov.masteritsa.repository;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.Mockito.when;

@SpringBootTest
class ProductRepositoryImplTest {
    @Autowired
    ProductRepositoryImpl productRepository;
    @MockBean
    private SessionFactory sessionFactory;

    @Test
    void getErrorMessage_ErrorWhenOpenedSession_AndThrowHibernateException_WhenSessionFactoryOpenSessionInMethodGetAll() {
        when(sessionFactory.openSession()).thenThrow(HibernateException.class);
        HibernateException exception = Assertions.assertThrows(HibernateException.class,
                () -> productRepository.getAll());
        Assertions
                .assertEquals("Error when opened session on sessionFactory in method getAll from ProductRepositoryImpl",
                        exception.getMessage());
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

    @Test
    void getErrorMessage_ErrorWhenOpenedSession_AndThrowHibernateException_WhenSessionFactoryOpenSessionInMethodGetById() {
        when(sessionFactory.openSession()).thenThrow(HibernateException.class);
        HibernateException exception = Assertions.assertThrows(HibernateException.class,
                () -> productRepository.getById(1L));
        Assertions
                .assertEquals("Error when opened session on sessionFactory in method getById from ProductRepositoryImpl",
                        exception.getMessage());
    }
}