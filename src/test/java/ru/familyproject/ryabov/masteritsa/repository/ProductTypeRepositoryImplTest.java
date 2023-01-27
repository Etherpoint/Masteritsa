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
import ru.familyproject.ryabov.masteritsa.entity.ProductType;

import static org.mockito.Mockito.*;

@SpringBootTest
class ProductTypeRepositoryImplTest {
    @Autowired
    ProductTypeRepositoryImpl productTypeRepository;

    @MockBean
    private SessionFactory sessionFactory;

    @MockBean
    private Session session;

    @MockBean
    private Query<ProductType> result;

    @Test
    void getSessionWhenSessionFactoryCalls_openSession_InMethodGetAllById() {
        successfulMethod_getAll();
        verify(sessionFactory, times(1)).openSession();
    }

    @Test
    void getQueryResultWhenCalls_createQuery_InMethodGetAllById() {
        successfulMethod_getAll();
        verify(session, times(1))
                .createQuery("SELECT pt FROM product_type pt ORDER BY pt.id", ProductType.class);
    }

    @Test
    void getListWhenCallsMethodList_InMethodGetAllById() {
        successfulMethod_getAll();
        verify(result, times(1)).list();
    }

    @Test
    void getErrorMessage_ErrorWhenOpenedSession_AndThrowHibernateException_WhenSessionFactoryOpenSessionInMethodGetAll() {
        when(sessionFactory.openSession()).thenThrow(HibernateException.class);
        HibernateException exception = Assertions.assertThrows(HibernateException.class,
                () -> productTypeRepository.getAll());
        Assertions.assertEquals("Error when opened session on sessionFactory in method getAll from ProductTypeRepositoryImpl",
                exception.getMessage());

    }

    void successfulMethod_getAll() {
        when(sessionFactory.openSession()).thenReturn(session);
        when(session
                .createQuery("SELECT pt FROM product_type pt ORDER BY pt.id", ProductType.class))
                .thenReturn(result);
        productTypeRepository.getAll();
    }
}