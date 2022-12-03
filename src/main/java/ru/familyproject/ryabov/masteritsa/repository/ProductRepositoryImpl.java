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
import ru.familyproject.ryabov.masteritsa.entity.Product;
import ru.familyproject.ryabov.masteritsa.entity.ProductType;

import java.util.List;

@Repository
public class ProductRepositoryImpl implements ProductRepository{
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductRepositoryImpl.class);
    private final SessionFactory sessionFactory;

    public ProductRepositoryImpl() {
        try{
            sessionFactory= new Configuration()
                    .configure()
                    .addAnnotatedClass(Product.class)
                    .addAnnotatedClass(ProductType.class)
                    .buildSessionFactory();
            LOGGER.info("Configuration in ProductRepositoryImpl was successful");
        }catch (HibernateException e){
            LOGGER.error("Error while configuring sessionFactory");
            throw new HibernateException(e);
        }


    }
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
}
