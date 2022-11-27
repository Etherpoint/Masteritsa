package ru.familyproject.ryabov.masteritsa.repository;

import org.springframework.stereotype.Repository;
import ru.familyproject.ryabov.masteritsa.entity.Product;

import java.util.List;

@Repository
public interface ProductRepository{
    List<Product> getAll();

    List<Product> getAllById(Long id);
}
