package ru.familyproject.ryabov.masteritsa.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.familyproject.ryabov.masteritsa.entity.Comment;
import ru.familyproject.ryabov.masteritsa.entity.Product;
import ru.familyproject.ryabov.masteritsa.entity.ProductType;
import ru.familyproject.ryabov.masteritsa.entity.User;

/**
 * EN: Singleton class for configuration <b>SessionFactory</b><br>
 * RU: Класс-синглтон для конфигурации <b>SessionFactory</b>
 * @see SessionFactory
 */
public class MySessionFactory {
    private static MySessionFactory instance;
    private final SessionFactory sessionFactory;
    private MySessionFactory() {
        sessionFactory = new Configuration()
                .configure()
                .addAnnotatedClass(Product.class)
                .addAnnotatedClass(ProductType.class)
                .addAnnotatedClass(Comment.class)
                .addAnnotatedClass(User.class)
                .buildSessionFactory();
    }

    /**
     * EN: Static method for getting <b>sessionFactory</b><br>
     * RU: Статический метод для получения <b>sessionFactory</b>
     */
    public static SessionFactory getSessionFactory() {
        if (instance == null) {
            instance = new MySessionFactory();
        }
        return instance.sessionFactory;
    }
}
