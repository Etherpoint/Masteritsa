package ru.familyproject.ryabov.masteritsa.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.familyproject.ryabov.masteritsa.entity.ProductType;

@Repository
public interface ProductTypeRepository extends CrudRepository<ProductType, Long> {

}
