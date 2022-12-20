package ru.familyproject.ryabov.masteritsa.repository;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class ProductTypeRepositoryImplTest {
    @Autowired
    ProductTypeRepositoryImpl productTypeRepository;

    private SessionFactory sessionFactory;

    @BeforeEach
    void setUp(){
        sessionFactory = mock(SessionFactory.class);
    }

    @Test
    void successfullyGetProductTypes_WhenCallsMethod_getAll(){
        Assertions.assertNotNull(productTypeRepository.getAll());
    }

    @Test
    void getErrorMessage_ErrorWhenOpenedSession_AndThrowHibernateException_WhenSessionFactoryOpenSessionInMethodGetAll(){
        productTypeRepository = new ProductTypeRepositoryImpl(sessionFactory);
        when(sessionFactory.openSession()).thenThrow(HibernateException.class);
        HibernateException exception = Assertions.assertThrows(HibernateException.class,
                () -> productTypeRepository.getAll());
        Assertions.assertEquals("Error when opened session on sessionFactory in method getAll from ProductTypeRepositoryImpl",
                exception.getMessage());

    }
}