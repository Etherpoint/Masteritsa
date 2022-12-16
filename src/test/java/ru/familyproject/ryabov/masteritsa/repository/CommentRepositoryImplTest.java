package ru.familyproject.ryabov.masteritsa.repository;

import org.hibernate.SessionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.familyproject.ryabov.masteritsa.entity.Comment;
import ru.familyproject.ryabov.masteritsa.entity.Product;
import ru.familyproject.ryabov.masteritsa.entity.ProductType;
import ru.familyproject.ryabov.masteritsa.entity.User;

import java.util.List;

@SpringBootTest
class CommentRepositoryImplTest {
    @Autowired
    CommentRepositoryImpl commentRepository;
    @MockBean
    SessionFactory sessionFactory;
    @MockBean
    ProductType productType;

    private List<Comment> comments;

    @BeforeEach
    void init() {
        User user = new User();
        Product product = new Product();
        comments = List.of(
                new Comment(1L, "Текст1", "2022-12-12", user, product),
                new Comment(2L, "Текст2", "2022-12-11", user, product),
                new Comment(3L, "Текст3", "2022-12-10", user, product));
    }

    @Test
    void successfullyGetCommentsWithProductIdEqualsOne_WhenCallsMethod_getAllCommentsById_WithParameterEqualsOne() {
        commentRepository.getAllCommentsById(1L);
        /*when(sessionFactory.openSession()).thenReturn(session);
        verify(sessionFactory, times(1)).openSession();*/
    }
}