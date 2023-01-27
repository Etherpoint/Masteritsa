package ru.familyproject.ryabov.masteritsa.repository;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.familyproject.ryabov.masteritsa.entity.User;


/**
 * EN: Class for working with entities <b>User</b> in the database<br>
 * RU: Класс для работы с сущностями <b>User</b> в БД
 *
 * @see User
 * @see UserRepository
 *
 * @author Danila Ryabov
 * @version 1.0
 */
@Repository
public class UserRepositoryImpl implements UserRepository {
    /**
     * slf4j logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(UserRepositoryImpl.class);
    /**
     * EN: Interface for working with the database<br>
     * RU: Интерфейс для работы с БД
     */
    @Autowired
    private SessionFactory sessionFactory;

    /**
     * EN: Method for saving the entity <b>User</b> in the database<br>
     * RU: Метод для сохранения сущности <b>User</b> в БД
     */
    @Override
    public void save(User user) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
        } catch (HibernateException e) {
            LOGGER.error("Error when opened session on sessionFactory in method save from UserRepositoryImpl");
            throw new HibernateException("Error when opened session on sessionFactory in method save from UserRepositoryImpl", e);
        }
    }

    /**
     * EN: Method for deleting the <b>User</b> entity from the database<br>
     * RU: Метод для удаления сущности <b>User</b> из БД
     */
    @Override
    public void delete(User user) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(user);
            transaction.commit();
        } catch (HibernateException e) {
            LOGGER.error("Error when opened session on sessionFactory in method delete from UserRepositoryImpl");
            throw new HibernateException("Error when opened session on sessionFactory in method delete from UserRepositoryImpl", e);
        }
    }

    /**
     * EN: Method for searching and getting the entity <b>User</b> in the database by the field <b>username</b><br>
     * RU: Метод для поиска и получения сущности <b>User</b> в БД по полю <b>username</b>
     */
    @Override
    public User findByUsername(String username) {
        try (Session session = sessionFactory.openSession()) {
            Query<User> query = session.createQuery("SELECT u FROM users u WHERE u.name = :username", User.class);
            query.setParameter("username", username);
            return query.uniqueResult();
        } catch (HibernateException e) {
            LOGGER.error("Error when opened session on sessionFactory in method findByUsername from UserRepositoryImpl");
            throw new HibernateException("Error when opened session on sessionFactory in method findByUsername from UserRepositoryImpl", e);
        }
    }
}
