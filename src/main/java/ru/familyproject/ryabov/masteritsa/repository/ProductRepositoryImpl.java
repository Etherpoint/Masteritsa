package ru.familyproject.ryabov.masteritsa.repository;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.familyproject.ryabov.masteritsa.entity.Product;
import ru.familyproject.ryabov.masteritsa.utils.MySessionFactory;

import java.util.List;

/**Класс для работы с сущностями <b>Product</b> в БД
 * @see ProductRepository
 * @see Product
 *
 * @author Danila Ryabov
 *
 * @version 1.0
 */
@Repository
public class ProductRepositoryImpl implements ProductRepository{
    /**slf4j логгер*/
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductRepositoryImpl.class);
    /**Интерфейс для работы с БД*/
    private final SessionFactory sessionFactory;

    /**Конструкторы с конфигурацией <b>sessionFactory</b>
     * @see SessionFactory
     */
    public ProductRepositoryImpl() {
        sessionFactory = MySessionFactory.getSessionFactory();
    }
    public ProductRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**Метод для получения всего списка сущностей <b>Product</b>*/
    @Override
    public List<Product> getAll() {
        try(Session session = sessionFactory.openSession()){
            Query<Product> result = session.createQuery("SELECT p FROM product p", Product.class);
            LOGGER.info("Method getAll completed successfully");
            return result.list();
        } catch (HibernateException e) {
            LOGGER.error("Error when opened session on sessionFactory in method getAll from ProductRepositoryImpl");
            throw new HibernateException(e);
        }
    }

    /**Метод для получения списка <b>Product</b> с id = productType.id*/
    @Override
    public List<Product> getAllById(Long id) {
        try(Session session = sessionFactory.openSession()){
            Query<Product> result = session.createQuery("SELECT p FROM product p WHERE p.productType.id = :id", Product.class);
            result.setParameter("id", id);
            LOGGER.info("Method getAllById completed successfully");
            return result.list();
        } catch (HibernateException e) {
            LOGGER.error("Error when opened session on sessionFactory in method getAllById from ProductRepositoryImpl");
            throw new HibernateException(e);
        }
    }

    @Override
    public Product getById(Long id) {
        try(Session session = sessionFactory.openSession()){
            Query<Product> result = session.createQuery("SELECT p FROM product p WHERE p.id = :id", Product.class);
            result.setParameter("id", id);
            LOGGER.info("Method getById completed successfully");
            return result.uniqueResult();
        } catch (HibernateException e) {
            LOGGER.error("Error when opened session on sessionFactory in method getById from ProductRepositoryImpl");
            throw new HibernateException(e);
        }
    }


}
