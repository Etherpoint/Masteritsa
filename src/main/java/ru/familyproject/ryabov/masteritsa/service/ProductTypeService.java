package ru.familyproject.ryabov.masteritsa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.familyproject.ryabov.masteritsa.entity.ProductType;
import ru.familyproject.ryabov.masteritsa.repository.ProductTypeRepository;
import ru.familyproject.ryabov.masteritsa.repository.ProductTypeRepositoryImpl;

import java.util.List;

/**Сервис для работы с сущностями <b>ProductType</b>*/
@Service
public class ProductTypeService{
    /**Приватное поле работы с сущностями <b>ProductType</b> в БД
     * @see ProductTypeRepositoryImpl
     * */
    private final ProductTypeRepository repository;

    /**Конструктор для инициализации поля <b>repository</b>*/
    public ProductTypeService(@Autowired ProductTypeRepositoryImpl repository){
        this.repository = repository;
    }

    /**Метод для получения всего списка типов продуктов*/
    public List<ProductType> getAll() {
        return repository.getAll();
    }
}