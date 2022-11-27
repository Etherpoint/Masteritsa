package ru.familyproject.ryabov.masteritsa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.familyproject.ryabov.masteritsa.entity.Product;
import ru.familyproject.ryabov.masteritsa.repository.ProductRepositoryImpl;

import java.util.List;

@Service public class ProductService {
    private final ProductRepositoryImpl repository;

    public ProductService(@Autowired ProductRepositoryImpl repository) {
        this.repository = repository;
    }

    public List<Product> getAll(){
        return repository.getAll();
    }

    public List<Product> getAllById(Long id){
        return repository.getAllById(id);
    }
}
