package ru.familyproject.ryabov.masteritsa.repository;

import org.springframework.stereotype.Repository;
import ru.familyproject.ryabov.masteritsa.entity.ProductType;

import java.util.List;

@Repository
public interface ProductTypeRepository{
        List<ProductType> getAll();
}
