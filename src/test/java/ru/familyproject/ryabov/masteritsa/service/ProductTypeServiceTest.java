package ru.familyproject.ryabov.masteritsa.service;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.familyproject.ryabov.masteritsa.repository.ProductTypeRepositoryImpl;

@SpringBootTest
class ProductTypeServiceTest {

    @Autowired
    private ProductTypeService productTypeService;
    @MockBean
    private ProductTypeRepositoryImpl repository;
    @Test
    void getAll() {
        productTypeService.getAll();
        Mockito.verify(repository, Mockito.times(1)).getAll();
    }
}