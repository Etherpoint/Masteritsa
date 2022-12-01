package ru.familyproject.ryabov.masteritsa.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import ru.familyproject.ryabov.masteritsa.entity.ProductType;
import ru.familyproject.ryabov.masteritsa.utils.CfgForHiber;

import java.util.List;
import java.util.Properties;

@Repository
public class ProductTypeRepositoryImpl implements ru.familyproject.ryabov.masteritsa.repository.ProductTypeRepository {
    SessionFactory sessionFactory;

    public ProductTypeRepositoryImpl() {
        Properties properties = new Properties();
        properties.put(Environment.DRIVER, CfgForHiber.getDRIVER());
        properties.put(Environment.URL, CfgForHiber.getURL());
        properties.put(Environment.DIALECT, CfgForHiber.getDIALECT());
        properties.put(Environment.USER, CfgForHiber.getUSER());
        properties.put(Environment.PASS, CfgForHiber.getPASSWORD());
        sessionFactory= new Configuration()
                .addProperties(properties)
                .addAnnotatedClass(ProductType.class)
                .buildSessionFactory();
    }

    @Override
    public List<ProductType> getAll() {
        try(Session session = sessionFactory.openSession()){
            Query<ProductType> result = session.createQuery("SELECT pt FROM product_type pt ORDER BY pt.id", ProductType.class);
            return result.list();
        }
    }
}
