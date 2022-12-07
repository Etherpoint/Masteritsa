package ru.familyproject.ryabov.masteritsa.repository;

import org.springframework.stereotype.Repository;
import ru.familyproject.ryabov.masteritsa.entity.Comment;
import ru.familyproject.ryabov.masteritsa.entity.Product;

import java.util.List;

/**Интерфейс для работы с сущностями <b>Product</b> в БД
 * @see Product
 */
@Repository
public interface ProductRepository{
    List<Product> getAll();

    List<Product> getAllById(Long id);

    Product getById(Long id);
}
