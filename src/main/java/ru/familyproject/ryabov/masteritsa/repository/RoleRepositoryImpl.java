package ru.familyproject.ryabov.masteritsa.repository;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.familyproject.ryabov.masteritsa.entity.Role;


/**
 * EN: Class for working with entities <b>Role</b> in the database<br>
 * RU: Класс для работы с сущностями <b>Role</b> в БД
 *
 * @author Danila Ryabov
 * @version 1.0
 * @see RoleRepository
 * @see Role
 */
@Repository
public class RoleRepositoryImpl implements RoleRepository{
    /**
     * slf4j logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(RoleRepositoryImpl.class);
    /**
     * EN: Interface for working with the database<br>
     * RU: Интерфейс для работы с БД
     */
    @Autowired
    private SessionFactory sessionFactory;

    /**
     * EN: Method for getting the entity <b>Role</b> by its field <b>name</b><br>
     * RU: Метод для получения сущности <b>Role</b> по ее полю <b>name</b>
     * @see Role#getName()
     *
     * @param name Role.getName()
     * @return Role
     */
    @Override
    public Role getRoleByName(String name) {
        try (Session session = sessionFactory.openSession()) {
            Query<Role> result = session.createQuery("SELECT r FROM roles r WHERE r.name = :name", Role.class);
            result.setParameter("name", name);
            LOGGER.info("Method getRoleByName completed successfully");
            return result.uniqueResult();
        } catch (HibernateException e) {
            LOGGER.error("Error when opened session on sessionFactory in method getRoleByName from RoleRepositoryImpl");
            throw new HibernateException("Error when opened session on sessionFactory in method getRoleByName from RoleRepositoryImpl", e);
        }
    }
}
