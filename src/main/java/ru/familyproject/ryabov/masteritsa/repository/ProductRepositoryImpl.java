package ru.familyproject.ryabov.masteritsa.repository;

import org.hibernate.HibernateException;
import org.hibernate.QueryException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.familyproject.ryabov.masteritsa.entity.Comment;
import ru.familyproject.ryabov.masteritsa.entity.Product;
import ru.familyproject.ryabov.masteritsa.entity.ProductType;
import ru.familyproject.ryabov.masteritsa.entity.User;

import java.util.List;

/**Класс для работы с сущностями <b>Product</b> в БД
 * @see ProductRepository
 * @see Product
 */
@Repository
public class ProductRepositoryImpl implements ProductRepository{
    /**slf4j логгер*/
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductRepositoryImpl.class);
    /**Интерфейс для работы с БД*/
    private final SessionFactory sessionFactory;

    /**Конструктор с конфигурацией <b>sessionFactory</b>
     * @see SessionFactory
     */
    public ProductRepositoryImpl() {
        try{
            sessionFactory= new Configuration()
                    .configure()
                    .addAnnotatedClass(Product.class)
                    .addAnnotatedClass(ProductType.class)
                    .addAnnotatedClass(Comment.class)
                    .addAnnotatedClass(User.class)
                    .buildSessionFactory();
            LOGGER.info("Configuration in ProductRepositoryImpl was successful");
        }catch (HibernateException e){
            LOGGER.error("Error while configuring sessionFactory");
            throw new HibernateException(e);
        }


    }

    /**Метод для получения всего списка сущностей <b>Product</b>*/
    @Override
    public List<Product> getAll() {
        try(Session session = sessionFactory.openSession()){
            Query<Product> result = session.createQuery("SELECT p FROM product p", Product.class);
            LOGGER.info("Method getAll completed successfully");
            return result.list();
        }catch (Exception e){
            LOGGER.error("Error in method getAll");
            throw new QueryException(e);
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
        }catch (Exception e){
            LOGGER.error("Error in method getAll");
            throw new QueryException(e);
        }
    }

    @Override
    public Product getById(Long id) {
        try(Session session = sessionFactory.openSession()){
            Query<Product> result = session.createQuery("SELECT p FROM product p WHERE p.id = :id", Product.class);
            result.setParameter("id", id);
            LOGGER.info("Method getAllCommentsById completed successfully");
            return result.uniqueResult();
        }catch (Exception e){
            LOGGER.error("Error in method getAllCommentsById");
            throw new QueryException(e);
        }
    }


}
