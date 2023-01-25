package ru.familyproject.ryabov.masteritsa.repository;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.familyproject.ryabov.masteritsa.entity.Product;

import java.util.List;

/**
 * EN: Class for working with entities <b>Product</b> in the database<br>
 * RU: Класс для работы с сущностями <b>Product</b> в БД
 * @see ProductRepository
 * @see Product
 *
 * @author Danila Ryabov
 *
 * @version 1.0
 */
@Repository
public class ProductRepositoryImpl implements ProductRepository{
    /**slf4j logger*/
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductRepositoryImpl.class);
    /**
     * EN: Interface for working with the database<br>
     * RU: Интерфейс для работы с БД
     */
    @Autowired
    private SessionFactory sessionFactory;

    /**
     * EN: Method for getting the entire list of <b>Product</b> entities<br>
     * RU: Метод для получения всего списка сущностей <b>Product</b>
     */
    @Override
    public List<Product> getAll() {
        try(Session session = sessionFactory.openSession()){
            Query<Product> result = session.createQuery("SELECT p FROM product p", Product.class);
            LOGGER.info("Method getAll completed successfully");
            return result.list();
        } catch (HibernateException e) {
            LOGGER.error("Error when opened session on sessionFactory in method getAll from ProductRepositoryImpl");
            throw new HibernateException("Error when opened session on sessionFactory in method getAll from ProductRepositoryImpl", e);
        }
    }

    /**
     * EN: Method for getting the <b>Product</b> list with id = productType.id<br>
     * RU: Метод для получения списка <b>Product</b> с id = productType.id
     */
    @Override
    public List<Product> getAllById(Long id) {
        try(Session session = sessionFactory.openSession()){
            Query<Product> result = session.createQuery("SELECT p FROM product p WHERE p.productType.id = :id", Product.class);
            result.setParameter("id", id);
            LOGGER.info("Method getAllById completed successfully");
            return result.list();
        } catch (HibernateException e) {
            LOGGER.error("Error when opened session on sessionFactory in method getAllById from ProductRepositoryImpl");
            throw new HibernateException("Error when opened session on sessionFactory in method getAllById from ProductRepositoryImpl", e);
        }
    }

    /**
     * EN: Method for getting a product with id = product.id<br>
     * RU: Метод для получения продукта с id = product.id
     */
    @Override
    public Product getById(Long id) {
        try(Session session = sessionFactory.openSession()){
            Query<Product> result = session.createQuery("SELECT p FROM product p WHERE p.id = :id", Product.class);
            result.setParameter("id", id);
            LOGGER.info("Method getById completed successfully");
            return result.uniqueResult();
        } catch (HibernateException e) {
            LOGGER.error("Error when opened session on sessionFactory in method getById from ProductRepositoryImpl");
            throw new HibernateException("Error when opened session on sessionFactory in method getById from ProductRepositoryImpl", e);
        }
    }
}