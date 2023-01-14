package ru.familyproject.ryabov.masteritsa.service;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.familyproject.ryabov.masteritsa.entity.Comment;
import ru.familyproject.ryabov.masteritsa.entity.Product;
import ru.familyproject.ryabov.masteritsa.repository.CommentRepositoryImpl;
import ru.familyproject.ryabov.masteritsa.repository.ProductRepositoryImpl;

import java.util.ArrayList;

@SpringBootTest
class ProductServiceTest {
    @Autowired
    private ProductService productService;
    @MockBean
    private ProductRepositoryImpl productRepository;
    @MockBean
    private CommentRepositoryImpl commentRepository;

    @Test
    void repositoryCallsOneTimeWhenCallsMethod_getAllInProductService() {
        productService.getAll();
        Mockito.verify(productRepository, Mockito.times(1)).getAll();
    }

    @Test
    void getListOfProductsWhenCallsMethod_getAllById() {
        productService.getAllById(1L);
        Mockito.doReturn(new ArrayList<Product>()).when(productRepository).getAllById(1L);
        Mockito.verify(productRepository, Mockito.times(1)).getAllById(1L);
    }

    @Test
    void getListOfCommentsWhenCallsMethod_getAllCommentsById(){
        productService.getAllCommentsById(1L);
        Mockito.doReturn(new ArrayList<Comment>()).when(commentRepository).getAllCommentsById(1L);
        Mockito.verify(commentRepository, Mockito.times(1)).getAllCommentsById(1L);
    }

    @Test
    void getProductWhenCallsMethod_getById(){
        productService.getById(1L);
        Mockito.doReturn(new Product()).when(productRepository).getById(1L);
        Mockito.verify(productRepository, Mockito.times(1)).getById(1L);
    }
}