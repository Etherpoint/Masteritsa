package ru.familyproject.ryabov.masteritsa.repository;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.familyproject.ryabov.masteritsa.entity.Role;

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
    @MockBean
    Role role;

    @Test
    void getErrorMessage_ErrorWhenOpenedSession_AndThrowHibernateException_WhenSessionFactoryOpenSessionInMethod_GetRoleByName(){
        Mockito.when(sessionFactory.openSession()).thenThrow(HibernateException.class);
        HibernateException exception = Assertions.assertThrows(HibernateException.class,
                () -> roleRepository.getRoleByName("USER"));
        Assertions.assertEquals(exception.getMessage(), "Error when opened session on sessionFactory in method getRoleByName from RoleRepositoryImpl");
    }
}