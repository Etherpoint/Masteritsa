package ru.familyproject.ryabov.masteritsa.repository;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.familyproject.ryabov.masteritsa.entity.Comment;
import ru.familyproject.ryabov.masteritsa.entity.User;

/**Класс для работы с сущностями <b>User</b> в БД
 * @see User
 *
 * @author Danila Ryabov
 *
 * @version 1.0
 */
@Repository
public class UserRepository {
    /**
     * slf4j логгер
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(UserRepository.class);
    /**
     * Интерфейс для работы с БД
     */
    private final SessionFactory sessionFactory;
    /**
     * Конструктор с конфигурацией sessionFactory
     */
    public UserRepository() {
        try{
            sessionFactory= new Configuration()
                    .configure()
                    .addAnnotatedClass(Comment.class)
                    .addAnnotatedClass(User.class)
                    .buildSessionFactory();
            LOGGER.info("Configuration in UserRepository was successful");
        }catch (HibernateException e){
            LOGGER.error("Error while configuring sessionFactory");
            throw new HibernateException(e);
        }
    }

    /**
     * Метод для сохранения пользователя в БД
     * @see User
     */
    public void save(User user){
        try(Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            session.save(user);
            session.evict(user);
            transaction.commit();
        }catch (HibernateException e) {
            LOGGER.error("Error when opened session on sessionFactory in method save from UserRepository");
            throw new HibernateException(e);
        }
    }
}
