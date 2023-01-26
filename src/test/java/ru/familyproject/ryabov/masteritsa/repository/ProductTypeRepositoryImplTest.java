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
class ProductTypeRepositoryImplTest {
    @Autowired
    ProductTypeRepositoryImpl productTypeRepository;

    @MockBean
    private SessionFactory sessionFactory;

    @Test
    void getErrorMessage_ErrorWhenOpenedSession_AndThrowHibernateException_WhenSessionFactoryOpenSessionInMethodGetAll(){
        when(sessionFactory.openSession()).thenThrow(HibernateException.class);
        HibernateException exception = Assertions.assertThrows(HibernateException.class,
                () -> productTypeRepository.getAll());
        Assertions.assertEquals("Error when opened session on sessionFactory in method getAll from ProductTypeRepositoryImpl",
                exception.getMessage());

    }
}