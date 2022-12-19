package ru.familyproject.ryabov.masteritsa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.familyproject.ryabov.masteritsa.entity.ProductType;
import ru.familyproject.ryabov.masteritsa.repository.ProductTypeRepository;
import ru.familyproject.ryabov.masteritsa.repository.ProductTypeRepositoryImpl;

import java.util.List;

/**
 * EN: Service for working with entities <b>ProductType</b><br>
 * RU: Сервис для работы с сущностями <b>ProductType</b>
 * @author Danila Ryabov
 *
 * @version 1.0
 */
@Service
public class ProductTypeService{
    /**
     * EN: Private field for working with <b>ProductType</b> entities in the database<br>
     * RU: Приватное поле работы с сущностями <b>ProductType</b> в БД
     * @see ProductTypeRepository
     */
    private final ProductTypeRepository repository;

    /**
     * EN: Constructor for field initialization <b>repository</b><br>
     * RU: Конструктор для инициализации поля <b>repository</b>
     * @see ProductTypeRepositoryImpl
     */
    public ProductTypeService(@Autowired ProductTypeRepositoryImpl repository){
        this.repository = repository;
    }

    /**
     * EN: Method for getting the entire list of product types<br>
     * RU: Метод для получения всего списка типов продуктов
     */
    public List<ProductType> getAll() {
        return repository.getAll();
    }
}