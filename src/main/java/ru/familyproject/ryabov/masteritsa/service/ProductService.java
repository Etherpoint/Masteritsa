package ru.familyproject.ryabov.masteritsa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.familyproject.ryabov.masteritsa.entity.Product;
import ru.familyproject.ryabov.masteritsa.repository.ProductRepositoryImpl;

import java.util.List;

/**Сервис для работы с сущностью Product*/
@Service public class ProductService {
    /**Приватное поле работы с сущностями <b>Product</b> в БД
     * @see ProductRepositoryImpl
     */
    private final ProductRepositoryImpl repository;

    /**Конструктор для инициализации поля <b>repository</b>*/
    public ProductService(@Autowired ProductRepositoryImpl repository) {
        this.repository = repository;
    }

    /**Метод для получения всего списка продуктов из БД*/
    public List<Product> getAll(){
        return repository.getAll();
    }

    /**Метод для получения всех товаров, с product_type_id = id
     * @see Product#getProductType()
     */
    public List<Product> getAllById(Long id){
        return repository.getAllById(id);
    }
}
