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
import ru.familyproject.ryabov.masteritsa.entity.ProductType;

import java.util.List;

@Repository
public class ProductTypeRepositoryImpl implements ru.familyproject.ryabov.masteritsa.repository.ProductTypeRepository {
    private final SessionFactory sessionFactory;
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductTypeRepositoryImpl.class);

    public ProductTypeRepositoryImpl() {
        try {
            sessionFactory = new Configuration()
                    .configure()
                    .addAnnotatedClass(ProductType.class)
                    .buildSessionFactory();
            LOGGER.info("Configuration in ProductTypeRepositoryImpl was successful");
        } catch (HibernateException e) {
            LOGGER.error("Error while configuring sessionFactory");
            throw new HibernateException(e);
        }
    }

    @Override
    public List<ProductType> getAll() {
        try (Session session = sessionFactory.openSession()) {
            Query<ProductType> result = session.createQuery("SELECT pt FROM product_type pt ORDER BY pt.id", ProductType.class);
            LOGGER.info("Method getAll completed successfully");
            return result.list();
        }catch (Exception e){
            LOGGER.error("Error in method getAll");
            throw new QueryException(e);
        }
    }
}
