package ru.familyproject.ryabov.masteritsa.service;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.familyproject.ryabov.masteritsa.repository.ProductRepositoryImpl;

import java.util.ArrayList;

@SpringBootTest
class ProductServiceTest {
    @Autowired
    private ProductService productService;
    @MockBean
    ProductRepositoryImpl repository;

    @Test
    void getAll() {
        productService.getAll();
        Mockito.verify(repository, Mockito.times(1)).getAll();
    }

    @Test
    void getAllById() {
        repository.getAllById(1L);
        Mockito.doReturn(new ArrayList<>()).when(repository).getAllById(1L);
        Mockito.verify(repository, Mockito.times(1)).getAllById(1L);
    }
}