package ru.familyproject.ryabov.masteritsa.repository;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.familyproject.ryabov.masteritsa.entity.User;

@SpringBootTest
class UserRepositoryImplTest {
    @Autowired
    UserRepository userRepository;
    @MockBean
    SessionFactory sessionFactory;
    @MockBean
    User user;
}