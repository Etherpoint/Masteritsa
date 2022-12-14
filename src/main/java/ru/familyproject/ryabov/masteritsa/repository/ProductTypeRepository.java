package ru.familyproject.ryabov.masteritsa.repository;

import org.springframework.stereotype.Repository;
import ru.familyproject.ryabov.masteritsa.entity.ProductType;

import java.util.List;

/**
 * Интерфейс для работы с сущностями <b>ProductType</b> в БД
 *
 * @see ProductType
 *
 * @author Danila Ryabov
 * @version 1.0
 */
@Repository
public interface ProductTypeRepository {
    List<ProductType> getAll();
}