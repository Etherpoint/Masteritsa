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
import ru.familyproject.ryabov.masteritsa.entity.Role;

import static org.mockito.Mockito.*;

@SpringBootTest
class RoleRepositoryImplTest {
    @Autowired
    RoleRepositoryImpl roleRepository;
    @MockBean
    SessionFactory sessionFactory;
    @MockBean
    Session session;
    @MockBean
    Query<Role> result;

    @Test
    void getSessionWhenSessionFactoryCalls_openSession_InMethodGetRoleByName() {
        successfulMethod_getRoleByName();
        verify(sessionFactory, times(1)).openSession();
    }

    @Test
    void getQueryResultWhenCalls_createQuery_InMethodGetRoleByName() {
        successfulMethod_getRoleByName();
        verify(session, times(1))
                .createQuery("SELECT r FROM roles r WHERE r.name = :name", Role.class);
    }

    @Test
    void methodSetParameterCallsOneTime_InMethodGetRoleByName() {
        successfulMethod_getRoleByName();
        verify(result, times(1))
                .setParameter("name", "USER");
    }

    @Test
    void getUniqueResultWhenCallsMethodList_InMethodGetRoleByName() {
        successfulMethod_getRoleByName();
        verify(result, times(1)).uniqueResult();
    }

    @Test
    void getErrorMessage_ErrorWhenOpenedSession_AndThrowHibernateException_WhenSessionFactoryOpenSessionInMethod_GetRoleByName(){
        when(sessionFactory.openSession()).thenThrow(HibernateException.class);
        HibernateException exception = Assertions.assertThrows(HibernateException.class,
                () -> roleRepository.getRoleByName("USER"));
        Assertions.assertEquals(exception.getMessage(), "Error when opened session on sessionFactory in method getRoleByName from RoleRepositoryImpl");
    }

    void successfulMethod_getRoleByName(){
        when(sessionFactory.openSession()).thenReturn(session);
        when(session
                .createQuery("SELECT r FROM roles r WHERE r.name = :name", Role.class))
                .thenReturn(result);
        roleRepository.getRoleByName("USER");
    }
}