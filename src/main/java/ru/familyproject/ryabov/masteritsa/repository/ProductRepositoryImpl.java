package ru.familyproject.ryabov.masteritsa.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.internal.util.config.ConfigurationException;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.familyproject.ryabov.masteritsa.entity.Product;
import ru.familyproject.ryabov.masteritsa.entity.ProductType;
import ru.familyproject.ryabov.masteritsa.utils.CfgForHiber;

import java.util.List;
import java.util.Properties;

@Repository
public class ProductRepositoryImpl implements ProductRepository{
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductRepositoryImpl.class);
    private final SessionFactory sessionFactory;

    public ProductRepositoryImpl() {
        try{
            Properties properties = new Properties();
            properties.put(Environment.DRIVER, CfgForHiber.getDRIVER());
            properties.put(Environment.URL, CfgForHiber.getURL());
            properties.put(Environment.DIALECT, CfgForHiber.getDIALECT());
            properties.put(Environment.USER, CfgForHiber.getUSER());
            properties.put(Environment.PASS, CfgForHiber.getPASSWORD());
            sessionFactory= new Configuration()
                    .addProperties(properties)
                    .addAnnotatedClass(Product.class)
                    .addAnnotatedClass(ProductType.class)
                    .buildSessionFactory();
            LOGGER.info("Configuration in ProductRepositoryImpl was successful");
        }catch (Exception e){
            LOGGER.error("Error while configuring sessionFactory");
            throw new ConfigurationException(e.toString());
        }


    }
    @Override
    public List<Product> getAll() {
        try(Session session = sessionFactory.openSession()){
            Query<Product> result = session.createQuery("SELECT p FROM product p", Product.class);
            return result.list();
        }
    }

    @Override
    public List<Product> getAllById(Long id) {
        try(Session session = sessionFactory.openSession()){
            Query<Product> result = session.createQuery("SELECT p FROM product p WHERE p.productType.id = :id", Product.class);
            result.setParameter("id", id);
            return result.list();
        }
    }
}
