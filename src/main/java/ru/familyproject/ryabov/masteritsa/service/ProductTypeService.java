package ru.familyproject.ryabov.masteritsa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.familyproject.ryabov.masteritsa.entity.ProductType;
import ru.familyproject.ryabov.masteritsa.repository.ProductTypeRepository;
import ru.familyproject.ryabov.masteritsa.repository.ProductTypeRepositoryImpl;

import java.util.List;

@Service
public class ProductTypeService {
    private final ProductTypeRepository repository;

    public ProductTypeService(@Autowired ProductTypeRepositoryImpl repository){
        this.repository = repository;
    }

    public List<ProductType> getAll() {
        return repository.getAll();
    }
}
