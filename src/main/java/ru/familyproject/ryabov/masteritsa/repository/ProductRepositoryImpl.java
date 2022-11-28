package ru.familyproject.ryabov.masteritsa.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import ru.familyproject.ryabov.masteritsa.entity.Product;
import ru.familyproject.ryabov.masteritsa.entity.ProductType;
import ru.familyproject.ryabov.masteritsa.utils.CfgForHiber;

import java.util.List;
import java.util.Properties;

@Repository
public class ProductRepositoryImpl implements ProductRepository{
    SessionFactory sessionFactory;

    public ProductRepositoryImpl() {
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