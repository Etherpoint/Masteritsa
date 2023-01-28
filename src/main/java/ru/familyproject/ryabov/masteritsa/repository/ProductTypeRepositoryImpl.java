package ru.familyproject.ryabov.masteritsa.repository;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.familyproject.ryabov.masteritsa.entity.ProductType;

import java.util.List;

/**
 * EN: Class for working with entities <b>ProductType</b> in the database<br>
 * RU: Класс для работы с сущностями <b>ProductType</b> в БД
 *
 * @author Danila Ryabov
 * @version 1.0
 * @see ProductTypeRepository
 * @see ProductType
 */
@Repository
public class ProductTypeRepositoryImpl implements ProductTypeRepository {
    /**
     * slf4j logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductTypeRepositoryImpl.class);
    /**
     * EN: Interface for working with the database<br>
     * RU: Интерфейс для работы с БД
     */
    private final SessionFactory sessionFactory;

    /**
     * EN: Constructor to initialize SessionFactory<br>
     * RU: Конструктор для инициализации SessionFactory
     */
    public ProductTypeRepositoryImpl(@Autowired SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * EN: Method for getting the entire list of ProductType entities<br>
     * RU: Метод для получения всего списка сущностей ProductType
     */
    @Override
    public List<ProductType> getAll() {
        try (Session session = sessionFactory.openSession()) {
            Query<ProductType> result = session.createQuery("SELECT pt FROM product_type pt ORDER BY pt.id", ProductType.class);
            LOGGER.info("Method getAll completed successfully");
            return result.list();
        } catch (HibernateException e) {
            LOGGER.error("Error when opened session on sessionFactory in method getAll from ProductTypeRepositoryImpl");
            throw new HibernateException("Error when opened session on sessionFactory in method getAll from ProductTypeRepositoryImpl", e);
        }
    }
}
